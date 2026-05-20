import Fastify, { FastifyInstance } from 'fastify'; import asegurarFondosRouter, { mapTipoDocumento, parseMoney } from '../../../src/routers/AsegurarFondos'; import { createAsegurarFondosService, AsegurarFondosError } from '../../../src/services/asegurarFondos.service'; import { logTransaction } from '../../../src/lib/shared/databaseErrorLogger'; import { logErrorToCSV } from '../../../src/lib/shared/csvErrorLogger';

jest.mock('../../../src/services/asegurarFondos.service', () => { class MockAsegurarFondosError extends Error { code: string; statusCode: number;

constructor(message: string, code: string, statusCode = 500) {
  super(message);
  this.name = 'AsegurarFondosError';
  this.code = code;
  this.statusCode = statusCode;
}

}

return { createAsegurarFondosService: jest.fn(), AsegurarFondosError: MockAsegurarFondosError }; });

jest.mock('../../../src/lib/shared/databaseErrorLogger', () => ({ logTransaction: jest.fn().mockResolvedValue(undefined) }));

jest.mock('../../../src/lib/shared/csvErrorLogger', () => ({ logErrorToCSV: jest.fn() }));

const mockCreateAsegurarFondosService = createAsegurarFondosService as jest.MockedFunction<typeof createAsegurarFondosService>; const mockLogTransaction = logTransaction as jest.MockedFunction<typeof logTransaction>; const mockLogErrorToCSV = logErrorToCSV as jest.MockedFunction<typeof logErrorToCSV>;

const mockService = { asegurarFondos: jest.fn() };

const buildSuccessCoreResponse = (overrides: Record<string, unknown> = {}) => ({ data: { BGMN201: { ERROR: 'N', COERROR: '0', DERROR1: '', DERROR2: '', DERROR3: '', IDTRXSN: 'process.env.IDTRANSACCIONSN', IDTXNIO: 'CORE123456', MKTOUT: '2025-02-10T14:30:01.000Z', ...overrides } } });

const buildAltairErrorResponse = (codigo: string, mensaje: string) => ({ errores: [ { codigo, mensaje } ] });

/**

IMPORTANTE:

El schema actual tiene estos patterns como strings literales:

pattern: 'process.env.IDTRANSACCIONSN'

pattern: 'process.env.MARCATIEMPO'

pattern: 'process.env.IPTRANSACCION'

Por eso el payload válido de pruebas usa esos valores exactos.

Si luego corrigen el schema para usar process.env.X || regex real,

estos valores pueden reemplazarse por UUID/ISO/IP reales. */ const validPayload = { monto: 1000.5, valorComision: 0, valorIvaComision: 0, nombre: 'Juan Perez', tipoDocOrig: 'CC', numDocOrig: '12345678901', tipoCuentaOrig: 'CAHO', numCuentaOrig: '987654321', idTransaccionSN: 'process.env.IDTRANSACCIONSN', identificadorSPBVI: 'TFY', tipoTransaccion: 'TRANSFER', marcaTiempo: 'process.env.MARCATIEMPO', ipOriginador: 'process.env.IPTRANSACCION', canalOriginador: 'WEB', campo1: 'campo uno', campo2: 'campo dos', campo3: null, campo4: '', campo5: { origen: 'test' }, idTransaccionSPBVI: 'SPBVI123456', CampoLibre0: '', CampoLibre1: '' };


describe('AsegurarFondos Router - POST /AsegurarFondos', () => { let fastify: FastifyInstance;

beforeEach(async () => { jest.clearAllMocks();

process.env.API_SANBA_RUTA_SERVICIO_ASEGURAR_FONDOS = 'asegurarFondos';
process.env.API_SANBA_USUARIO = 'USR';
process.env.API_SANBA_TERMINAL = 'TERM';
process.env.API_SANBA_ENTORNO = 'TEST';
process.env.API_SANBA_PERFIL = 'PERFIL';
process.env.API_SANBA_SUCURSAL = '001';
process.env.API_SANBA_ENTIDAD = '0065';
process.env.API_SANBA_DIAS_RESTANTES_CAMBIO_CLAVE = '30';
process.env.API_SANBA_FUNCION = 'BGMN201';
process.env.API_SANBA_SECUENCIA = '12345';
process.env.API_SANBA_CANAL = '60';

mockCreateAsegurarFondosService.mockReturnValue(mockService as never);
mockService.asegurarFondos.mockResolvedValue(buildSuccessCoreResponse());
mockLogTransaction.mockResolvedValue(undefined as never);

fastify = Fastify({ logger: false });
fastify.addHook('preHandler', async (request: any) => {
  request.mensajeId = '3fa85f66-5717-4562-b3fc-2c963f66afa6';
});
await fastify.register(asegurarFondosRouter);
await fastify.ready();

});

afterEach(async () => { await fastify.close(); });

const postAsegurarFondos = (payload: Record<string, unknown> = validPayload) => fastify.inject({ method: 'POST', url: '/AsegurarFondos', payload });

describe('helpers exportados', () => { test('mapTipoDocumento transforma NIT a NT', () => { expect(mapTipoDocumento('NIT')).toBe('NT'); expect(mapTipoDocumento('CC')).toBe('CC'); });

test('parseMoney maneja valores válidos e inválidos', () => {
  expect(parseMoney(undefined)).toBe(0);
  expect(parseMoney(null)).toBe(0);
  expect(parseMoney('')).toBe(0);
  expect(parseMoney('123.45')).toBe(123.45);
  expect(parseMoney(99.1)).toBe(99.1);
  expect(Number.isNaN(parseMoney('abc'))).toBe(true);
});

});

describe('flujo exitoso', () => { test('procesa exitosamente y llama al servicio con formato correcto', async () => { const response = await postAsegurarFondos(); const body = JSON.parse(response.payload);

expect(response.statusCode).toBe(200);
  expect(body.resultado.error).toBe(false);
  expect(body.resultado.codigo).toBe(0);
  expect(body.respuesta.idTransaccionSN).toBe('process.env.IDTRANSACCIONSN');
  expect(body.respuesta.idTransaccionCore).toBe('CORE123456');

  expect(mockService.asegurarFondos).toHaveBeenCalledTimes(1);
  const callArgs = mockService.asegurarFondos.mock.calls[0][0];

  expect(callArgs.cabecera.rutaServicio).toBe('asegurarFondos');
  expect(callArgs.cabecera.secuencia).toBe(12345);
  expect(callArgs.cabecera.canal).toBe('60');
  expect(callArgs.data.monto).toBe('1000.50');
  expect(callArgs.data.ValorComision).toBe('0.00');
  expect(callArgs.data.ValorIvaComision).toBe('0.00');
  expect(callArgs.data.tipoDocOrig).toBe('CC');
  expect(callArgs.data.numDocOrig).toBe('12345678901');
  expect(callArgs.data.numCuentaOrig).toBe('00650100000987654321');
  expect(callArgs.data.Campo5).toBe(JSON.stringify({ origen: 'test' }));
});

test('payload mínimo válido debe procesar exitosamente', async () => {
  const minimalPayload = {
    monto: 1,
    nombre: 'Ana',
    tipoDocOrig: 'CC',
    numDocOrig: '1234',
    tipoCuentaOrig: 'CAHO',
    numCuentaOrig: '123456789',
    idTransaccionSN: 'process.env.IDTRANSACCIONSN',
    identificadorSPBVI: 'TFY',
    tipoTransaccion: 'TRANSFER',
    marcaTiempo: 'process.env.MARCATIEMPO',
    idTransaccionSPBVI: 'SPBVI123'
  };

  const response = await postAsegurarFondos(minimalPayload);
  const body = JSON.parse(response.payload);

  expect(response.statusCode).toBe(200);
  expect(body.resultado.error).toBe(false);
  expect(mockService.asegurarFondos).toHaveBeenCalledTimes(1);
});

test('acepta canalOriginador vacío y null', async () => {
  const responseVacio = await postAsegurarFondos({ ...validPayload, canalOriginador: '' });
  expect(responseVacio.statusCode).toBe(200);

  mockService.asegurarFondos.mockClear();

  const responseNull = await postAsegurarFondos({ ...validPayload, canalOriginador: null });
  expect(responseNull.statusCode).toBe(200);
  expect(mockService.asegurarFondos).toHaveBeenCalledTimes(1);
});

});

describe('transformación de datos', () => { test('transforma NIT a NT antes de llamar el servicio', async () => { await postAsegurarFondos({ ...validPayload, tipoDocOrig: 'NIT' });

expect(mockService.asegurarFondos).toHaveBeenCalledTimes(1);
  const callArgs = mockService.asegurarFondos.mock.calls[0][0];
  expect(callArgs.data.tipoDocOrig).toBe('NT');
});

test('formatea número de cuenta con prefijo 00650100 y padding a 12 dígitos', async () => {
  await postAsegurarFondos({ ...validPayload, numCuentaOrig: '123456789' });

  expect(mockService.asegurarFondos).toHaveBeenCalledTimes(1);
  const callArgs = mockService.asegurarFondos.mock.calls[0][0];
  expect(callArgs.data.numCuentaOrig).toBe('00650100000123456789');
});

test('formatea número de documento con padding a 11 dígitos', async () => {
  await postAsegurarFondos({ ...validPayload, numDocOrig: '1234' });

  expect(mockService.asegurarFondos).toHaveBeenCalledTimes(1);
  const callArgs = mockService.asegurarFondos.mock.calls[0][0];
  expect(callArgs.data.numDocOrig).toBe('00000001234');
});

test('formatea valores monetarios con 2 decimales', async () => {
  await postAsegurarFondos({
    ...validPayload,
    monto: 25,
    valorComision: 1.2,
    valorIvaComision: 0.228
  });

  expect(mockService.asegurarFondos).toHaveBeenCalledTimes(1);
  const callArgs = mockService.asegurarFondos.mock.calls[0][0];
  expect(callArgs.data.monto).toBe('25.00');
  expect(callArgs.data.ValorComision).toBe('1.20');
  expect(callArgs.data.ValorIvaComision).toBe('0.23');
});

test('serializa campos opcionales objeto con JSON.stringify', async () => {
  const campo1 = { a: 1, b: 'x' };
  const campo5 = { nested: { ok: true } };

  await postAsegurarFondos({ ...validPayload, campo1, campo5 });

  expect(mockService.asegurarFondos).toHaveBeenCalledTimes(1);
  const callArgs = mockService.asegurarFondos.mock.calls[0][0];
  expect(callArgs.data.Campo1).toBe(JSON.stringify(campo1));
  expect(callArgs.data.Campo5).toBe(JSON.stringify(campo5));
});

test('campos opcionales undefined o null viajan como string vacío', async () => {
  const payload = {
    ...validPayload,
    campo1: undefined,
    campo2: null,
    campo3: '',
    campo4: undefined,
    campo5: null
  };

  await postAsegurarFondos(payload);

  expect(mockService.asegurarFondos).toHaveBeenCalledTimes(1);
  const callArgs = mockService.asegurarFondos.mock.calls[0][0];
  expect(callArgs.data.Campo1).toBe('');
  expect(callArgs.data.Campo2).toBe('');
  expect(callArgs.data.Campo3).toBe('');
  expect(callArgs.data.Campo4).toBe('');
  expect(callArgs.data.Campo5).toBe('');
});

});

describe('validación local del schema', () => { test('rechaza campos adicionales con CAMPO_INCORRECTO y no llama servicio', async () => { const response = await postAsegurarFondos({ ...validPayload, campoNoPermitido: 'x' }); const body = JSON.parse(response.payload);

expect(response.statusCode).toBe(400);
  expect(body.resultado.error).toBe(true);
  expect(mockService.asegurarFondos).not.toHaveBeenCalled();
  expect(mockLogErrorToCSV).toHaveBeenCalled();
});

test('rechaza campo obligatorio faltante y no llama servicio', async () => {
  const payload = { ...validPayload } as Record<string, unknown>;
  delete payload.nombre;

  const response = await postAsegurarFondos(payload);
  const body = JSON.parse(response.payload);

  expect(response.statusCode).toBe(400);
  expect(body.resultado.error).toBe(true);
  expect(mockService.asegurarFondos).not.toHaveBeenCalled();
});

test('rechaza enum inválido y no llama servicio', async () => {
  const response = await postAsegurarFondos({ ...validPayload, tipoDocOrig: 'XYZ' });
  const body = JSON.parse(response.payload);

  expect(response.statusCode).toBe(400);
  expect(body.resultado.error).toBe(true);
  expect(mockService.asegurarFondos).not.toHaveBeenCalled();
});

test('rechaza monto menor al mínimo desde el schema', async () => {
  const response = await postAsegurarFondos({ ...validPayload, monto: 0 });
  const body = JSON.parse(response.payload);

  expect(response.statusCode).toBe(400);
  expect(body.resultado.error).toBe(true);
  expect(mockService.asegurarFondos).not.toHaveBeenCalled();
});

});

describe('respuesta BGMN201 del core', () => { test('cuando BGMN201 ERROR = S devuelve error y código core', async () => { mockService.asegurarFondos.mockResolvedValue( buildSuccessCoreResponse({ ERROR: 'S', COERROR: '3001', DERROR1: 'Saldo', DERROR2: 'insuficiente', DERROR3: '' }) );

const response = await postAsegurarFondos();
  const body = JSON.parse(response.payload);

  expect(response.statusCode).toBe(200);
  expect(body.resultado.error).toBe(true);
  expect(body.resultado.codigo).toBe(3001);
  expect(body.resultado.descripcionError).toBe('Saldo insuficiente');
  expect(mockLogTransaction).toHaveBeenCalledWith(expect.objectContaining({ estado: 'ERROR' }));
});

test('cuando BGMN201 ERROR = N registra transacción exitosa', async () => {
  const response = await postAsegurarFondos();
  const body = JSON.parse(response.payload);

  expect(response.statusCode).toBe(200);
  expect(body.resultado.error).toBe(false);
  expect(mockLogTransaction).toHaveBeenCalledWith(expect.objectContaining({ estado: 'SUCCESS' }));
});

});

describe('errores de Altair', () => { test.each([ ['BGE0038', 2502, 'Cuenta no encontrada'], ['BGE1320', 2802, 'Cuenta inactiva'], ['BGE3244', 2504, 'Cuenta bloqueada'] ])('mapea %s al código RTA esperado', async (codigoAltair, codigoRta, mensaje) => { mockService.asegurarFondos.mockResolvedValue(buildAltairErrorResponse(codigoAltair, mensaje));

const response = await postAsegurarFondos();
  const body = JSON.parse(response.payload);

  expect(response.statusCode).toBe(422);
  expect(body.resultado.error).toBe(true);
  expect(body.resultado.codigo).toBe(codigoRta);
  expect(mockLogErrorToCSV).toHaveBeenCalled();
  expect(mockLogTransaction).toHaveBeenCalledWith(
    expect.objectContaining({
      estado: 'ERROR',
      idMensajeError: codigoAltair
    })
  );
});

test('si Altair retorna código sin mapeo usa 1422 y mensaje original', async () => {
  mockService.asegurarFondos.mockResolvedValue(buildAltairErrorResponse('BGE9999', 'Error no mapeado'));

  const response = await postAsegurarFondos();
  const body = JSON.parse(response.payload);

  expect(response.statusCode).toBe(422);
  expect(body.resultado.error).toBe(true);
  expect(body.resultado.codigo).toBe(1422);
  expect(body.resultado.descripcionError).toBe('Error no mapeado');
});

});

describe('errores del servicio', () => { test('error genérico retorna 500 con código 2001', async () => { mockService.asegurarFondos.mockRejectedValue(new Error('boom'));

const response = await postAsegurarFondos();
  const body = JSON.parse(response.payload);

  expect(response.statusCode).toBe(500);
  expect(body.resultado.error).toBe(true);
  expect(body.resultado.codigo).toBe(2001);
  expect(body.resultado.descripcionError).toBe('Error interno al procesar AsegurarFondos');
});

test('AsegurarFondosError retorna error de negocio mapeado', async () => {
  mockService.asegurarFondos.mockRejectedValue(
    new AsegurarFondosError('timeout', 'CORE_TIMEOUT', 503)
  );

  const response = await postAsegurarFondos();
  const body = JSON.parse(response.payload);

  expect(body.resultado.error).toBe(true);
  expect(body.resultado.codigo).toEqual(expect.any(Number));
  expect(body.resultado.descripcionError).toEqual(expect.any(String));
  expect(mockLogTransaction).toHaveBeenCalledWith(expect.objectContaining({ estado: 'ERROR' }));
});

}); });
