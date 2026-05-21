import { getApiConfig } from '../config/api.config';
import { FastifyBaseLogger } from 'fastify';
import { HttpClient, HttpClientError } from '../lib';
import { AsegurarFondosRequest, AsegurarFondos } from 'types';

export class AsegurarFondosError extends Error {
  constructor (
    message: string,
    public code: string,
    public statusCode?: number,
    public details?: unknown
  ) {
    super(message);
    this.name = 'AsegurarFondosError';
  }
}

export class AsegurarFondosService {
  private readonly httpClient: HttpClient;
  private readonly logger: FastifyBaseLogger;

  constructor (logger: FastifyBaseLogger) {
    this.logger = logger;

    const apiConfig = getApiConfig('sanbaApi');

    this.httpClient = new HttpClient({
      baseURL: apiConfig.baseURL,
      timeout: apiConfig.timeout,
      rejectUnauthorized: apiConfig.rejectUnauthorized
    });
  }

  private buildSanbaErrorResponse(errorData: any): AsegurarFondos {
    return {
      ok: errorData.ok || false,
      errores: errorData.errores || [],
      avisos: errorData.avisos || [],
      cabecera: errorData.cabecera || {} as any,
      autorizacion: errorData.autorizacion || null,
      paginacion: (errorData.paginacion || {}) as Record<string, unknown>,
      conexion: errorData.conexion || null,
      data: {
        BGMN201: {
          IDTRXSN: '',
          IDTXNIO: '',
          MKTOUT: '',
          ERROR: 'N',
          COERROR: '0',
          DERROR1: '',
          DERROR2: '',
          DERROR3: ''
        }
      }
    };
  }

  private handleAsegurarFondosError(error: unknown): AsegurarFondos | never {
    this.logger.error({ error }, 'Error al consultar la API externa');

    if (this.isHttpClientError(error) && error.data?.errores && error.data.errores.length > 0) {
      this.logger.info({ errores: error.data.errores }, 'Retornando respuesta con errores de SANBA');
      return this.buildSanbaErrorResponse(error.data);
    }

    if (this.isHttpClientError(error)) {
      this.mapHttpErrorToBusinessError(error);
    }

    if (error instanceof AsegurarFondosError) {
      throw error;
    }

    throw new AsegurarFondosError(
      'Error inesperado al consultar AsegurarFondos',
      'INTERNAL_ERROR',
      500
    );
  }

  async asegurarFondos (
    params: AsegurarFondosRequest
  ): Promise<AsegurarFondos> {
    try {
      this.logger.info({ params }, 'Iniciando consulta de AsegurarFondos');

      const response = await this.httpClient.post<AsegurarFondos>(
        process.env.API_SANBA_RUTA_ASEGURAR_FONDOS,
        params,
        {
          headers: {
            mqRoute: process.env.API_SANBA_MQ_ROUTE || 'QCTFD'
          }
        }
      );

      this.logger.info(
        { status: response.status, mensajeId: response.data.avisos },
        'Respuesta exitosa de la API externa'
      );

      this.logger.info(
        { data: response.data },
        'Datos de AsegurarFondos obtenidos correctamente'
      );
      return response.data;
    } catch (error) {
      return this.handleAsegurarFondosError(error);
    }
  }

  private mapHttpErrorToBusinessError (httpError: HttpClientError): never {
    const { status, code, data, message } = httpError;

    // Helper to extract error message from external API response
    const extractMessage = (defaultMsg: string): string => {
      return data?.errores?.[0]?.mensaje || defaultMsg;
    };

    // First handle explicit status-based errors (401, 404, 400, 422)
    this.handleStatusCases(status, data, extractMessage);

    // Then handle non-status specific cases: timeouts, connection issues, 5xx
    this.handleDefaultCases(status, code);

    // Generic fallback for other errors
    throw new AsegurarFondosError(
      message || 'Error en la comunicación con el core bancario',
      'EXTERNAL_API_ERROR',
      status || 502,
      data
    );
  }

  /**
   * Handle explicit HTTP status cases and throw corresponding business errors.
   */
  private handleStatusCases (
    status: number | undefined,
    data: { errores?: { mensaje: string }[] } | null,
    extractMessage: (defaultMsg: string) => string
  ): void {
    switch (status) {
      case 401:
        throw new AsegurarFondosError(
          extractMessage('Token de autenticación inválido o expirado'),
          'INVALID_AUTH_TOKEN',
          401,
          data
        );

      case 404:
        throw new AsegurarFondosError('AsegurarFondos no encontrada', 'ASEGURAR_FONDOS_NOT_FOUND', 404);

      case 400:
        throw new AsegurarFondosError(
          extractMessage('Error de validación en la solicitud'),
          'ASEGURAR_FONDOS_VALIDATION_ERROR',
          400,
          data?.errores || []
        );

      case 422:
        throw new AsegurarFondosError(
          extractMessage('Datos inválidos o solicitud no procesable'),
          'INVALID_REQUEST',
          422,
          data?.errores || []
        );
      // no default — fallthrough to other handlers
    }
  }

  /**
   * Handle non-status-specific cases such as timeouts, connection errors and 5xx.
   */
  private handleDefaultCases (
    status: number | undefined,
    code: string | undefined
  ): void {
    // Detect timeout by Axios error code
    if (code === 'ECONNABORTED' || code === 'ETIMEDOUT') {
      throw new AsegurarFondosError('Timeout en la consulta al core bancario', 'CORE_TIMEOUT', 504);
    }

    // Detect connection errors
    if (code === 'ECONNREFUSED' || code === 'ENOTFOUND' || code === 'EAI_AGAIN') {
      throw new AsegurarFondosError('No se pudo establecer conexión con el core bancario', 'CORE_CONNECTION_ERROR', 503);
    }

    // Handle 5xx errors from external server
    if (status && status >= 500) {
      throw new AsegurarFondosError('Servicio externo no disponible', 'CORE_COMMUNICATION_ERROR', 503);
    }
  }

  private isHttpClientError (error: unknown): error is HttpClientError {
    return (
      error !== null &&
      error !== undefined &&
      typeof error === 'object' &&
      'message' in error &&
      ('status' in error || 'code' in error)
    );
  }
}

export function createAsegurarFondosService (logger: FastifyBaseLogger): AsegurarFondosService {
  return new AsegurarFondosService(logger);
}

export default AsegurarFondosService;
