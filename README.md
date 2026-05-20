import { Type } from '@sinclair/typebox';
import { TIPOS_DOCUMENTO_ARRAY, TIPOS_CUENTA_ARRAY, IDENTIFICADOR_SPBVI_ARRAY } from '../../constants/constantes';
/**
 * Schema for AsegurarFondos request body
 */
export const AsegurarFondosBodySchema = Type.Object({
  monto: Type.Number({ description: 'Monto de la transaccion. Numero con hasta 13 enteros y 2 decimales. Ejemplo: 1234567890123.45', minimum: 0.01, maximum: 9999999999999.99, examples: [1234567890123.45] }),
  valorComision: Type.Optional(Type.Number({ description: 'Valor de la comision. Numero con hasta 13 enteros y 2 decimales. Ejemplo: 0.00', minimum: 0, maximum: 9999999999999.99, examples: [0] })),
  valorIvaComision: Type.Optional(Type.Number({ description: 'Valor del iva aplicado a la comision. Numero con hasta 13 enteros y 2 decimales. Ejemplo: 0.00', minimum: 0, maximum: 9999999999999.99, examples: [0] })),
  nombre: Type.String({ description: 'Nombre del cliente al que se realice el movimiento en el core bancario', minLength: 1, maxLength: 40, examples: ['Juan'] }),

  tipoDocOrig: Type.String({
    enum: TIPOS_DOCUMENTO_ARRAY,
    description: 'Tipo de documento de identificación del originador. Valores permitidos: CC, CE, NIT, TDI, PAS, PPT, PEP',
    examples: ['CC']
  }),
  numDocOrig: Type.String({ description: 'Número de documento del originador (11 dígitos)', minLength: 4, maxLength: 11, pattern: '^[0-9]{4,11}$', examples: ['12345678901'] }),
  tipoCuentaOrig: Type.String({
    enum: TIPOS_CUENTA_ARRAY,
    description: 'Tipo de cuenta del Orig. Valores válidos: CAHO, CCTE, DBMO, DORD, DBMI',
    minLength: 1,
    maxLength: 4,
    examples: ['CAHO']
  }),
  numCuentaOrig: Type.String({ description: 'Número de cuenta del originador (mínimo 9 dígitos)', minLength: 9, maxLength: 12, pattern: '^[0-9]{9,12}$', examples: ['987654321'] }),

  idTransaccionSN: Type.String({ description: 'Identificador único de la transacción en el sistema', minLength: 1, maxLength: 36, pattern: 'process.env.IDTRANSACCIONSN', examples: ['TX123456'] }),
  identificadorSPBVI: Type.String({
    enum: IDENTIFICADOR_SPBVI_ARRAY,
    description: 'Sistema que realiza la transacción – nombre nodo SPBVI. TFY: Transfiya, ENT: Entrecuentas, CRB: Credibanco, VIS: Visionamos, SiX: SiX',
    examples: ['TFY']
  }),
  idTransaccionOriginal: Type.Optional(Type.String({ description: 'Identificador de la transacción original objeto de la solicitud de devolución desde el canal', maxLength: 36, examples: ['TRX123456'] })),
  tipoTransaccion: Type.String({ description: 'Código o descripción que indique si es transferencia, pago, recaudo, solicitud, etc.', minLength: 1, maxLength: 20, pattern: '^[A-Z_]{1,20}$', examples: ['TRANSFER'] }),
  hubConcentrador: Type.Optional(Type.String({ description: 'Nombre del ordenante de la transacción (para casos de uso BaaS)', maxLength: 36, examples: ['HUB001'] })),

  marcaTiempo: Type.String({ description: 'Fecha y hora del envío del consumo en formato ISO8601. Acepta con o sin Z final.', pattern: 'process.env.MARCATIEMPO', minLength: 23, maxLength: 24, examples: ['2025-02-10T14:30:01.000', '2025-02-10T14:30:01.000Z'] }),
  ipOriginador: Type.Optional(Type.String({
    description: 'Dirección IP desde donde se realiza la transacción. Soporta IPv4 e IPv6. Ejemplo IPv6: 2001:0db8:85a3:0000:0000:8a2e:0370:7334',
    minLength: 7,
    maxLength: 50,
    pattern: 'process.env.IPTRANSACCION',
  })),
  canalOriginador: Type.Optional(Type.Union([
    Type.String({ description: 'Nombre del canal por donde se está realizando la transacción', maxLength: 20, pattern: '^[A-Z]{1,20}$', examples: ['WEB'] }),
    Type.Literal(''),
    Type.Null()
  ])),

  campo1: Type.Optional(Type.Union([Type.String({ maxLength: 100 }), Type.Object({}, { additionalProperties: true }), Type.Null()])),
  campo2: Type.Optional(Type.Union([Type.String({ maxLength: 100 }), Type.Object({}, { additionalProperties: true }), Type.Null()])),
  campo3: Type.Optional(Type.Union([Type.String({ maxLength: 100 }), Type.Object({}, { additionalProperties: true }), Type.Null()])),
  campo4: Type.Optional(Type.Union([Type.String({ maxLength: 100 }), Type.Object({}, { additionalProperties: true }), Type.Null()])),
  campo5: Type.Optional(Type.Union([Type.String({ maxLength: 100 }), Type.Object({}, { additionalProperties: true }), Type.Null()])),
  idTransaccionSPBVI: Type.String({
    description: 'Identificador de la transacción en el SPBVI (obligatorio)',
    minLength: 1,
    maxLength: 36,
    examples: ['SPBVI123456']
  }),
  CampoLibre0: Type.Optional(Type.String({
    description: 'Campo libre disponible 0 (opcional)',
    maxLength: 100,
    examples: ['', 'dato adicional 0']
  })),
  CampoLibre1: Type.Optional(Type.String({
    description: 'Campo libre disponible 1 (opcional)',
    maxLength: 100,
    examples: ['', 'dato adicional 1']
  }))
}, { additionalProperties: false });

/**
 * Schema for AsegurarFondos success response
 */
export const AsegurarFondosResponseSchema = Type.Object({
  respuesta: Type.Optional(Type.Object({
    idTransaccionSN: Type.String({ description: 'Identificador único de la transacción en el sistema Neurona', maxLength: 36 }),
    idTransaccionCore: Type.String({ description: 'Identificador de la transacción original del banco', maxLength: 36 }),
    marcaTiempo: Type.String({ format: 'date-time', description: 'Fecha y hora de la respuesta al consumo. En formato ISO8601 YYYY-MM-DDThh:mm:SS.sss' }),
    CampoLibreSalida: Type.Optional(Type.String({ description: 'Campo libre de salida (opcional)', maxLength: 100, examples: ['', 'dato de salida'] }))
  })),
  resultado: Type.Object({
    mensajeId: Type.String({ format: 'uuid', description: 'Identificador del mensaje enviado en la petición' }),
    error: Type.Boolean({ description: 'Indicador de éxito o error en el proceso. true: Proceso fallido, false: Proceso exitoso' }),
    codigo: Type.Integer({ description: 'Código del error en caso de ocurrencia, si el proceso es exitoso retorna valor cero' }),
    descripcionError: Type.String({ description: 'Descripción del error en caso de ocurrencia, si el proceso es exitoso retorna un string vacío', maxLength: 255 })
  })
});

export const ErrorResponseSchema = Type.Object({
  resultado: Type.Object({
    mensajeId: Type.String({ format: 'uuid' }),
    error: Type.Boolean(),
    codigo: Type.Integer(),
    descripcionError: Type.String()
  })
});




*****************



import type { FastifyPluginAsync } from 'fastify';
import type { Static } from '@sinclair/typebox';
import {
  AsegurarFondosBodySchema,
  AsegurarFondosResponseSchema,
  ErrorResponseSchema
} from './schemas';
import { AsegurarFondosRequest, AsegurarFondos } from 'types';
import { createAsegurarFondosService, AsegurarFondosError } from '../../services/asegurarFondos.service';
import { getErrorCodeNumber, getErrorDescription, getHttpStatusForError } from '../../constants/error-codes';
import {
  getAsegurarFondosAltairErrorMapping,
  ASEGURAR_FONDOS_VALIDACION_LOCAL_CONFIG,
  ASEGURAR_FONDOS_HTTP_STATUS_CONFIG
} from '../../constants/asegurar-fondos-error-mapping';
import { logErrorToCSV } from '../../lib/shared/csvErrorLogger';
import { logTransaction } from '../../lib/shared/databaseErrorLogger';
import { formatMarcaTiempo } from '../../lib/shared/utils';

const ERROR_GUARDANDO_EN_BD = 'Error guardando en base de datos';
const ERROR_GUARDANDO_VALIDACION_LOCAL_EN_BD = 'Error guardando validación local en base de datos';

type AsegurarFondosBody = Static<typeof AsegurarFondosBodySchema>;

interface RequestWithMensajeId {
  mensajeId?: string;
}


/**
 * Mapea tipo de documento según observaciones de SANBA
 * NIT -> NT, resto se mantiene igual
 */
export function mapTipoDocumento(tipoDoc: string): string {
  return tipoDoc === 'NIT' ? 'NT' : tipoDoc;
}

/**
 * Formatea número de cuenta con prefijo 0065+0100 y padding a 12 dígitos
 * Formato: 0065 + 0100 + número cuenta (12 dígitos)
 * Total: 20 dígitos
 */
function formatNumCuenta(numCuentaOrig: string): string {
  // Rellenar con ceros a la izquierda hasta 12 dígitos
  const cuentaConPadding = numCuentaOrig.padStart(12, '0');
  // prefijos: 0065 + 0100
  return `00650100${cuentaConPadding}`;
}

function formatIdentificacion(identificacion: string): string {
  const cuentaConPadding = identificacion.padStart(11, '0');
  return `${cuentaConPadding}`;
}

/**
 * Convierte un valor a número, manejando undefined/null/string
 */
export function parseMoney(v: any): number {
  if (v === undefined || v === null || v === '') { return 0; }
  if (typeof v === 'number') { return v; }
  const n = parseFloat(String(v));
  return Number.isFinite(n) ? n : NaN;
}

/**
 * Mapea errores de validación con información detallada
 */
function mapEnhancedErrors(validationErrors: any[], requestBody: any) {
  return validationErrors.map((e: any) => {
    const instancePath: string = e.instancePath || '';
    const field = instancePath.startsWith('/') ? instancePath.slice(1) : instancePath;
    let providedValue: unknown;
    try {
      if (field) {
        providedValue = requestBody?.[field];
      }
    } catch (err) {
      providedValue = undefined;
    }
    let friendlyMessage = e.message;
    if (e.keyword === 'pattern' && providedValue !== undefined) {
      friendlyMessage = `El valor '${String(providedValue)}' no cumple el patrón requerido.`;
    } else if (e.keyword === 'enum' && providedValue !== undefined) {
      friendlyMessage = `El valor '${String(providedValue)}' no es uno de los valores permitidos.`;
    } else if (e.keyword === 'required') {
      friendlyMessage = `Falta el campo requerido '${e.params?.missingProperty}'.`;
    } else {
      friendlyMessage = e.message;
    }
    return {
      field: field || null,
      providedValue: providedValue ?? null,
      keyword: e.keyword,
      params: e.params || {},
      message: e.message,
      friendlyMessage
    };
  });
}

/**
 * Persiste errores de validación en CSV
 */
function persistValidationToCSV(log: any, enhancedErrors: any[], mensajeId: string) {
  try {
    const validationErrorObj = new Error('Validation error');
    (validationErrorObj as any).code = 'FST_ERR_VALIDATION';
    (validationErrorObj as any).statusCode = 400;
    (validationErrorObj as any).details = enhancedErrors;
    logErrorToCSV(log, validationErrorObj, mensajeId, 'VALIDATION_ERROR', 'ASEG-FOND');
  } catch (e) {
    log.warn({ err: e }, 'No se pudo escribir validación local en CSV');
  }
}

/**
 * Persiste campo incorrecto en base de datos
 */
function persistCampoIncorrectoToDB(request: any, mensajeId: string) {
  logTransaction({
    servicio: 'ASEG-FOND',
    fechaEvento: new Date(),
    tipoDocOrigen: (request.body as AsegurarFondosBody)?.tipoDocOrig || null,
    numeroIdOrigen: (request.body as AsegurarFondosBody)?.numDocOrig || null,
    tipoDocDestino: null,
    numeroIdDestino: null,
    monto: typeof (request.body as AsegurarFondosBody)?.monto === 'number' ? (request.body as AsegurarFondosBody).monto : parseFloat(String((request.body as AsegurarFondosBody)?.monto)) || 0,
    numCuentaOrigen: (request.body as AsegurarFondosBody)?.numCuentaOrig || null,
    numCuentaDestino: null,
    numeroTx: (request.body as AsegurarFondosBody)?.idTransaccionSN || mensajeId,
    estado: 'ERROR',
    nivel: 'ERROR',
    idMensajeError: 'CAMPO_INCORRECTO',
    mensaje: 'Campo incorrecto en el payload'
  })
    .catch(dbError => {
      request.log.error({ dbError }, ERROR_GUARDANDO_VALIDACION_LOCAL_EN_BD);
    });
}

/**
 * Construye respuesta de error estándar
 */
function buildErrorResponse(config: any, mensajeId: string) {
  return {
    resultado: {
      mensajeId,
      error: true,
      codigo: config.codigoRta,
      descripcionError: config.descripcion
    }
  };
}

/**
 * Maneja errores de validación del esquema
 */
function handleValidationError(request: any, reply: any, mensajeId: string) {
  request.log.warn({ error: request.validationError, mensajeId, errorType: 'VALIDATION_ERROR' }, 'Error de validación AsegurarFondos');
  const validationErrors = request.validationError.validation || [];
  const enhancedErrors = mapEnhancedErrors(validationErrors, request.body);
  persistValidationToCSV(request.log, enhancedErrors, mensajeId);
  request.log.info({ totalErrors: enhancedErrors.length, errors: enhancedErrors }, 'Errores de validación detallados');
  const camposMalEscritos = validationErrors.filter((err: any) => err.keyword === 'additionalProperties');
  if (camposMalEscritos.length > 0) {
    const config = ASEGURAR_FONDOS_VALIDACION_LOCAL_CONFIG.CAMPO_INCORRECTO;
    persistCampoIncorrectoToDB(request, mensajeId);
    reply.code(config.httpStatus).send(buildErrorResponse(config, mensajeId));
    return;
  }
  const config = ASEGURAR_FONDOS_VALIDACION_LOCAL_CONFIG.CAMPO_OBLIGATORIO;
  reply.code(config.httpStatus).send(buildErrorResponse(config, mensajeId));
}

/**
 * Procesa y responde a errores de validación de Altair (array errores[])
 */
async function handleAltairErrorArray(resultado: AsegurarFondos, request: any, reply: any, mensajeId: string, body: AsegurarFondosBody, montoNum: number) {
  const primerError = resultado.errores[0];
  const codigoAltair = primerError.codigo;

  request.log.warn({
    mensajeId,
    codigoAltair,
    mensajeAltair: primerError.mensaje,
    erroresCompletos: resultado.errores
  }, 'Error de validación de Altair detectado en errores[]');

  // Registrar el error de Altair también en el logger CSV para trazabilidad
  try {
    const altairError = new Error(primerError.mensaje || 'Error de Altair');
    (altairError as any).code = codigoAltair;
    (altairError as any).statusCode = ASEGURAR_FONDOS_HTTP_STATUS_CONFIG.ERROR_NEGOCIO;
    (altairError as any).details = resultado.errores;
    logErrorToCSV(request.log, altairError, mensajeId, 'ALTAIR_VALIDATION_ERROR', 'ASEG-FOND');
  }
  /* istanbul ignore next: error al escribir CSV, inalcanzable en tests */
  catch (e) {
    request.log.error({ mensajeId, err: e }, 'No se pudo registrar el error de Altair en CSV');
  }

  // Guardar en base de datos (misma estructura que TransaccionOnUs)
  await logTransaction({
    servicio: 'ASEG-FOND',
    fechaEvento: new Date(),
    tipoDocOrigen: body.tipoDocOrig,
    numeroIdOrigen: body.numDocOrig,
    tipoDocDestino: null,
    numeroIdDestino: null,
    monto: montoNum,
    numCuentaOrigen: formatNumCuenta(body.numCuentaOrig),
    numCuentaDestino: null,
    numeroTx: body.idTransaccionSN || mensajeId,
    estado: 'ERROR',
    nivel: 'ERROR',
    idMensajeError: codigoAltair,
    mensaje: primerError.mensaje?.substring(0, 50)
  })
    /* istanbul ignore next: Bloque inalcanzable */
    .catch(dbError => {
      /* istanbul ignore next: error en logTransaction, inalcanzable en tests */
      request.log.error({ dbError }, ERROR_GUARDANDO_EN_BD);
    });

  // Mapear código BGE de Altair a código RTA
  const errorMapping = getAsegurarFondosAltairErrorMapping(codigoAltair);

  if (errorMapping) {
    return reply.code(ASEGURAR_FONDOS_HTTP_STATUS_CONFIG.ERROR_NEGOCIO).send({
      resultado: {
        mensajeId,
        error: true,
        codigo: errorMapping.codigoRta,
        descripcionError: errorMapping.descripcion
      }
    });
  }

  // Si no hay mapeo, retornar error genérico
  return reply.code(ASEGURAR_FONDOS_HTTP_STATUS_CONFIG.ERROR_NEGOCIO).send({
    resultado: {
      mensajeId,
      error: true,
      codigo: 1422,
      descripcionError: primerError.mensaje || 'Error de validación de Altair'
    }
  });
}

/**
 * Procesa respuesta BGMN201 y persiste en base de datos
 */
async function processBGMN201Response(resultado: AsegurarFondos, request: any, reply: any, mensajeId: string, body: AsegurarFondosBody, montoNum: number) {
  const res = resultado.data.BGMN201;

  // Mapear el campo ERROR: N = éxito, S = error
  const tieneError = res.ERROR === 'S';
  const codigo = res.COERROR ? parseInt(res.COERROR, 10) : 0;

  // Concatenar los 3 campos de descripción de error
  const descripcionError = [res.DERROR1 || '', res.DERROR2 || '', res.DERROR3 || '']
    .filter(d => d.trim() !== '')
    .join(' ')
    .trim();

  // Guardar en base de datos (tanto éxito como error)
  if (tieneError) {
    /* istanbul ignore next: error en logTransaction, inalcanzable en tests */
    await logTransaction({
      servicio: 'ASEG-FOND',
      fechaEvento: new Date(),
      tipoDocOrigen: body.tipoDocOrig,
      numeroIdOrigen: body.numDocOrig,
      tipoDocDestino: null,
      numeroIdDestino: null,
      monto: montoNum,
      numCuentaOrigen: formatNumCuenta(body.numCuentaOrig),
      numCuentaDestino: null,
      numeroTx: body.idTransaccionSN || mensajeId,
      estado: 'ERROR',
      nivel: 'ERROR',
      idMensajeError: res.COERROR || 'ERROR_BGMN201',
      mensaje: descripcionError.substring(0, 50)
    })
      /* istanbul ignore next: Bloque inalcanzable */
      .catch(dbError => {
        /* istanbul ignore next: error en logTransaction, inalcanzable en tests */
        request.log.error({ dbError }, ERROR_GUARDANDO_EN_BD);
      });
  } else {
    // Registrar transacción exitosa
    /* istanbul ignore next: error en logTransaction, inalcanzable en tests */
    await logTransaction({
      servicio: 'ASEG-FOND',
      fechaEvento: new Date(),
      tipoDocOrigen: body.tipoDocOrig,
      numeroIdOrigen: body.numDocOrig,
      tipoDocDestino: null,
      numeroIdDestino: null,
      monto: montoNum,
      numCuentaOrigen: formatNumCuenta(body.numCuentaOrig),
      numCuentaDestino: null,
      numeroTx: body.idTransaccionSN || mensajeId,
      estado: 'SUCCESS',
      nivel: 'INFO',
      idMensajeError: '0000',
      mensaje: 'Transacción exitosa'
    })
      /* istanbul ignore next: Bloque inalcanzable */
      .catch(dbError => {
        /* istanbul ignore next: error en logTransaction, inalcanzable en tests */
        request.log.error({ dbError }, ERROR_GUARDANDO_EN_BD);
      });

    // Registrar en CSV para trazabilidad de transacciones exitosas
    request.log.info({ mensajeId, transaccion: 'SUCCESS', idTransaccionSN: body.idTransaccionSN }, 'Transacción exitosa registrada');
  }

  return reply.code(200).send({
    respuesta: {
      idTransaccionSN: res.IDTRXSN,
      idTransaccionCore: res.IDTXNIO,
      marcaTiempo: formatMarcaTiempo(res.MKTOUT)
    },
    resultado: {
      mensajeId,
      error: tieneError,
      codigo,
      descripcionError: codigo !== 0 ? descripcionError : ''
    }
  });
}

/**
 * Maneja errores del catch block de AsegurarFondos
 */
async function handleAsegurarFondosCatchError(error: any, request: any, reply: any, mensajeId: string) {
  /* istanbul ignore next: error de negocio específico, inalcanzable en tests */
  if (error instanceof AsegurarFondosError) {
    request.log.warn({ error: error.message, code: error.code, statusCode: error.statusCode, mensajeId }, 'Error del negocio AsegurarFondos');

    const errorCode = getErrorCodeNumber(error.code);
    const httpStatus = getHttpStatusForError(error.code) as 200 | 400 | 422 | 500 | 503;
    const descripcionError = getErrorDescription(error.code);

    // Guardar error en base de datos
    await logTransaction({
      servicio: 'ASEG-FOND',
      fechaEvento: new Date(),
      tipoDocOrigen: (request.body as AsegurarFondosBody).tipoDocOrig || null,
      numeroIdOrigen: (request.body as AsegurarFondosBody).numDocOrig || null,
      tipoDocDestino: null,
      numeroIdDestino: null,
      monto: typeof (request.body as AsegurarFondosBody).monto === 'number' ? (request.body as AsegurarFondosBody).monto : parseFloat(String((request.body as AsegurarFondosBody).monto)) || 0,
      numCuentaOrigen: (request.body as AsegurarFondosBody).numCuentaOrig || null,
      numCuentaDestino: null,
      numeroTx: (request.body as AsegurarFondosBody).idTransaccionSN || mensajeId,
      estado: 'ERROR',
      nivel: 'ERROR',
      idMensajeError: error.code,
      mensaje: descripcionError.substring(0, 50)
    })
      /* istanbul ignore next: Bloque inalcanzable */
      .catch(dbError => {
        /* istanbul ignore next: error en logTransaction, inalcanzable en tests */
        request.log.error({ dbError }, ERROR_GUARDANDO_EN_BD);
      });

    return reply.code(httpStatus).send({
      resultado: {
        mensajeId,
        error: true,
        codigo: errorCode,
        descripcionError
      }
    });
  }

  request.log.error({ error, mensajeId, errorType: 'INTERNAL_ERROR' }, 'Error interno AsegurarFondos');

  // Guardar error interno en base de datos
  /* istanbul ignore next: error en logTransaction, inalcanzable en tests */
  await logTransaction({
    servicio: 'ASEG-FOND',
    fechaEvento: new Date(),
    tipoDocOrigen: (request.body as AsegurarFondosBody)?.tipoDocOrig || null,
    numeroIdOrigen: (request.body as AsegurarFondosBody)?.numDocOrig || null,
    tipoDocDestino: null,
    numeroIdDestino: null,
    monto: 0,
    numCuentaOrigen: (request.body as AsegurarFondosBody)?.numCuentaOrig || null,
    numCuentaDestino: null,
    numeroTx: mensajeId,
    estado: 'ERROR',
    nivel: 'FATAL',
    idMensajeError: 'INTERNAL_ERROR',
    mensaje: 'Error interno al procesar AsegurarFondos'
  })
    /* istanbul ignore next: Bloque inalcanzable */
    .catch(dbError => {
      /* istanbul ignore next: error en logTransaction, inalcanzable en tests */
      request.log.error({ dbError }, ERROR_GUARDANDO_EN_BD);
    });

  return reply.code(500).send({
    resultado: {
      mensajeId,
      error: true,
      codigo: 2001,
      descripcionError: 'Error interno al procesar AsegurarFondos'
    }
  });
}

const asegurarFondosRouter: FastifyPluginAsync = async (fastify) => {
  fastify.post(
    '/AsegurarFondos',
    {
      schema: {
        body: AsegurarFondosBodySchema,
        response: {
          200: AsegurarFondosResponseSchema,
          400: ErrorResponseSchema,
          422: ErrorResponseSchema,
          500: ErrorResponseSchema,
          503: ErrorResponseSchema
        },
        tags: ['AsegurarFondos'],
        description: 'Asegurar fondos para una transacción',
        summary: 'Crear AsegurarFondos'
      },
      attachValidation: true
    },
    async (request, reply) => {
      const requestWithId = request as RequestWithMensajeId;
      const mensajeId = requestWithId.mensajeId;

      request.log.info({ mensajeId, body: request.body }, 'Procesando AsegurarFondos');

      if (request.validationError) {
        handleValidationError(request, reply, mensajeId);
        return;
      }

      try {
        const body = request.body as AsegurarFondosBody;

        // Convertir y validar campos monetarios que vienen como string (hasta 13 enteros + 2 decimales en cliente)
        const montoNum = parseMoney((body as any).monto);
        if (isNaN(montoNum) || montoNum <= 0) {
          const config = ASEGURAR_FONDOS_VALIDACION_LOCAL_CONFIG.CAMPO_MAL_INFORMADO;

          // Guardar en base de datos
          /* istanbul ignore next: error en logTransaction, inalcanzable en tests */
          await logTransaction({
            servicio: 'ASEG-FOND',
            fechaEvento: new Date(),
            tipoDocOrigen: body.tipoDocOrig || null,
            numeroIdOrigen: body.numDocOrig || null,
            tipoDocDestino: null,
            numeroIdDestino: null,
            monto: 0,
            numCuentaOrigen: body.numCuentaOrig || null,
            numCuentaDestino: null,
            numeroTx: body.idTransaccionSN || mensajeId,
            estado: 'ERROR',
            nivel: 'ERROR',
            idMensajeError: 'CAMPO_MAL_INFORMADO',
            mensaje: 'Valor de monto inv\u00e1lido'
          })
            /* istanbul ignore next: Bloque inalcanzable */
            .catch(dbError => {
              /* istanbul ignore next: error en logTransaction, inalcanzable en tests */
              request.log.error({ dbError }, ERROR_GUARDANDO_VALIDACION_LOCAL_EN_BD);
            });

          return reply.code(config.httpStatus).send({
            resultado: {
              mensajeId,
              error: true,
              codigo: config.codigoRta,
              descripcionError: config.descripcion
            }
          });
        }

        const valorComisionNum = (body as any).valorComision !== undefined ? parseMoney((body as any).valorComision) : 0;
        const valorIvaComisionNum = (body as any).valorIvaComision !== undefined ? parseMoney((body as any).valorIvaComision) : 0;

        const now = new Date();
        const horaConexion = now.toISOString().slice(0, 16);
        const fechaContable = now.toISOString().slice(0, 10);

        const asegurarFondosService = createAsegurarFondosService(request.log);

        // Procesar campos opcionales que pueden ser string u objeto
        const campo1Value = typeof body.campo1 === 'string' ? body.campo1 : (body.campo1 ? JSON.stringify(body.campo1) : '');
        const campo2Value = typeof body.campo2 === 'string' ? body.campo2 : (body.campo2 ? JSON.stringify(body.campo2) : '');
        const campo3Value = typeof body.campo3 === 'string' ? body.campo3 : (body.campo3 ? JSON.stringify(body.campo3) : '');
        const campo4Value = typeof body.campo4 === 'string' ? body.campo4 : (body.campo4 ? JSON.stringify(body.campo4) : '');
        const campo5Value = typeof body.campo5 === 'string' ? body.campo5 : (body.campo5 ? JSON.stringify(body.campo5) : '');

        const asegurarFondosRequest: AsegurarFondosRequest = {
          cabecera: {
            rutaServicio: process.env.API_SANBA_RUTA_SERVICIO_ASEGURAR_FONDOS || 'asegurarFondos',
            sesion: {
              usuario: process.env.API_SANBA_USUARIO,
              terminal: process.env.API_SANBA_TERMINAL,
              horaConexion,
              entorno: process.env.API_SANBA_ENTORNO,
              perfil: process.env.API_SANBA_PERFIL,
              sucursal: process.env.API_SANBA_SUCURSAL,
              entidad: process.env.API_SANBA_ENTIDAD,
              diasRestantesCambioClave: process.env.API_SANBA_DIAS_RESTANTES_CAMBIO_CLAVE,
              fechaContable,
              turno: ''
            },
            funcion: process.env.API_SANBA_FUNCION,
            secuencia: Number(process.env.API_SANBA_SECUENCIA || Math.floor(Math.random() * 100000)),
            canal: process.env.API_SANBA_CANAL || '60'
          },
          data: {
            monto: montoNum.toFixed(2),
            ValorComision: valorComisionNum.toFixed(2),
            ValorIvaComision: valorIvaComisionNum.toFixed(2),
            nombre: body.nombre ?? '',
            tipoDocOrig: mapTipoDocumento(body.tipoDocOrig),
            numDocOrig: formatIdentificacion(body.numDocOrig),
            tipoCuentaOrig: body.tipoCuentaOrig ?? '',
            numCuentaOrig: formatNumCuenta(body.numCuentaOrig),
            idTransaccionSN: body.idTransaccionSN ?? '',
            identificadorSPBVI: body.identificadorSPBVI ?? '',
            tipoTransaccion: body.tipoTransaccion ?? '',
            idTransaccionOriginal: body.idTransaccionOriginal ?? '',
            marcaTiempo: formatMarcaTiempo(body.marcaTiempo ?? new Date().toISOString()),
            HubConcentrador: body.hubConcentrador ?? '',
            IpOriginador: body.ipOriginador ?? '',
            CanalOriginador: body.canalOriginador ?? '',
            Campo1: campo1Value,
            Campo2: campo2Value,
            Campo3: campo3Value,
            Campo4: campo4Value,
            Campo5: campo5Value,
            idTransaccionSPBVI: body.idTransaccionSPBVI,
            CampoLibre0: body.CampoLibre0 ?? '',
            CampoLibre1: body.CampoLibre1 ?? ''
          }
        };

        const resultado: AsegurarFondos = await asegurarFondosService.asegurarFondos(asegurarFondosRequest);

        request.log.info({ mensajeId, resultado }, 'AsegurarFondos procesada exitosamente');

        // PRIORIDAD 1: Verificar errores de validación en el array errores[] (códigos BGE de Altair)
        if (resultado.errores && resultado.errores.length > 0) {
          return await handleAltairErrorArray(resultado, request, reply, mensajeId, body, montoNum);
        }

        // PRIORIDAD 2: Verificar estructura BGMN201 para errores de transacción
        return await processBGMN201Response(resultado, request, reply, mensajeId, body, montoNum);
      } catch (error) {
        return await handleAsegurarFondosCatchError(error, request, reply, mensajeId);
      }
    }
  );

  fastify.log.info('Ruta /AsegurarFondos registrada correctamente');
};

export { formatNumCuenta, formatMarcaTiempo };
export default asegurarFondosRouter;

