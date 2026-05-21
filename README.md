async function enviarNotificacion(payload, logger, mensajeId, authorization) {
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
        logger.info({
            mensajeId,
            mappedPayload: notificacionPayload,
            mappedHeaders: {
                'Content-Type': 'application/json',
                'X-Channel': channel,
                'x-bg-api-key': '***masked***'
            }
        }, 'Payload mapeado para EnvioNotificacion');
        console.log(`[EnvioNotificacion][MAPPED_REQUEST] mensajeId=${mensajeId} payload=${JSON.stringify(notificacionPayload)}`);
        (0, lib_1.getCSVErrorLogger)().logError({
            mensajeId,
            errorType: 'ENVIO_NOTIFICACION_REQUEST',
            message: 'Solicitud de EnvioNotificacion construida',
            details: notificacionPayload,
            level: 'info'
        });
        // Crear cliente HTTP con URL base vacía (usamos URL completa)
        const httpClient = (0, httpClient_1.createHttpClient)({
            baseURL: '',
            timeout: parseInt(process.env.API_NOTIFICACION_TIMEOUT || '30000', 10),
            rejectUnauthorized: false // API de notificación puede tener certificado diferente
        });
        // Enviar notificación usando URL completa
        const response = await httpClient.post(notificacionUrl, notificacionPayload, {
            headers: {
                'Content-Type': 'application/json',
                'x-bg-api-key': apiKey,
                'X-Channel': channel
            }
        });
        const successValidation = (0, envio_notificacion_validations_1.validateSuccessResponse)(response.status, response.data, notificacionPayload);
        if (successValidation.isTechnicalError) {
            const validationError = new Error(successValidation.descripcionError);
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
            logger.warn({ mensajeId, status: response.status, data: response.data }, 'Notificación procesada con error de negocio');
            (0, lib_1.getCSVErrorLogger)().logError({
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
            await (0, databaseErrorLogger_1.logPayloadToDatabase)(logger, {
                resultado: {
                    mensajeId,
                    error: true,
                    codigo: successValidation.codigo,
                    descripcionError: successValidation.descripcionError,
                    fechaCreacion,
                    fechaActualizacion
                }
            }, mensajeId, 'ENVIO_NOTIFICACION');
            return;
        }
        printEnvioNotificacionResponse(logger, 'info', {
            mensajeId,
            caseId: successValidation.caseId,
            status: response.status,
            response: response.data,
            descripcion: 'EnvioNotificacion exitoso'
        });
        logger.info({ mensajeId, status: response.status, data: response.data }, 'Notificación enviada exitosamente');
        (0, lib_1.getCSVErrorLogger)().logError({
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
        await (0, databaseErrorLogger_1.logPayloadToDatabase)(logger, {
            resultado: {
                mensajeId,
                error: false,
                codigo: 0,
                descripcionError: '',
                fechaCreacion,
                fechaActualizacion
            }
        }, mensajeId, 'ENVIO_NOTIFICACION');
    }
    catch (error) {
        const errorObj = error;
        const responseStatus = typeof errorObj.status === 'number'
            ? errorObj.status
            : (typeof errorObj.statusCode === 'number' ? errorObj.statusCode : 500);
        const responseData = (typeof errorObj.data === 'object' && errorObj.data !== null)
            ? errorObj.data
            : null;
        const errorValidation = (0, envio_notificacion_validations_1.validateErrorResponse)(responseStatus, responseData, errorObj.message);
        printEnvioNotificacionResponse(logger, 'error', {
            mensajeId,
            caseId: errorValidation.caseId,
            status: responseStatus,
            response: responseData,
            descripcion: errorValidation.descripcionError
        });
        logger.error({
            mensajeId,
            error: errorObj.message,
            status: responseStatus,
            caseId: errorValidation.caseId,
            responseData,
            stack: errorObj.stack
        }, 'Error al enviar notificación');
        // Registrar error en CSV
        (0, lib_1.logErrorToCSV)(logger, {
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
        }, mensajeId, errorValidation.csvErrorType);
        // Registrar error en base de datos con código 9001 (misma estructura que NotificarProcesoLlave)
        const fechaCreacion = new Date().toISOString();
        const fechaActualizacion = new Date().toISOString();
        await (0, databaseErrorLogger_1.logPayloadToDatabase)(logger, {
            resultado: {
                mensajeId,
                error: true,
                codigo: errorValidation.codigo,
                descripcionError: errorValidation.descripcionError,
                fechaCreacion,
                fechaActualizacion
            }
        }, mensajeId, 'ENVIO_NOTIFICACION');
    }
}
