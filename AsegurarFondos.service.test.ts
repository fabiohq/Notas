/// <reference types="jest" />
import { describe, test, expect, beforeEach, afterEach, jest } from '@jest/globals';
import { getApiConfig } from '../../../src/config/api.config';
import { HttpClient } from '../../../src/lib';
import {
  AsegurarFondosError,
  AsegurarFondosService,
  createAsegurarFondosService
} from '../../../src/services/asegurarFondos.service';
import type { AsegurarFondosRequest, AsegurarFondos } from '../../../src/types';

const mockPost = jest.fn();

jest.mock('../../../src/config/api.config', () => ({
  getApiConfig: jest.fn()
}));

jest.mock('../../../src/lib', () => ({
  HttpClient: jest.fn().mockImplementation(() => ({
    post: mockPost
  }))
}));

describe('AsegurarFondosService', () => {
  const logger = {
    info: jest.fn(),
    error: jest.fn()
  } as any;

  const request: AsegurarFondosRequest = {
    cabecera: {
      canal: 'WEB',
      ip: '192.10.1.24',
      mensajeId: '3fa85f66-5717-4562-b3fc-2c963f66afa6'
    },
    data: {
      monto: '100000.00',
      valorComision: '0.00',
      valorIvaComision: '0.00',
      nombre: 'Juan',
      tipoDocOrig: 'CC',
      numDocOrig: '12345678901',
      tipoCuentaOrig: 'CAHO',
      numCuentaOrig: '00650100000987654321',
      idTransaccionSN: 'TX123456',
      identificadorSPBVI: 'TFY',
      tipoTransaccion: 'TRANSFER',
      marcaTiempo: '2025-02-10T14:30:00.000',
      hubConcentrador: 'HUB001',
      ipOriginador: '192.10.1.24',
      canalOriginador: 'WEB',
      campo1: 'valor1',
      idTransaccionSPBVI: 'SPBVI123456'
    }
  } as any;

  const successResponse: AsegurarFondos = {
    ok: true,
    errores: [],
    avisos: [],
    cabecera: { resultado: 'OK' } as any,
    autorizacion: null,
    paginacion: {},
    conexion: null,
    data: {
      BGMN201: {
        IDTRXSN: 'TX123456',
        IDTXNIO: 'CORE987654321',
        MKTOUT: '2025-12-10T10:30:00.000',
        ERROR: 'N',
        COERROR: '0',
        DERROR1: 'OK',
        DERROR2: '',
        DERROR3: ''
      }
    }
  } as any;

  beforeEach(() => {
    jest.clearAllMocks();

    process.env.API_SANBA_RUTA_ASEGURAR_FONDOS = '/asegurar-fondos';
    process.env.API_SANBA_MQ_ROUTE = 'QTEST';

    (getApiConfig as jest.Mock).mockReturnValue({
      baseURL: 'https://sanba.test',
      timeout: 5000,
      rejectUnauthorized: false
    });
  });

  afterEach(() => {
    delete process.env.API_SANBA_RUTA_ASEGURAR_FONDOS;
    delete process.env.API_SANBA_MQ_ROUTE;
  });

  test('Debe construir HttpClient con configuración de sanbaApi', () => {
    new AsegurarFondosService(logger);

    expect(getApiConfig).toHaveBeenCalledWith('sanbaApi');
    expect(HttpClient).toHaveBeenCalledWith({
      baseURL: 'https://sanba.test',
      timeout: 5000,
      rejectUnauthorized: false
    });
  });

  test('Debe enviar la petición al core con ruta, payload y mqRoute configurado', async () => {
    mockPost.mockResolvedValue({ status: 200, data: successResponse });

    const service = new AsegurarFondosService(logger);
    const result = await service.asegurarFondos(request);

    expect(result).toBe(successResponse);
    expect(mockPost).toHaveBeenCalledWith(
      '/asegurar-fondos',
      request,
      {
        headers: {
          mqRoute: 'QTEST'
        }
      }
    );
    expect(logger.info).toHaveBeenCalledWith({ params: request }, 'Iniciando consulta de AsegurarFondos');
  });

  test('Debe usar QCTFD como mqRoute por defecto cuando no existe variable de entorno', async () => {
    delete process.env.API_SANBA_MQ_ROUTE;
    mockPost.mockResolvedValue({ status: 200, data: successResponse });

    const service = new AsegurarFondosService(logger);
    await service.asegurarFondos(request);

    expect(mockPost).toHaveBeenCalledWith(
      '/asegurar-fondos',
      request,
      {
        headers: {
          mqRoute: 'QCTFD'
        }
      }
    );
  });

  test('Debe retornar respuesta SANBA normalizada cuando HttpClientError trae errores del core', async () => {
    const sanbaError = {
      message: 'Business error',
      status: 422,
      data: {
        ok: false,
        errores: [{ codigo: 'BGE0038', mensaje: 'Cuenta no existe' }],
        avisos: [{ codigo: 'AVISO' }],
        cabecera: { resultado: 'ERROR' },
        autorizacion: null,
        paginacion: { pagina: 1 },
        conexion: null
      }
    };

    mockPost.mockRejectedValue(sanbaError);

    const service = new AsegurarFondosService(logger);
    const result = await service.asegurarFondos(request);

    expect(result.ok).toBe(false);
    expect(result.errores).toEqual([{ codigo: 'BGE0038', mensaje: 'Cuenta no existe' }]);
    expect(result.data.BGMN201).toEqual({
      IDTRXSN: '',
      IDTXNIO: '',
      MKTOUT: '',
      ERROR: 'N',
      COERROR: '0',
      DERROR1: '',
      DERROR2: '',
      DERROR3: ''
    });
    expect(logger.error).toHaveBeenCalledWith({ error: sanbaError }, 'Error al consultar la API externa');
    expect(logger.info).toHaveBeenCalledWith(
      { errores: sanbaError.data.errores },
      'Retornando respuesta con errores de SANBA'
    );
  });

  test.each([
    {
      name: '401',
      error: { message: 'Unauthorized', status: 401, data: { errores: [{ mensaje: 'Token inválido' }] } },
      expectedMessage: 'Token inválido',
      expectedCode: 'INVALID_AUTH_TOKEN',
      expectedStatus: 401
    },
    {
      name: '404',
      error: { message: 'Not found', status: 404, data: null },
      expectedMessage: 'AsegurarFondos no encontrada',
      expectedCode: 'ASEGURAR_FONDOS_NOT_FOUND',
      expectedStatus: 404
    },
    {
      name: '400',
      error: { message: 'Bad request', status: 400, data: { errores: [{ mensaje: 'Solicitud inválida' }] } },
      expectedMessage: 'Solicitud inválida',
      expectedCode: 'ASEGURAR_FONDOS_VALIDATION_ERROR',
      expectedStatus: 400
    },
    {
      name: '422',
      error: { message: 'Unprocessable', status: 422, data: { errores: [{ mensaje: 'No procesable' }] } },
      expectedMessage: 'No procesable',
      expectedCode: 'INVALID_REQUEST',
      expectedStatus: 422
    },
    {
      name: 'timeout ECONNABORTED',
      error: { message: 'timeout', code: 'ECONNABORTED' },
      expectedMessage: 'Timeout en la consulta al core bancario',
      expectedCode: 'CORE_TIMEOUT',
      expectedStatus: 504
    },
    {
      name: 'timeout ETIMEDOUT',
      error: { message: 'timeout', code: 'ETIMEDOUT' },
      expectedMessage: 'Timeout en la consulta al core bancario',
      expectedCode: 'CORE_TIMEOUT',
      expectedStatus: 504
    },
    {
      name: 'connection ECONNREFUSED',
      error: { message: 'connection refused', code: 'ECONNREFUSED' },
      expectedMessage: 'No se pudo establecer conexión con el core bancario',
      expectedCode: 'CORE_CONNECTION_ERROR',
      expectedStatus: 503
    },
    {
      name: 'connection ENOTFOUND',
      error: { message: 'not found', code: 'ENOTFOUND' },
      expectedMessage: 'No se pudo establecer conexión con el core bancario',
      expectedCode: 'CORE_CONNECTION_ERROR',
      expectedStatus: 503
    },
    {
      name: 'connection EAI_AGAIN',
      error: { message: 'dns retry', code: 'EAI_AGAIN' },
      expectedMessage: 'No se pudo establecer conexión con el core bancario',
      expectedCode: 'CORE_CONNECTION_ERROR',
      expectedStatus: 503
    },
    {
      name: '5xx externo',
      error: { message: 'server error', status: 503 },
      expectedMessage: 'Servicio externo no disponible',
      expectedCode: 'CORE_COMMUNICATION_ERROR',
      expectedStatus: 503
    }
  ])('Debe mapear error HTTP $name a AsegurarFondosError', async ({ error, expectedMessage, expectedCode, expectedStatus }) => {
    mockPost.mockRejectedValue(error);

    const service = new AsegurarFondosService(logger);

    await expect(service.asegurarFondos(request)).rejects.toMatchObject({
      name: 'AsegurarFondosError',
      message: expectedMessage,
      code: expectedCode,
      statusCode: expectedStatus
    });
  });

  test('Debe usar mensaje genérico para HttpClientError sin caso específico', async () => {
    mockPost.mockRejectedValue({
      message: 'Error externo no controlado',
      status: 418,
      data: { detalle: 'teapot' }
    });

    const service = new AsegurarFondosService(logger);

    await expect(service.asegurarFondos(request)).rejects.toMatchObject({
      name: 'AsegurarFondosError',
      message: 'Error externo no controlado',
      code: 'EXTERNAL_API_ERROR',
      statusCode: 418,
      details: { detalle: 'teapot' }
    });
  });

  test('Debe relanzar AsegurarFondosError sin envolverlo', async () => {
    const domainError = new AsegurarFondosError('Error controlado', 'CONTROLLED_ERROR', 409, { id: '1' });
    mockPost.mockRejectedValue(domainError);

    const service = new AsegurarFondosService(logger);

    await expect(service.asegurarFondos(request)).rejects.toBe(domainError);
  });

  test('Debe envolver errores inesperados como INTERNAL_ERROR', async () => {
    mockPost.mockRejectedValue(new Error('boom'));

    const service = new AsegurarFondosService(logger);

    await expect(service.asegurarFondos(request)).rejects.toMatchObject({
      name: 'AsegurarFondosError',
      message: 'Error inesperado al consultar AsegurarFondos',
      code: 'INTERNAL_ERROR',
      statusCode: 500
    });
  });

  test('Factory debe crear una instancia de AsegurarFondosService', () => {
    const service = createAsegurarFondosService(logger);

    expect(service).toBeInstanceOf(AsegurarFondosService);
  });
});
