/**
 * Servicio de Envío de Notificaciones
 * 
 * Servicio asíncrono para enviar notificaciones después de procesar NotificarProcesoLlave
 */

import { createHttpClient } from '../lib/shared/httpClient';
import { logErrorToCSV, getCSVErrorLogger } from '../lib';
import { logPayloadToDatabase } from '../lib/shared/databaseErrorLogger';
import type { FastifyBaseLogger } from 'fastify';
import { validateSuccessResponse, validateErrorResponse } from './envio-notificacion-validations';

type ConsoleLogLevel = 'info' | 'warn' | 'error';

function printEnvioNotificacionResponse(
  logger: FastifyBaseLogger,
  level: ConsoleLogLevel,
  payload: {
    mensajeId: string;
    caseId: string;
    status: number;
    response: unknown;
    descripcion: string;
  }
): void {
  const logLine = `[EnvioNotificacion][${payload.caseId}] mensajeId=${payload.mensajeId} status=${payload.status} descripcion=${payload.descripcion} response=${JSON.stringify(payload.response)}`;

  if (level === 'error') {
    logger.error(payload, 'Respuesta de EnvioNotificacion (console)');
    console.error(logLine);
    return;
  }

  if (level === 'warn') {
    logger.warn(payload, 'Respuesta de EnvioNotificacion (console)');
    console.warn(logLine);
    return;
  }

  logger.info(payload, 'Respuesta de EnvioNotificacion (console)');
  console.log(logLine);
}

/**
 * Interfaz para el payload de NotificarProcesoLlave
 */
export interface NotificacionPayload {
  tipoIdentificacion: string;
  identificacion: string;
  llaveUsuario: string;
  tipoTransaccion: string;
  estadoTx: string | null;
  identificadorTx: string | null;
  numeroMedioPago: string;
  fechaHoraEjecucion: string;
  Error: string | null;
  DescripcionError: string | null;
  monto: number;
}

/**
 * Mapeo de tipos de identificación a códigos
 */
const TIPO_IDENTIFICACION_MAP: Record<string, string> = {
  NIT: '10',
  CC: '1',
  CE: '2',
  TI: '3',
  PA: '4',
  NUIP: '5',
  OTR: '99'
};



/**
 * Mapeo de tipos de proceso
 */
const TIPO_PROCESO_MAP: Record<string, string> = {
  REGISTRO: '1',
  MODIFICACION: '2',
  BLOQUEO: '3',
  CANCELACION: '4',
  REACTIVACION: '5'
};

/**
 * Envía notificación de forma asíncrona (fire and forget)
 * 
 * @param payload - Datos del proceso de llave
 * @param logger - Logger de Fastify
 * @param mensajeId - ID del mensaje para trazabilidad
 */
export async function enviarNotificacionAsync(
  payload: NotificacionPayload,
  logger: FastifyBaseLogger,
  mensajeId: string
): Promise<void> {
  // Ejecutar de forma asíncrona sin bloquear
  setImmediate(async () => {
    try {
      await enviarNotificacion(payload, logger, mensajeId);
    } catch (error) {
      // Los errores ya fueron registrados en enviarNotificacion
      logger.error({ mensajeId, error }, 'Error no controlado en enviarNotificacionAsync');
    }
  });
}

/**
 * Envía notificación al servicio EnvioNotificacion
 */
async function enviarNotificacion(
  payload: NotificacionPayload,
  logger: FastifyBaseLogger,
  mensajeId: string
): Promise<void> {
  try {
    logger.info({ mensajeId, payload }, 'Iniciando envío de notificación');

    // Validar que tengamos la URL configurada
    const notificacionUrl = process.env.API_NOTIFICACION_URL;
    if (!notificacionUrl) {
      throw new Error('API_NOTIFICACION_URL no está configurada');
    }

    const apiKey = process.env.API_NOTIFICACION_X_BG_API_KEY;
    if (!apiKey) {
      throw new Error('API_NOTIFICACION_X_BG_API_KEY no está configurada');
    }

    const channel = process.env.API_NOTIFICACION_X_CHANNEL;
    if (!channel) {
      throw new Error('API_NOTIFICACION_X_CHANNEL no está configurada');
    }

    const moneda = process.env.API_NOTIFICACION_MONEDA;
    if (!moneda) {
      throw new Error('API_NOTIFICACION_MONEDA no está configurada');
    }

    // Mapear tipoIdentificacion
    const tipoIdentificacionMapeado = TIPO_IDENTIFICACION_MAP[payload.tipoIdentificacion] || '10';

    // Mapear tipoTransaccion
    const tipoTransaccionMapeado = TIPO_PROCESO_MAP[payload.tipoTransaccion] || payload.tipoTransaccion;

    // Construir payload para EnvioNotificacion (6 campos mapeados)
    const notificacionPayload = {
      tipoIdentificacion: tipoIdentificacionMapeado,
      identificacion: payload.identificacion,
      LlaveUsuario: payload.llaveUsuario,
      TipoTransaccion: tipoTransaccionMapeado,
      estadoTx: payload.estadoTx,
      identificadorTx: payload.identificadorTx,
      numeroMedioPago: payload.numeroMedioPago,
      FechaHoraEjecucion: payload.fechaHoraEjecucion,
      Error: payload.Error,
      DescripcionError: payload.DescripcionError,
      monto: payload.monto,
      Moneda: moneda
    };

    logger.info(
      {
        mensajeId,
        mappedPayload: notificacionPayload,
        mappedHeaders: {
          'Content-Type': 'application/json',
          'X-Channel': channel,
          'x-bg-api-key': '***masked***'
        }
      },
      'Payload mapeado para EnvioNotificacion'
    );
    console.log(
      `[EnvioNotificacion][MAPPED_REQUEST] mensajeId=${mensajeId} payload=${JSON.stringify(notificacionPayload)}`
    );
    getCSVErrorLogger().logError({
      mensajeId,
      errorType: 'ENVIO_NOTIFICACION_REQUEST',
      message: 'Solicitud de EnvioNotificacion construida',
      details: notificacionPayload,
      level: 'info'
    });

    // Crear cliente HTTP con URL base vacía (usamos URL completa)
    const httpClient = createHttpClient({
      baseURL: '',
      timeout: parseInt(process.env.API_NOTIFICACION_TIMEOUT || '30000', 10),
      rejectUnauthorized: false // API de notificación puede tener certificado diferente
    });

    // Enviar notificación usando URL completa
    const response = await httpClient.post(
      notificacionUrl,
      notificacionPayload,
      {
        headers: {
          'Content-Type': 'application/json',
          'x-bg-api-key': apiKey,
          'X-Channel': channel
        }
      }
    );

    const successValidation = validateSuccessResponse(response.status, response.data, notificacionPayload);

    if (successValidation.isTechnicalError) {
      const validationError = new Error(successValidation.descripcionError) as Error & {
        status?: number;
        statusCode?: number;
        data?: unknown;
      };
      validationError.status = response.status;
      validationError.statusCode = response.status;
      validationError.data = response.data;
      throw validationError;
    }

    if (successValidation.isBusinessError) {
      printEnvioNotificacionResponse(logger, 'warn', {
        mensajeId,
        caseId: successValidation.caseId,
        status: response.status,
        response: response.data,
        descripcion: successValidation.descripcionError
      });

      logger.warn(
        { mensajeId, status: response.status, data: response.data },
        'Notificación procesada con error de negocio'
      );
      getCSVErrorLogger().logError({
        mensajeId,
        errorType: successValidation.csvErrorType,
        errorCode: successValidation.csvErrorCode,
        statusCode: response.status,
        message: successValidation.descripcionError,
        details: response.data,
        level: successValidation.csvLevel
      });

      const fechaCreacion = new Date().toISOString();
      const fechaActualizacion = new Date().toISOString();

      await logPayloadToDatabase(
        logger,
        {
          resultado: {
            mensajeId,
            error: true,
            codigo: successValidation.codigo,
            descripcionError: successValidation.descripcionError,
            fechaCreacion,
            fechaActualizacion
          }
        },
        mensajeId,
        'ENVIO_NOTIFICACION'
      );

      return;
    }

    printEnvioNotificacionResponse(logger, 'info', {
      mensajeId,
      caseId: successValidation.caseId,
      status: response.status,
      response: response.data,
      descripcion: 'EnvioNotificacion exitoso'
    });

    logger.info(
      { mensajeId, status: response.status, data: response.data },
      'Notificación enviada exitosamente'
    );
    getCSVErrorLogger().logError({
      mensajeId,
      errorType: successValidation.csvErrorType,
      errorCode: successValidation.csvErrorCode,
      statusCode: response.status,
      message: 'EnvioNotificacion exitoso',
      details: response.data,
      level: successValidation.csvLevel
    });

    // Registrar éxito en base de datos (misma estructura que NotificarProcesoLlave)
    const fechaCreacion = new Date().toISOString();
    const fechaActualizacion = new Date().toISOString();

    await logPayloadToDatabase(
      logger,
      {
        resultado: {
          mensajeId,
          error: false,
          codigo: 0,
          descripcionError: '',
          fechaCreacion,
          fechaActualizacion
        }
      },
      mensajeId,
      'ENVIO_NOTIFICACION'
    );

  } catch (error) {
    const errorObj = error as Error & {
      code?: string;
      statusCode?: number;
      status?: number;
      data?: unknown;
      details?: unknown;
    };

    const responseStatus = typeof errorObj.status === 'number'
      ? errorObj.status
      : (typeof errorObj.statusCode === 'number' ? errorObj.statusCode : 500);

    const responseData = (typeof errorObj.data === 'object' && errorObj.data !== null)
      ? (errorObj.data as Record<string, unknown>)
      : null;

    const errorValidation = validateErrorResponse(responseStatus, responseData, errorObj.message);

    printEnvioNotificacionResponse(logger, 'error', {
      mensajeId,
      caseId: errorValidation.caseId,
      status: responseStatus,
      response: responseData,
      descripcion: errorValidation.descripcionError
    });

    logger.error(
      {
        mensajeId,
        error: errorObj.message,
        status: responseStatus,
        caseId: errorValidation.caseId,
        responseData,
        stack: errorObj.stack
      },
      'Error al enviar notificación'
    );

    // Registrar error en CSV
    logErrorToCSV(
      logger,
      {
        ...errorObj,
        code: errorValidation.csvErrorCode,
        statusCode: responseStatus,
        message: errorValidation.descripcionError,
        details: {
          mensajeId,
          payload,
          responseStatus,
          responseData,
          caseId: errorValidation.caseId
        }
      },
      mensajeId,
      errorValidation.csvErrorType
    );

    // Registrar error en base de datos con código 9001 (misma estructura que NotificarProcesoLlave)
    const fechaCreacion = new Date().toISOString();
    const fechaActualizacion = new Date().toISOString();

    await logPayloadToDatabase(
      logger,
      {
        resultado: {
          mensajeId,
          error: true,
          codigo: errorValidation.codigo,
          descripcionError: errorValidation.descripcionError,
          fechaCreacion,
          fechaActualizacion
        }
      },
      mensajeId,
      'ENVIO_NOTIFICACION'
    );
  }
}
