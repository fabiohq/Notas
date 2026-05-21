
import { type FastifyPluginAsync, type FastifyRequest, type FastifyReply } from 'fastify';
import {
  ClienteDetalleResponseSchema,
  ConsultarDetalleClienteBodySchema,
  ErrorResponseSchema,
  type ConsultarDetalleClienteBody
} from './schemas';
import { createHttpClient } from '../../lib/shared/httpClient';
import { getApiConfig } from '../../config/api.config';
import { ConsultaClienteRequest } from 'types';
import {
  ERROR_CODE_MAP
} from '../../constants/error-codes';
import { TIPO_IDENTIFICACION, TIPO_USUARIO, TIPO_VIA } from '../../constants/constantes';
import { formatDateToLocalISO, formatDateToDDMMYYYY, generarNumeroAleatorio, logErrorToCSV } from '../../lib';
import { logPayloadToDatabase } from '../../lib/shared/databaseErrorLogger';
import {
  getConsultarDetalleClienteAltairErrorMapping,
  CONSULTAR_DETALLE_CLIENTE_HTTP_STATUS_CONFIG
} from '../../constants/consultar-detalle-cliente-error-mapping';
import ciudades from '../../enums/ciudades.enum';
import { CiudadInfo } from '../../enums/ciudades.enum';


function formatIdentificacion(identificacion: string): string {
  const cuentaConPadding = identificacion.padStart(11, '0');
  return `${cuentaConPadding}`;
}

function formatCodigoCategoria(codigo: string): string {
  return codigo ? String(parseInt(codigo, 10)) : '';
}


function buscarCiudad(clave: string): CiudadInfo {
  const claveInt = parseInt(clave);
  return ciudades[claveInt] ?? null;
}

/**
 * Maneja errores de validación del esquema
 */
async function handleValidationError(
  request: any,
  reply: any,
  mensajeId: string,
  fechaCreacion: string,
  fechaActualizacion: string
) {
  const validationErrors = request.validationError.validation || [];

  const tipoIdentificacionError = validationErrors.find(
    (err: any) =>
      err.instancePath === '/tipoIdentificacion' &&
      (err.keyword === 'enum' || err.message?.includes('enum'))
  );

  const identificacionLengthError = validationErrors.find(
    (err: any) =>
      err.instancePath === '/identificacion' &&
      (err.keyword === 'minLength' || err.keyword === 'maxLength')
  );

  let errorConfig = ERROR_CODE_MAP.INVALID_PAYLOAD;
  let errorType = 'INVALID_PAYLOAD';

  if (tipoIdentificacionError) {
    errorConfig = ERROR_CODE_MAP.UNSUPPORTED_ID_TYPE;
    errorType = 'UNSUPPORTED_ID_TYPE';
  } else if (identificacionLengthError) {
    errorConfig = ERROR_CODE_MAP.INVALID_ID_NUMBER;
    errorType = 'INVALID_ID_NUMBER';
  } else {
  }

  function formatIdentificacion(identificacion: string): string {
    const identificacionConPadding = identificacion.padStart(11, '0');
    return `${identificacionConPadding}`;
  }

  const validationError = new Error('Error de validación en la solicitud');

  const payload400 = {
    resultado: {
      mensajeId,
      error: true,
      codigo: errorConfig.codigo,
      descripcionError: errorConfig.descripcion,
      fechaCreacion,
      fechaActualizacion
    }
  };

  request.log.info({ mensajeId, codigo: payload400.resultado.codigo, payload: payload400 }, 'Respuesta enviada (validación)');

  logErrorToCSV(
    request.log,
    {
      ...validationError,
      code: errorType,
      statusCode: errorConfig.httpStatus,
      message: payload400.resultado.descripcionError,
      details: payload400
    },
    mensajeId,
    errorType
  );

  await logPayloadToDatabase(request.log, payload400, mensajeId, 'CONSULTAR_DETALLE_CLIENTE');

  return reply.code(errorConfig.httpStatus).send(payload400);
}

/**
 * Maneja errores de Altair en el array errores[]
 */
async function handleAltairErrorArray(
  coreErrores: any[],
  request: any,
  reply: any,
  mensajeId: string,
  fechaCreacion: string,
  fechaActualizacion: string
) {
  const primerError = coreErrores[0];
  const codigoAltair = primerError.codigo;

  request.log.warn({
    mensajeId,
    codigoAltair,
    mensajeAltair: primerError.mensaje,
    erroresCompletos: coreErrores
  }, 'Error de validación de Altair detectado en errores[]');

  try {
    const altairError = new Error(primerError.mensaje || 'Error de Altair');
    (altairError as any).code = codigoAltair;
    const errorMapping = getConsultarDetalleClienteAltairErrorMapping(codigoAltair);
    (altairError as any).statusCode = errorMapping?.httpStatus || 500;
    (altairError as any).details = coreErrores;
    logErrorToCSV(request.log, altairError, mensajeId, 'ALTAIR_VALIDATION_ERROR');
  } catch (e) {
    request.log.error({ mensajeId, err: e }, 'No se pudo registrar el error de Altair en CSV');
  }

  const errorMapping = getConsultarDetalleClienteAltairErrorMapping(codigoAltair);

  if (errorMapping) {
    const payloadAltairErr = {
      resultado: {
        mensajeId,
        error: true,
        codigo: errorMapping.codigoRta,
        descripcionError: errorMapping.descripcion,
        fechaCreacion,
        fechaActualizacion
      }
    };

    request.log.info({ mensajeId, codigo: payloadAltairErr.resultado.codigo, payload: payloadAltairErr }, 'Respuesta enviada (error Altair)');
    await logPayloadToDatabase(request.log, payloadAltairErr, mensajeId, 'CONSULTAR_DETALLE_CLIENTE');
    return reply.code(errorMapping.httpStatus).send(payloadAltairErr);
  }

  const payloadAltairGenerico = {
    resultado: {
      mensajeId,
      error: true,
      codigo: 2008,
      descripcionError: primerError.mensaje || 'Error de validación de Altair',
      fechaCreacion,
      fechaActualizacion
    }
  };

  request.log.info({ mensajeId, codigo: payloadAltairGenerico.resultado.codigo, payload: payloadAltairGenerico }, 'Respuesta enviada (error Altair sin mapeo)');
  await logPayloadToDatabase(request.log, payloadAltairGenerico, mensajeId, 'CONSULTAR_DETALLE_CLIENTE');
  return reply.code(CONSULTAR_DETALLE_CLIENTE_HTTP_STATUS_CONFIG.ERROR_NEGOCIO).send(payloadAltairGenerico);

}

/**
 * Construye y envía el payload de éxito
 */

async function buildSuccessPayload(
  coreResp: any,
  request: any,
  reply: any,
  mensajeId: string,
  tipoIdentificacion: string,
  identificacion: string,
  fechaCreacion: string,
  fechaActualizacion: string
) {
  const cliente = coreResp?.data?.datosBasicos ?? {} as any;

  const fechaNacimientoFormateada = formatDateToDDMMYYYY((cliente.fechaConstitucion ?? '').toString());

  const prefijoCel: string = cliente.prefijoCel ?? '';
  const numeroCel: string = cliente.numeroCel ?? '';
  const celular = (prefijoCel.length >= 2 ? prefijoCel.slice(2) : prefijoCel) + numeroCel;


  const direccionParts = [
    typeof cliente.tipoVia !== 'undefined' ? TIPO_VIA[cliente.tipoVia] ?? '' : '',
    cliente.nombreVia ?? '',
    cliente.descripcionDireccion ?? ''
  ].filter(Boolean);
  const direccion = direccionParts.join(' ').trim();
  const codigoDane = cliente.departamentoDireccion || '';
  const ciudad = buscarCiudad(codigoDane || '');

  request.log.info('formatCiudad ->', codigoDane, ciudades);
  const successPayload = {
    resultado: {
      mensajeId,
      error: false,
      codigo: 0,
      descripcionError: '',
      fechaCreacion,
      fechaActualizacion
    },
    cliente: {
      tipoUsuario: TIPO_USUARIO.JURIDICA,
      nombrePj: cliente.razonSocial,
      primerNombre: '',
      segundoNombre: '',
      primerApellido: '',
      segundoApellido: '',
      tipoIdentificacion,
      identificacion,
      correo: cliente.email,
      celular,
      fechaNacimiento: fechaNacimientoFormateada,
      esComercio: true,
      merchantId: generarNumeroAleatorio(),
      direccion,
      codigoDane: cliente.departamentoDireccion || '',
      ciudad: ciudad?.descripcion ?? '',
      pais: 'CO',
      codigoPostal: ciudad?.codigoPostal ?? '',
      codigoCategoria: formatCodigoCategoria(cliente.codActEco || ''),
      idAgregador: ''
    }
  };

  request.log.info({ mensajeId, codigo: successPayload.resultado.codigo, payload: successPayload }, 'Respuesta enviada (success)');

  await logPayloadToDatabase(request.log, successPayload, mensajeId, 'CONSULTAR_DETALLE_CLIENTE');

  return reply.code(200).send(successPayload);
}

/**
 * Maneja errores de Altair en el catch
 */
async function handleAltairErrorInCatch(
  coreErrores: any[],
  request: any,
  reply: any,
  mensajeId: string,
  fechaCreacion: string,
  fechaActualizacion: string
) {
  const primerError = coreErrores[0];
  const codigoAltair = primerError.codigo;

  request.log.warn({
    mensajeId,
    codigoAltair,
    mensajeAltair: primerError.mensaje,
    erroresCompletos: coreErrores
  }, 'Error de validación de Altair detectado en catch errores[]');

  try {
    const altairError = new Error(primerError.mensaje || 'Error de Altair');
    (altairError as any).code = codigoAltair;
    const errorMapping = getConsultarDetalleClienteAltairErrorMapping(codigoAltair);
    (altairError as any).statusCode = errorMapping?.httpStatus || 500;
    (altairError as any).details = coreErrores;
    logErrorToCSV(request.log, altairError, mensajeId, 'ALTAIR_VALIDATION_ERROR_CATCH');
  } catch (e) {
    request.log.error({ mensajeId, err: e }, 'No se pudo registrar el error de Altair en CSV');
  }

  const errorMapping = getConsultarDetalleClienteAltairErrorMapping(codigoAltair);

  if (errorMapping) {
    const payloadAltairErr = {
      resultado: {
        mensajeId,
        error: true,
        codigo: errorMapping.codigoRta,
        descripcionError: errorMapping.descripcion,
        fechaCreacion,
        fechaActualizacion
      }
    };

    request.log.info({ mensajeId, codigo: payloadAltairErr.resultado.codigo, payload: payloadAltairErr }, 'Respuesta enviada (error Altair desde catch)');
    await logPayloadToDatabase(request.log, payloadAltairErr, mensajeId, 'CONSULTAR_DETALLE_CLIENTE');
    return reply.code(errorMapping.httpStatus).send(payloadAltairErr);
  }

  const payloadAltairGenerico = {
    resultado: {
      mensajeId,
      error: true,
      codigo: 2008,
      descripcionError: primerError.mensaje || 'Error de validación de Altair',
      fechaCreacion,
      fechaActualizacion
    }
  };

  request.log.info({ mensajeId, codigo: payloadAltairGenerico.resultado.codigo, payload: payloadAltairGenerico }, 'Respuesta enviada (error Altair sin mapeo desde catch)');
  await logPayloadToDatabase(request.log, payloadAltairGenerico, mensajeId, 'CONSULTAR_DETALLE_CLIENTE');
  return reply.code(CONSULTAR_DETALLE_CLIENTE_HTTP_STATUS_CONFIG.ERROR_NEGOCIO).send(payloadAltairGenerico);
}

/**
 * Maneja errores inesperados
 */
async function handleUnexpectedError(
  error: any,
  request: any,
  reply: any,
  mensajeId: string,
  fechaCreacion: string,
  fechaActualizacion: string
) {
  const payloadUnexpected = {
    resultado: {
      mensajeId,
      error: true,
      codigo: 2003,
      descripcionError: 'Error inesperado en la ejecución del servicio.',
      fechaCreacion,
      fechaActualizacion
    }
  };

  request.log.error({ mensajeId, codigo: payloadUnexpected.resultado.codigo, payload: payloadUnexpected }, 'Respuesta enviada (unexpected error)');

  logErrorToCSV(
    request.log,
    {
      ...(error as Error),
      message: payloadUnexpected.resultado.descripcionError,
      statusCode: 500,
      details: payloadUnexpected
    },
    mensajeId,
    'UNEXPECTED_ERROR'
  );

  return reply.code(500).send(payloadUnexpected);
}

/**
 * ConsultarDetalleCliente router plugin
 * Handles client detail consultation endpoints
 *
 * @remarks
 * This plugin follows Fastify v5 best practices:
 * - Uses FastifyPluginAsync for async/await pattern
 * - Type-safe with TypeBox schemas
 * - Automatic validation with JSON Schema
 * - Service layer for business logic
 * - Proper error handling and logging
 */
const consultarDetalleClienteRouter: FastifyPluginAsync = async (fastify) => {
  /**
   * POST /ConsultarDetalleCliente
   * Retrieves detailed information about a client from external API
   */
  fastify.post<{ Body: ConsultarDetalleClienteBody }>(
    '/ConsultarDetalleCliente',
    {
      schema: {
        body: ConsultarDetalleClienteBodySchema,
        response: {
          200: ClienteDetalleResponseSchema,
          400: ErrorResponseSchema,
          404: ErrorResponseSchema,
          422: ErrorResponseSchema,
          500: ErrorResponseSchema,
          503: ErrorResponseSchema
        }
      },
      attachValidation: true // Attach validation errors to request object
    },
    async (
      request: FastifyRequest<{ Body: ConsultarDetalleClienteBody }>,
      reply: FastifyReply
    ) => {
      // Use mensajeId from request (generated globally in onRequest hook)
      const mensajeId = request.mensajeId;
      const fechaCreacion = new Date().toISOString();
      const fechaActualizacion = new Date().toISOString();

      // Validacion de errores para esta ruta específica
      if (request.validationError) {
        return await handleValidationError(request, reply, mensajeId, fechaCreacion, fechaActualizacion);
      }

      try {
        const { tipoIdentificacion, identificacion } = request.body;

        // Logueamos la solicitud entrante con el mensajeId
        request.log.info(
          { tipoIdentificacion, identificacion, mensajeId },
          'Consultando detalle del cliente'
        );

        // Mapeamos tipo identificacion con acceso seguro
        const tipoDocumentoMapeado = TIPO_IDENTIFICACION[tipoIdentificacion as keyof typeof TIPO_IDENTIFICACION];

        request.log.debug(
          { tipoIdentificacion, tipoDocumentoMapeado },
          'Tipo de identificación mapeado correctamente'
        );

        const rawSecuencia = Number(process.env.API_SANBA_SECUENCIA);
        const secuencia = Number.isNaN(rawSecuencia) ? 0 : rawSecuencia;

        const clientePJuridica: ConsultaClienteRequest = {
          cabecera: {
            rutaServicio: process.env.API_SANBA_RUTA_SERVICIO,
            sesion: {
              usuario: process.env.API_SANBA_USUARIO,
              terminal: '',
              horaConexion: formatDateToLocalISO(new Date()),
              entorno: process.env.API_SANBA_ENTORNO,
              perfil: process.env.API_SANBA_PERFIL,
              sucursal: process.env.API_SANBA_SUCURSAL,
              entidad: process.env.API_SANBA_ENTIDAD,
              diasRestantesCambioClave: process.env.API_SANBA_DIAS_RESTANTES_CAMBIO_CLAVE,
              fechaContable: process.env.API_SANBA_FECHA_CONTABLE,
              turno: ''
            },
            funcion: process.env.API_SANBA_FUNCION,
            secuencia,
            canal: 'TFC'
          },
          data: {
            datosIdentificacion: {
              tipoDocumento: tipoDocumentoMapeado,
              numDocumento: formatIdentificacion(identificacion)
            }
          }
        };

        // Log the mapped request at info level temporarily to troubleshoot core payload
        request.log.info({ clientePJuridica, mensajeId }, 'Cuerpo mapeado para core');

        // Build HTTP client and call core directly
        const apiConfig = getApiConfig('sanbaApi');
        const httpClient = createHttpClient({ baseURL: apiConfig.baseURL, timeout: apiConfig.timeout, rejectUnauthorized: apiConfig.rejectUnauthorized });

        const response = await httpClient.post(
          process.env.API_SANBA_RUTA_CONSULTA_PERSONA_JURIDICA,
          clientePJuridica,
          {
            headers: { mqRoute: process.env.API_SANBA_MQ_ROUTE || 'QCTFD' }
          }
        );

        request.log.info({ status: response.status, data: response.data, mensajeId }, 'Respuesta desde core');

        // PRIORIDAD 1: Verificar errores de validación en el array errores[] (códigos PEE de Altair)
        const coreResp: any = response?.data || {};
        const coreErrores = Array.isArray(coreResp.errores) ? coreResp.errores : [];

        if (coreErrores.length > 0) {
          return await handleAltairErrorArray(coreErrores, request, reply, mensajeId, fechaCreacion, fechaActualizacion);
        }

        return await buildSuccessPayload(coreResp, request, reply, mensajeId, tipoIdentificacion, identificacion, fechaCreacion, fechaActualizacion);
      } catch (error) {
        request.log.warn(
          { error: error && (error as any).message ? (error as any).message : String(error), code: (error as any)?.code, statusCode: (error as any)?.statusCode, mensajeId },
          'Error al procesar la solicitud'
        );

        // Log completo del error para debugging
        request.log.debug({ fullError: error, data: (error as any)?.data, responseData: (error as any)?.response?.data }, 'Estructura completa del error');

        // Verificar si el error contiene errores de Altair (igual que NotificarProcesoLlave)
        const coreResp = (error as any)?.data || (error as any)?.response?.data;

        if (coreResp && typeof coreResp === 'object') {
          const coreErrores = Array.isArray(coreResp.errores) ? coreResp.errores : [];

          // PRIORIDAD 1: Verificar si hay errores de Altair mapeables en errores[]
          if (coreErrores.length > 0) {
            return await handleAltairErrorInCatch(coreErrores, request, reply, mensajeId, fechaCreacion, fechaActualizacion);
          }
        }

        return await handleUnexpectedError(error, request, reply, mensajeId, fechaCreacion, fechaActualizacion);
      }
    }
  );
};

export { formatIdentificacion, formatCodigoCategoria };
export default consultarDetalleClienteRouter;

import { Type, Static } from '@sinclair/typebox';

/**
 * Tipos de identificación válidos
 * CC: Cédula de ciudadanía
 * CE: Cédula de extranjería
 * NUIP: Número Único de Identificación Personal
 * PPT: Permiso de Protección Temporal
 * NIT: Número de Identificación Tributaria sin dígito de verificación
 * PEP: Permiso Especial de Permanencia
 * PAS: Número de Pasaporte
 * TDI: Tarjeta de identidad
 */
const TIPOS_IDENTIFICACION_ENUM = ['CC', 'CE', 'NUIP', 'PPT', 'NIT', 'PEP', 'PAS', 'TDI'] as const;

/**
 * Schema for ConsultarDetalleCliente request body
 */
export const ConsultarDetalleClienteBodySchema = Type.Object({
  tipoIdentificacion: Type.String({
    enum: TIPOS_IDENTIFICACION_ENUM,
    description: 'Tipo de identificación del cliente. Valores permitidos: CC (Cédula de ciudadanía), CE (Cédula de extranjería), NUIP (Número Único de Identificación Personal), PPT (Permiso de Protección Temporal), NIT (Número de Identificación Tributaria), PEP (Permiso Especial de Permanencia), PAS (Número de Pasaporte), TDI (Tarjeta de identidad)'
  }),
  identificacion: Type.String({
    minLength: 1,
    maxLength: 11,
    description: 'Número de identificación del cliente debe tener entre 1 y 11 caracteres.'
  })
});

/**
 * Schema for Cliente detail data
 */
export const ClienteDetalleResponseSchema = Type.Object({
  resultado: Type.Object({
    mensajeId: Type.String(),
    error: Type.Boolean(),
    codigo: Type.Integer(),
    descripcionError: Type.String(),
    fechaCreacion: Type.String({ format: 'date-time' }),
    fechaActualizacion: Type.String({ format: 'date-time' })
  }),
  cliente: Type.Optional(Type.Object({
    tipoUsuario: Type.String(),
    nombrePj: Type.String(),
    primerNombre: Type.String(),
    segundoNombre: Type.String(),
    primerApellido: Type.String(),
    segundoApellido: Type.String(),
    tipoIdentificacion: Type.String(),
    identificacion: Type.String(),
    correo: Type.String(),
    celular: Type.String(),
    fechaNacimiento: Type.String({ format: 'date-time' }),
    esComercio: Type.Boolean(),
    merchantId: Type.Optional(Type.String({
      description: 'Identificador único del comercio',
      examples: ['0018902092']
    })),
    direccion: Type.Optional(Type.String({
      minLength: 5,
      maxLength: 70,
      description: 'Dirección física detallada del cliente. Solo permite 2 caracteres especiales numeral (#) y guión (-). Este dato es requerido si se tiene vínculo con Entrecuentas',
      examples: ['Clle 3 # 10 - 20']
    })),
    codigoDane: Type.Optional(Type.String({
      minLength: 5,
      maxLength: 5,
      description: 'Código de la Ciudad según el DANE. Este dato es requerido si se tiene Web Component',
      examples: ['11001']
    })),
    ciudad: Type.Optional(Type.String({
      minLength: 1,
      maxLength: 50,
      description: 'Ciudad asociada al cliente. Este dato es requerido si se tiene Web Component',
      examples: ['BOGOTA']
    })),
    pais: Type.Optional(Type.String({
      minLength: 2,
      maxLength: 2,
      description: 'Nomenclatura del país. Este dato es requerido si se tiene Web Component',
      examples: ['CO']
    })),
    codigoPostal: Type.Optional(Type.String({
      minLength: 1,
      maxLength: 10,
      description: 'Código postal del cliente. Este dato es requerido si se tiene Web Component',
      examples: ['110111']
    })),
    codigoCategoria: Type.Optional(Type.String({
      minLength: 1,
      maxLength: 20,
      description: 'Código de categoría del cliente. Si está identificado como comercio, se debe enviar el código de actividad económica, si se trata de una persona enviar "0000". Este dato es requerido si se tiene Web Component',
      examples: ['3210']
    })),
    idAgregador: Type.Optional(Type.String({
      minLength: 1,
      maxLength: 15,
      description: 'Código único asociado al cliente cuando se identifica como comercio',
      examples: ['65478987654']
    }))
  }))

});

/**
 * Schema for error response
 * Basado en la especificación del API
 */
export const ErrorResponseSchema = Type.Object({
  resultado: Type.Object({
    mensajeId: Type.String({
      format: 'uuid',
      description: 'Identificador del mensaje enviado en la petición (de tipo UUID v4), utilizado para la trazabilidad del llamado del API en el log'
    }),
    error: Type.Boolean({
      description: 'Indicador de éxito o error en el proceso. true: Proceso fallido, false: Proceso exitoso'
    }),
    codigo: Type.Integer({
      description: 'Código del error en caso de ocurrencia, si el proceso es exitoso retorna valor cero',
      examples: [10101]
    }),
    descripcionError: Type.String({
      maxLength: 255,
      description: 'Descripción del error en caso de ocurrencia, si el proceso es exitoso retorna un string vacío',
      examples: ['Transacción no encontrada']
    }),
    fechaCreacion: Type.String({
      format: 'date-time',
      description: 'Fecha y hora en la que se creó la transacción',
      examples: ['2024-08-01T21:38:05.491Z']
    }),
    fechaActualizacion: Type.String({
      format: 'date-time',
      description: 'Fecha y hora en la que se efectuó la última actualización de la transacción',
      examples: ['2024-08-02T13:18:01.569Z']
    })
  })
});

/**
 * TypeScript types derived from schemas
 */
export type ConsultarDetalleClienteBody = Static<typeof ConsultarDetalleClienteBodySchema>;
export type ClienteDetalle = Static<typeof ClienteDetalleResponseSchema>;
export type ErrorResponse = Static<typeof ErrorResponseSchema>;



import { type FastifyPluginAsync, type FastifyRequest, type FastifyReply } from 'fastify';
import { NotificarProcesoLlaveBodySchema, NotificarProcesoLlaveResponseSchema, ErrorResponseSchema } from './schemas';
import { createHttpClient } from '../../lib/shared/httpClient';
import { getApiConfig } from '../../config/api.config';
import { getErrorCodeNumber, getHttpStatusForError, getErrorDescription } from '../../constants/error-codes';
import { TIPO_IDENTIFICACION } from '../../constants/constantes';
import { logErrorToCSV } from '../../lib';
import { logPayloadToDatabase } from '../../lib/shared/databaseErrorLogger';
import { enviarNotificacionAsync } from '../../services/envio-notificacion.service';
import {
  getNotificarProcesoLlaveAltairErrorMapping,
  NOTIFICAR_PROCESO_LLAVE_HTTP_STATUS_CONFIG,
  NOTIFICAR_PROCESO_LLAVE_VALIDACION_LOCAL_CONFIG
} from '../../constants/notificar-proceso-llave-error-mapping';

function formatIdentificacion(nroiden: string): string {
  const cuentaConPadding = nroiden.padStart(11, '0');
  return `${cuentaConPadding}`;
}
function formatNumPago(numeroMedioPago: string): string {
  if (numeroMedioPago.length >= 9) {
    const pagoConPadding = numeroMedioPago.padStart(12, '0');
    return `00650100${pagoConPadding}`;
  }

  return numeroMedioPago;
}
/**
 * Formatea timestamp para SANBA (24 caracteres)
 */
function formatSanbaTimestamp(d: Date): string {
  const yyyy = d.getFullYear();
  const mm = String(d.getMonth() + 1).padStart(2, '0');
  const dd = String(d.getDate()).padStart(2, '0');
  const HH = String(d.getHours()).padStart(2, '0');
  const Min = String(d.getMinutes()).padStart(2, '0');
  const SS = String(d.getSeconds()).padStart(2, '0');
  const ms = String(d.getMilliseconds()).padStart(3, '0');
  const micro = String(Math.floor((d.getMilliseconds() % 1000))).padStart(3, '0');
  const full = `${yyyy}-${mm}-${dd}-${HH}.${Min}.${SS}.${ms}${micro}`;
  return full.slice(0, 24);
}

function formatNotificacionTimestamp(d: Date): string {
  const yyyy = d.getFullYear();
  const mm = String(d.getMonth() + 1).padStart(2, '0');
  const dd = String(d.getDate()).padStart(2, '0');
  const HH = String(d.getHours()).padStart(2, '0');
  const Min = String(d.getMinutes()).padStart(2, '0');
  const SS = String(d.getSeconds()).padStart(2, '0');
  const ms = String(d.getMilliseconds()).padStart(3, '0');
  const micro = String(Math.floor((d.getMilliseconds() % 1000))).padStart(3, '0');
  const full = `${yyyy}-${mm}-${dd} ${HH}:${Min}:${SS}`;
  return full.slice(0, 19);
}

/**
 * Maneja errores de validación: campos mal escritos (additionalProperties)
 */
async function handleValidacionCampoIncorrecto(
  request: any,
  reply: any,
  mensajeId: string,
  fechaCreacion: string,
  fechaActualizacion: string
) {
  const config = NOTIFICAR_PROCESO_LLAVE_VALIDACION_LOCAL_CONFIG.CAMPO_INCORRECTO;
  const validationError = new Error('Validación fallida: Campo incorrecto');

  const payload400 = {
    resultado: {
      mensajeId,
      error: true,
      codigo: config.codigoRta,
      descripcionError: config.descripcion,
      fechaCreacion,
      fechaActualizacion
    }
  };

  request.log.info({ mensajeId, codigo: payload400.resultado.codigo, payload: payload400 }, 'Respuesta enviada (campo incorrecto)');

  logErrorToCSV(
    request.log,
    {
      ...validationError,
      code: 'CAMPO_INCORRECTO',
      statusCode: config.httpStatus,
      message: payload400.resultado.descripcionError,
      details: payload400
    },
    mensajeId,
    'CAMPO_INCORRECTO'
  );

  await logPayloadToDatabase(request.log, payload400, mensajeId, 'NOTIFICAR_PROCESO_LLAVE');

  return reply.code(config.httpStatus).send(payload400);
}

/**
 * Maneja errores de validación: campos obligatorios faltantes (required)
 */
async function handleValidacionCampoObligatorio(
  request: any,
  reply: any,
  mensajeId: string,
  fechaCreacion: string,
  fechaActualizacion: string
) {
  const config = NOTIFICAR_PROCESO_LLAVE_VALIDACION_LOCAL_CONFIG.CAMPO_OBLIGATORIO;
  const validationError = new Error('Validación fallida: Campo obligatorio');

  const payload400 = {
    resultado: {
      mensajeId,
      error: true,
      codigo: config.codigoRta,
      descripcionError: config.descripcion,
      fechaCreacion,
      fechaActualizacion
    }
  };

  request.log.info({ mensajeId, codigo: payload400.resultado.codigo, payload: payload400 }, 'Respuesta enviada (campo obligatorio)');

  logErrorToCSV(
    request.log,
    {
      ...validationError,
      code: 'CAMPO_OBLIGATORIO',
      statusCode: config.httpStatus,
      message: payload400.resultado.descripcionError,
      details: payload400
    },
    mensajeId,
    'CAMPO_OBLIGATORIO'
  );

  await logPayloadToDatabase(request.log, payload400, mensajeId, 'NOTIFICAR_PROCESO_LLAVE');

  return reply.code(config.httpStatus).send(payload400);
}

/**
 * Maneja errores de validación: valores inválidos (enum, pattern, type, etc.)
 */
async function handleValidacionCampoMalInformado(
  request: any,
  reply: any,
  mensajeId: string,
  fechaCreacion: string,
  fechaActualizacion: string
) {
  const config = NOTIFICAR_PROCESO_LLAVE_VALIDACION_LOCAL_CONFIG.CAMPO_MAL_INFORMADO;
  const validationError = new Error('Validación fallida: Valor inválido');

  const payload400 = {
    resultado: {
      mensajeId,
      error: true,
      codigo: config.codigoRta,
      descripcionError: config.descripcion,
      fechaCreacion,
      fechaActualizacion
    }
  };

  request.log.info({ mensajeId, codigo: payload400.resultado.codigo, payload: payload400 }, 'Respuesta enviada (campo mal informado)');

  logErrorToCSV(
    request.log,
    {
      ...validationError,
      code: 'CAMPO_MAL_INFORMADO',
      statusCode: config.httpStatus,
      message: payload400.resultado.descripcionError,
      details: payload400
    },
    mensajeId,
    'CAMPO_MAL_INFORMADO'
  );

  await logPayloadToDatabase(request.log, payload400, mensajeId, 'NOTIFICAR_PROCESO_LLAVE');

  return reply.code(config.httpStatus).send(payload400);
}

/**
 * Construye el objeto mappedData mapeando campos del body al formato SANBA
 */
function buildMappedDataForCore(body: any) {
  const nombrePjRaw = body.nombrePj ?? '';
  const nombrePj1 = (nombrePjRaw || '').toString().slice(0, 70);
  const nombrePj2 = (nombrePjRaw || '').toString().slice(70, 140) || '';

  const mensajeCausalRaw = body.mensajeCausal ?? '';
  const mc = (mensajeCausalRaw || '').toString();
  const mensajeCausal1 = mc.slice(0, 100);
  const mensajeCausal2 = mc.slice(100, 200);
  const mensajeCausal3 = mc.slice(200, 300);

  const tipoIdentificacionMapped = (body.tipoIdentificacion && (TIPO_IDENTIFICACION as any)[body.tipoIdentificacion]) || body.tipoIdentificacion;

  const rawMedio = (body.numeroMedioPago ?? '').toString();
  const entidadEnv = (process.env.API_SANBA_ENTIDAD || '').toString().padStart(4, '0');
  const sucursalEnv = (process.env.API_SANBA_SUCURSAL || '').toString().padStart(4, '0');
  let numeroMedioPagoFormateado = '';
  if (rawMedio) {
    const cuenta = rawMedio.replace(/\D/g, '');
    const cuenta12 = cuenta.slice(-12).padStart(12, '0');
    numeroMedioPagoFormateado = `${entidadEnv}${sucursalEnv}${cuenta12}`;
  }

  const nroidenRaw = (body.numeroIdentificacion ?? '').toString().trim();
  const nroidenPadded = nroidenRaw.padStart(11, '0');

  return {
    idProce: body.idProceso,
    idInter: body.idInterno,
    proceso: body.proceso,
    vallav: body.valorLlave,
    canal: body.canal,
    nombre: body.nombre ?? '',
    apellid: body.apellido ?? '',
    nombPj1: nombrePj1,
    nombPj2: nombrePj2,
    nroiden: formatIdentificacion(nroidenPadded),
    tipiden: tipoIdentificacionMapped,
    nroctap: numeroMedioPagoFormateado,
    edoproc: body.estadoProceso,
    causal: (() => {
      const raw = body.causal ?? '0';
      const n = parseInt(String(raw), 10);
      return Number.isNaN(n) ? 0 : n;
    })(),
    msjcau1: mensajeCausal1,
    msjcau2: mensajeCausal2,
    msjcau3: mensajeCausal3
  };
}

/**
 * Maneja error de configuración faltante
 */
async function handleConfigurationError(
  request: any,
  reply: any,
  mensajeId: string,
  fechaCreacion: string,
  fechaActualizacion: string
) {
  const configError = new Error('Configuración faltante para NotificarProcesoLlave (env var API_SANBA_RUTA_NOTIFICAR_PROCESO_LLAVE)');
  logErrorToCSV(
    request.log,
    {
      ...configError,
      code: 'INTERNAL_SERVER_ERROR',
      statusCode: 500
    },
    mensajeId,
    'CONFIGURATION_ERROR'
  );

  const codigoCfg = getErrorCodeNumber('INTERNAL_SERVER_ERROR');
  const httpStatusCfg = getHttpStatusForError('INTERNAL_SERVER_ERROR');

  const payloadCfg = {
    resultado: {
      mensajeId,
      error: true,
      codigo: codigoCfg,
      descripcionError: 'Configuración del servicio faltante: variable API_SANBA_RUTA_NOTIFICAR_PROCESO_LLAVE',
      fechaCreacion,
      fechaActualizacion
    }
  };

  request.log.error({ mensajeId, codigo: payloadCfg.resultado.codigo, payload: payloadCfg }, 'Respuesta enviada (config missing)');

  logErrorToCSV(
    request.log,
    {
      ...configError,
      code: 'INTERNAL_SERVER_ERROR',
      statusCode: 500,
      message: payloadCfg.resultado.descripcionError,
      details: payloadCfg
    },
    mensajeId,
    'CONFIGURATION_ERROR'
  );

  return reply.code(httpStatusCfg).send(payloadCfg);
}

/**
 * Construye el objeto de solicitud al core SANBA
 */
function buildCoreRequest(mappedData: any) {
  const rutaServicio = process.env.API_SANBA_RUTA_SERVICIO_LLAVE || 'procesoLlaveB';
  const rawSecuencia = Number(process.env.API_SANBA_SECUENCIA || '0');
  const secuencia = Number.isNaN(rawSecuencia) ? 0 : rawSecuencia;

  return {
    cabecera: {
      rutaServicio,
      sesion: {
        usuario: process.env.API_SANBA_USUARIO || '',
        terminal: process.env.API_SANBA_TERMINAL || '',
        horaConexion: new Date().toISOString(),
        entorno: process.env.API_SANBA_ENTORNO || '',
        perfil: process.env.API_SANBA_PERFIL || '',
        sucursal: process.env.API_SANBA_SUCURSAL || '',
        entidad: process.env.API_SANBA_ENTIDAD || '',
        diasRestantesCambioClave: process.env.API_SANBA_DIAS_RESTANTES_CAMBIO_CLAVE || '',
        fechaContable: process.env.API_SANBA_FECHA_CONTABLE || '',
        turno: process.env.API_SANBA_TURNO || ''
      },
      funcion: process.env.API_SANBA_FUNCION || '',
      secuencia: Number(process.env.API_SANBA_SECUENCIA || secuencia),
      canal: process.env.API_SANBA_CANAL || '60'
    },
    data: mappedData
  };
}

/**
 * Maneja errores de Altair en el array errores[] (try block)
 */
async function handleAltairErrorArrayNotificar(
  coreErrores: any[],
  request: any,
  reply: any,
  mensajeId: string,
  fechaCreacion: string,
  fechaActualizacion: string
) {
  const primerError = coreErrores[0];
  const codigoAltair = primerError.codigo;

  request.log.warn({
    mensajeId,
    codigoAltair,
    mensajeAltair: primerError.mensaje,
    erroresCompletos: coreErrores
  }, 'Error de validación de Altair detectado en errores[]');

  try {
    const altairError = new Error(primerError.mensaje || 'Error de Altair');
    (altairError as any).code = codigoAltair;
    (altairError as any).statusCode = NOTIFICAR_PROCESO_LLAVE_HTTP_STATUS_CONFIG.ERROR_NEGOCIO;
    (altairError as any).details = coreErrores;
    logErrorToCSV(request.log, altairError, mensajeId, 'ALTAIR_VALIDATION_ERROR');
  } catch (e) {
    request.log.error({ mensajeId, err: e }, 'No se pudo registrar el error de Altair en CSV');
  }

  const errorMapping = getNotificarProcesoLlaveAltairErrorMapping(codigoAltair);

  if (errorMapping) {
    const payloadAltairErr = {
      resultado: {
        mensajeId,
        error: true,
        codigo: errorMapping.codigoRta,
        descripcionError: errorMapping.descripcion,
        fechaCreacion,
        fechaActualizacion
      }
    };

    request.log.info({ mensajeId, codigo: payloadAltairErr.resultado.codigo, payload: payloadAltairErr }, 'Respuesta enviada (error Altair)');
    await logPayloadToDatabase(request.log, payloadAltairErr, mensajeId, 'NOTIFICAR_PROCESO_LLAVE');
    return reply.code(NOTIFICAR_PROCESO_LLAVE_HTTP_STATUS_CONFIG.ERROR_NEGOCIO).send(payloadAltairErr);
  }

  const payloadAltairGenerico = {
    resultado: {
      mensajeId,
      error: true,
      codigo: 1422,
      descripcionError: primerError.mensaje || 'Error de validación de Altair',
      fechaCreacion,
      fechaActualizacion
    }
  };

  request.log.info({ mensajeId, codigo: payloadAltairGenerico.resultado.codigo, payload: payloadAltairGenerico }, 'Respuesta enviada (error Altair sin mapeo)');
  await logPayloadToDatabase(request.log, payloadAltairGenerico, mensajeId, 'NOTIFICAR_PROCESO_LLAVE');
  return reply.code(NOTIFICAR_PROCESO_LLAVE_HTTP_STATUS_CONFIG.ERROR_NEGOCIO).send(payloadAltairGenerico);
}

/**
 * Construye y envía el payload de éxito, incluyendo notificación asíncrona
 */
async function buildSuccessPayloadNotificar(
  request: any,
  reply: any,
  body: any,
  mensajeId: string,
  fechaCreacion: string,
  fechaActualizacion: string,
  fechaCreacionNotificacion: string
) {
  const successPayload = {
    resultado: {
      mensajeId,
      error: false,
      codigo: 0,
      descripcionError: '',
      fechaCreacion,
      fechaActualizacion
    }
  };

  request.log.info({ mensajeId, codigo: successPayload.resultado.codigo, payload: successPayload }, 'Respuesta enviada (success)');

  await logPayloadToDatabase(request.log, successPayload, mensajeId, 'NOTIFICAR_PROCESO_LLAVE');

  if (process.env.ENABLE_ENVIO_NOTIFICACION === 'true') {
    request.log.info({ mensajeId }, 'Enviando notificación asíncrona');
    enviarNotificacionAsync(
      {
        tipoIdentificacion: body.tipoIdentificacion,
        identificacion: formatIdentificacion(body.numeroIdentificacion),
        llaveUsuario: body.valorLlave || '',
        tipoTransaccion: body.proceso,
        estadoTx: 'EXITOSA',
        identificadorTx: null,
        numeroMedioPago: body.numeroMedioPago || '',
        fechaHoraEjecucion: fechaCreacionNotificacion,
        Error: null,
        DescripcionError: null,
        monto: body.monto || 0
      },
      request.log,
      mensajeId
    );
  } else {
    request.log.info({ mensajeId }, 'Envío de notificación asíncrona deshabilitado por configuración');
  }

  return reply.code(200).send(successPayload);
}

/**
 * Maneja errores de Altair en catch block
 */
async function handleAltairErrorInCatchNotificar(
  coreErrores: any[],
  request: any,
  reply: any,
  mensajeId: string,
  fechaCreacion: string,
  fechaActualizacion: string
) {
  const primerError = coreErrores[0];
  const codigoAltair = primerError.codigo;

  request.log.warn({
    mensajeId,
    codigoAltair,
    mensajeAltair: primerError.mensaje,
    erroresCompletos: coreErrores
  }, 'Error de validación de Altair detectado en catch errores[]');

  try {
    const altairError = new Error(primerError.mensaje || 'Error de Altair');
    (altairError as any).code = codigoAltair;
    (altairError as any).statusCode = NOTIFICAR_PROCESO_LLAVE_HTTP_STATUS_CONFIG.ERROR_NEGOCIO;
    (altairError as any).details = coreErrores;
    logErrorToCSV(request.log, altairError, mensajeId, 'ALTAIR_VALIDATION_ERROR_CATCH');
  } catch (e) {
    request.log.error({ mensajeId, err: e }, 'No se pudo registrar el error de Altair en CSV');
  }

  const errorMapping = getNotificarProcesoLlaveAltairErrorMapping(codigoAltair);

  if (errorMapping) {
    const payloadAltairErr = {
      resultado: {
        mensajeId,
        error: true,
        codigo: errorMapping.codigoRta,
        descripcionError: errorMapping.descripcion,
        fechaCreacion,
        fechaActualizacion
      }
    };

    request.log.info({ mensajeId, codigo: payloadAltairErr.resultado.codigo, payload: payloadAltairErr }, 'Respuesta enviada (error Altair desde catch)');
    await logPayloadToDatabase(request.log, payloadAltairErr, mensajeId, 'NOTIFICAR_PROCESO_LLAVE');
    return reply.code(NOTIFICAR_PROCESO_LLAVE_HTTP_STATUS_CONFIG.ERROR_NEGOCIO).send(payloadAltairErr);
  }

  const payloadAltairGenerico = {
    resultado: {
      mensajeId,
      error: true,
      codigo: 1422,
      descripcionError: primerError.mensaje || 'Error de validación de Altair',
      fechaCreacion,
      fechaActualizacion
    }
  };

  request.log.info({ mensajeId, codigo: payloadAltairGenerico.resultado.codigo, payload: payloadAltairGenerico }, 'Respuesta enviada (error Altair sin mapeo desde catch)');
  await logPayloadToDatabase(request.log, payloadAltairGenerico, mensajeId, 'NOTIFICAR_PROCESO_LLAVE');
  return reply.code(NOTIFICAR_PROCESO_LLAVE_HTTP_STATUS_CONFIG.ERROR_NEGOCIO).send(payloadAltairGenerico);
}

/**
 * Maneja respuestas de error del core (DESCO, observaciones, etc.)
 */
async function handleCoreErrorResponse(
  err: any,
  coreResp: any,
  coreErrores: any[],
  request: any,
  reply: any,
  mensajeId: string,
  fechaCreacion: string,
  fechaActualizacion: string
) {
  const coreCab = coreResp.cabecera || {};
  const resultadoCore = (coreCab.resultado || (coreResp.ok === false ? 'Error' : 'OK')).toString();
  const isError = /error|fail/i.test(resultadoCore) || coreResp.ok === false || coreErrores.length > 0;

  let mappedErrorKey = 'CORE_COMMUNICATION_ERROR';
  const firstErr = coreErrores[0];
  if (firstErr && firstErr.codigo) {
    const c = (firstErr.codigo || '').toString();
    if (c === 'PEE0001' || c === 'B50B_PE_PERSONA_INEXISTENTE') {
      mappedErrorKey = 'CLIENT_NOT_FOUND';
    } else if (c === 'QCE0000') {
      mappedErrorKey = 'EXTERNAL_API_ERROR';
    } else {
    }
  }

  const partes: string[] = [];

  if (Array.isArray(coreErrores) && coreErrores.length > 0) {
    for (const e of coreErrores) {
      const c = e && e.codigo ? String(e.codigo) : '';
      const m = e && e.mensaje ? String(e.mensaje) : '';
      if (c || m) {
        partes.push([c, m].filter(Boolean).join(': '));
      }
    }
  }

  const desco: string[] = [];
  for (const k of Object.keys(coreResp)) {
    if (/^DESCO0?\d$/i.test(k)) {
      const v = coreResp[k];
      if (v) {
        desco.push(String(v));
      }
    }
  }
  if (desco.length) {
    partes.push(desco.join(' '));
  }

  if (coreResp.observaciones) {
    if (Array.isArray(coreResp.observaciones)) {
      partes.push(coreResp.observaciones.filter(Boolean).map(String).join(' '));
    } else if (typeof coreResp.observaciones === 'string') {
      partes.push(coreResp.observaciones);
    } else {
      partes.push(String(coreResp.observaciones));
    }
  }

  if (partes.length === 0) {
    if (firstErr && firstErr.mensaje) {
      partes.push([firstErr.codigo || '', firstErr.mensaje].filter(Boolean).join(': '));
    } else {
      partes.push(getErrorDescription(mappedErrorKey));
    }
  }

  const descripcionError = partes.join(' | ');
  const codigo = getErrorCodeNumber(mappedErrorKey);
  const httpStatus = getHttpStatusForError(mappedErrorKey);

  logErrorToCSV(
    request.log,
    {
      ...err,
      message: descripcionError,
      code: firstErr?.codigo || mappedErrorKey,
      statusCode: httpStatus,
      details: coreResp
    },
    mensajeId,
    mappedErrorKey
  );

  const descripcionParaRespuesta = String(descripcionError).slice(0, 255);
  const payloadCoreErr = {
    resultado: {
      mensajeId,
      error: !!isError,
      codigo,
      descripcionError: descripcionParaRespuesta,
      fechaCreacion,
      fechaActualizacion
    }
  };

  request.log.error({ mensajeId, codigo: payloadCoreErr.resultado.codigo, payload: payloadCoreErr }, 'Respuesta enviada (core error)');

  logErrorToCSV(
    request.log,
    {
      ...err,
      message: descripcionParaRespuesta,
      code: firstErr?.codigo || mappedErrorKey,
      statusCode: httpStatus,
      details: payloadCoreErr
    },
    mensajeId,
    mappedErrorKey
  );

  return reply.code(httpStatus).send(payloadCoreErr);
}

/**
 * Maneja errores genéricos (fallback)
 */
async function handleGenericErrorNotificar(
  err: any,
  request: any,
  reply: any,
  mensajeId: string,
  fechaCreacion: string,
  fechaActualizacion: string
) {
  const errorKey = 'CORE_COMMUNICATION_ERROR';
  const codigo = getErrorCodeNumber(errorKey);
  const httpStatus = getHttpStatusForError(errorKey);
  const descripcion = getErrorDescription(errorKey);

  const payloadGenericErr = {
    resultado: {
      mensajeId,
      error: true,
      codigo,
      descripcionError: String(descripcion).slice(0, 255),
      fechaCreacion,
      fechaActualizacion
    }
  };

  request.log.error({ mensajeId, codigo: payloadGenericErr.resultado.codigo, payload: payloadGenericErr }, 'Respuesta enviada (generic error)');

  logErrorToCSV(
    request.log,
    {
      ...err,
      message: payloadGenericErr.resultado.descripcionError,
      statusCode: httpStatus,
      details: payloadGenericErr
    },
    mensajeId,
    errorKey
  );

  return reply.code(httpStatus).send(payloadGenericErr);
}

const notificarProcesoLlave: FastifyPluginAsync = async (fastify) => {
  fastify.post(
    '/NotificarProcesoLlave',
    {
      schema: {
        body: NotificarProcesoLlaveBodySchema,
        response: {
          200: NotificarProcesoLlaveResponseSchema,
          400: ErrorResponseSchema,
          500: ErrorResponseSchema
        }
      },
      attachValidation: true
    },
    async (request: FastifyRequest, reply: FastifyReply) => {
      const mensajeId = request.mensajeId ?? '';
      const fechaCreacion = formatSanbaTimestamp(new Date());
      const fechaCreacionNotificacion = formatNotificacionTimestamp(new Date());
      const fechaActualizacion = formatSanbaTimestamp(new Date());

      if ((request as any).validationError) {
        const validationErrors = (request as any).validationError?.validation || [];

        // PRIORIDAD 1: Detectar campos mal escritos o no permitidos (additionalProperties)
        const camposMalEscritos = validationErrors.filter((err: any) =>
          err.keyword === 'additionalProperties'
        );

        if (camposMalEscritos.length > 0) {
          return await handleValidacionCampoIncorrecto(request, reply, mensajeId, fechaCreacion, fechaActualizacion);
        }

        // PRIORIDAD 2: Detectar campos obligatorios faltantes (required)
        const camposObligatorios = validationErrors.filter((err: any) =>
          err.keyword === 'required'
        );

        if (camposObligatorios.length > 0) {
          return await handleValidacionCampoObligatorio(request, reply, mensajeId, fechaCreacion, fechaActualizacion);
        }

        // PRIORIDAD 3: Valores inválidos (enum, pattern, type, etc.) - CAMPO_MAL_INFORMADO
        return await handleValidacionCampoMalInformado(request, reply, mensajeId, fechaCreacion, fechaActualizacion);
      }

      try {
        const body = (request.body as any).Proceso;

        request.log.info({ proceso: body, mensajeId }, 'NotificarProcesoLlave recibido');

        const mappedData = buildMappedDataForCore(body);

        // Build core request
        const apiConfig = getApiConfig('sanbaApi');
        const httpClient = createHttpClient({ baseURL: apiConfig.baseURL, timeout: apiConfig.timeout, rejectUnauthorized: apiConfig.rejectUnauthorized });

        const rutaHttp = process.env.API_SANBA_RUTA_NOTIFICAR_PROCESO_LLAVE;

        if (!rutaHttp) {
          return await handleConfigurationError(request, reply, mensajeId, fechaCreacion, fechaActualizacion);
        }

        const coreRequest = buildCoreRequest(mappedData);

        // Log the mapped request at info level temporarily to troubleshoot core payload
        request.log.info({ coreRequest, mensajeId }, 'Cuerpo mapeado para core');

        // Send to core
        const response = await httpClient.post(rutaHttp, coreRequest, {
          headers: { mqRoute: process.env.API_SANBA_MQ_ROUTE || 'QCTFD' }
        });

        request.log.info({ status: response.status, data: response.data, mensajeId }, 'Respuesta desde core');

        // PRIORIDAD 1: Verificar errores de validación en el array errores[] (códigos BGE de Altair)
        const coreResp = response?.data || {};
        const coreErrores = Array.isArray(coreResp.errores) ? coreResp.errores : [];

        if (coreErrores.length > 0) {
          return await handleAltairErrorArrayNotificar(coreErrores, request, reply, mensajeId, fechaCreacion, fechaActualizacion);
        }

        return await buildSuccessPayloadNotificar(request, reply, body, mensajeId, fechaCreacion, fechaActualizacion, fechaCreacionNotificacion);
      } catch (err: any) {
        // If the error contains a response from core, try to map it to our error envelope
        const coreResp = err?.data || err?.response?.data;

        if (coreResp && typeof coreResp === 'object') {
          const coreCab = coreResp.cabecera || {};
          const coreErrores = Array.isArray(coreResp.errores) ? coreResp.errores : [];

          // PRIORIDAD 1: Verificar si hay errores de Altair mapeables en errores[]
          if (coreErrores.length > 0) {
            return await handleAltairErrorInCatchNotificar(coreErrores, request, reply, mensajeId, fechaCreacion, fechaActualizacion);
          }

          return await handleCoreErrorResponse(err, coreResp, coreErrores, request, reply, mensajeId, fechaCreacion, fechaActualizacion);
        }

        return await handleGenericErrorNotificar(err, request, reply, mensajeId, fechaCreacion, fechaActualizacion);
      }
    }
  );
};
export { formatIdentificacion, formatNumPago };
export default notificarProcesoLlave;



import { Type } from '@sinclair/typebox';

const TIPOS_IDENTIFICACION_ENUM = ['CC', 'CE', 'PA', 'TI', 'NUIP', 'NIT', 'OTR'] as const;
const PROCESOS_ENUM = ['REGISTRO', 'MODIFICACION', 'CANCELACION', 'BLOQUEO', 'REACTIVACION'] as const;
const ESTADO_PROCESO_ENUM = ['EXITOSO', 'RECHAZADO', 'ERROR'] as const;
const CANAL_ENUM = ['RED', 'APP', 'ATM', 'WEB', 'CRL', 'OFC', 'AUT'] as const;

export const NotificarProcesoLlaveBodySchema = Type.Object({
  Proceso: Type.Object({
    idProceso: Type.String({
      minLength: 1,
      maxLength: 50,
      description: 'Identificador del proceso en el SPBVI',
      pattern: '^[A-Za-z0-9\-]{1,50}$'
    }),
    idInterno: Type.String({
      minLength: 1,
      maxLength: 50,
      description: 'Identificador del proceso en el directorio local',
      pattern: '^[A-Za-z0-9\-]{1,50}$'
    }),
    proceso: Type.String({
      enum: PROCESOS_ENUM,
      description: 'Proceso notificado: REGISTRO, MODIFICACION, CANCELACION, BLOQUEO, REACTIVACION',
      pattern: '^(REGISTRO|MODIFICACION|CANCELACION|BLOQUEO|REACTIVACION)$'
    }),
    valorLlave: Type.String({
      minLength: 1,
      maxLength: 100,
      description: 'Valor de la llave sobre la que se realizó el proceso',
      pattern: '^.{1,100}$'
    }),
    canal: Type.String({
      enum: CANAL_ENUM,
      description: 'Tipo de canal por donde se originó la transacción. Valores permitidos: RED (Redes Aliadas), APP (Teléfono Móvil), ATM (Cajero Automático), WEB (Canal de Internet), CRL (Corresponsal), OFC (Oficina), AUT (Proceso automático)',
      pattern: '^(RED|APP|ATM|WEB|CRL|OFC|AUT)$'
    }),
    nombre: Type.String({
      minLength: 1,
      maxLength: 64,
      description: 'Nombre del cliente que origina la transacción',
      pattern: '^[A-Za-z0-9 \-\.\,]{1,64}$'
    }),
    apellido: Type.String({
      minLength: 1,
      maxLength: 64,
      description: 'Apellido del cliente que origina la transacción',
      pattern: '^[A-Za-z0-9 \-\.\,]{1,64}$'
    }),
    nombrePj: Type.Optional(
      Type.Union([
        Type.Null(),
        Type.String({
          minLength: 1,
          maxLength: 140,
          description: 'Nombre del comercio o persona jurídica. Si el tipo de usuario es PN, este valor debe ser nulo',
          pattern: '^[A-Za-z0-9 \-\.\,]{1,140}$'
        })
      ])
    ),
    numeroIdentificacion: Type.String({
      minLength: 4,
      maxLength: 11,
      description: 'Número de identificación del cliente que origina la transacción (11 caracteres, ceros a la izquierda)',
      pattern: '^[0-9]{4,11}$'
    }),
    tipoIdentificacion: Type.String({
      enum: TIPOS_IDENTIFICACION_ENUM,
      description: 'Tipo de identificación del cliente que origina la transacción. Valores permitidos: CC (Cédula de ciudadanía), CE (Cédula de extranjería), NUIP (Número Único de Identificación Personal), PPT (Permiso de Protección Temporal), NIT (Número de Identificación Tributaria), PEP (Permiso Especial de Permanencia), PAS (Número de Pasaporte), TDI (Tarjeta de identidad)',
      pattern: '^(CC|CE|PA|TI|NUIP|NIT|OTR)$'
    }),
    numeroMedioPago: Type.Optional(
      Type.String({
        minLength: 9,
        maxLength: 12,
        description: 'Número de la cuenta o medio de pago asociado con la llave (mínimo 9 dígitos, máximo 12)',
        pattern: '^[0-9]{9,12}$'
      })
    ),
    estadoProceso: Type.String({
      enum: ESTADO_PROCESO_ENUM,
      description: 'Estado final del proceso: EXITIOSO, RECHAZADO, ERROR',
      pattern: '^(EXITOSO|RECHAZADO|ERROR)$'
    }),
    causal: Type.Integer({
      minimum: 0,
      maximum: 9999999999,
      description: 'Causal del rechazo (número entre 0 y 9999999999). Valor típico: 0 para operaciones exitosas'
    }),
    mensajeCausal: Type.String({
      minLength: 1,
      maxLength: 300,
      description: 'Descripción de la causal. "Success" para transacciones exitosas',
      pattern: '^.{1,300}$'
    })
  }, { additionalProperties: false })
}, { additionalProperties: false });

export const NotificarProcesoLlaveResponseSchema = Type.Object({
  resultado: Type.Object({
    mensajeId: Type.String({ format: 'uuid' }),
    error: Type.Boolean(),
    codigo: Type.Integer(),
    descripcionError: Type.Optional(Type.String()),
    fechaCreacion: Type.String({ format: 'date-time' }),
    fechaActualizacion: Type.Optional(Type.String({ format: 'date-time' }))
  })
}, { additionalProperties: false });

export const ErrorResponseSchema = Type.Object({
  resultado: Type.Object({
    mensajeId: Type.String({ format: 'uuid' }),
    error: Type.Boolean(),
    codigo: Type.Integer(),
    descripcionError: Type.String(),
    fechaCreacion: Type.String({ format: 'date-time' }),
    fechaActualizacion: Type.Optional(Type.String({ format: 'date-time' }))
  })
}, { additionalProperties: false });



import { type FastifyPluginAsync } from 'fastify';
import fp from 'fastify-plugin';

import consultarDetalleClienteRouter from './ConsultarDetalleCliente';
import notificarProcesoLlaveRouter from './NotificarProcesoLlave';

/**
 * Auto-register all application routes
 *
 * @remarks
 * This plugin follows Fastify v5 best practices:
 * - Uses fastify-plugin to avoid unnecessary encapsulation
 * - Registers all routes with a common /api prefix
 * - Uses FastifyPluginAsync for async/await pattern
 * - Maintains proper plugin encapsulation for each router
 *
 * @see {@link https://fastify.dev/docs/latest/Reference/Plugins/ | Fastify Plugins Documentation}
 */
const autoloadRoutes: FastifyPluginAsync = async (fastify) => {
  fastify.log.info('Registrando rutas de la aplicación...');

  // Register ConsultarDetalleCliente routes
  await fastify.register(consultarDetalleClienteRouter, {
    prefix: '/api',
    logLevel: 'info'
  });

  // Register NotificarProcesoLlave routes
  await fastify.register(notificarProcesoLlaveRouter, {
    prefix: '/api',
    logLevel: 'info'
  });

  fastify.log.info('Todas las rutas registradas correctamente');
};

/**
 * Export plugin wrapped with fastify-plugin
 * This ensures decorators and hooks are accessible in parent scope
 */
export default fp(autoloadRoutes, {
  name: 'app-routes',
  fastify: '>=4.x <6.x'
});
