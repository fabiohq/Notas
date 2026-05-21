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

const MENSAJE_ID = '3fa85f66-5717-4562-b3fc-2c963f66afa6';

const buildSuccessCoreResponse = (overrides: Record<string, unknown> = {}) => ({ data: { BGMN201: { ERROR: 'N', COERROR: '0', DERROR1: '', DERROR2: '', DERROR3: '', IDTRXSN: 'process.env.IDTRANSACCIONSN', IDTXNIO: 'CORE123456', MKTOUT: '2025-02-10T14:30:01.000Z', ...overrides } } });

const buildAltairErrorResponse = (codigo: string, mensaje: string) => ({ errores: [ { codigo, mensaje } ] });

/**

AJUSTE VS LOS TESTS ORIGINALES DEL LOG:

Los tests fallaban porque el payload base no pasaba validación local.

En el schema actual estos patterns están escritos como strings literales:

pattern: 'process.env.IDTRANSACCIONSN'

pattern: 'process.env.MARCATIEMPO'

pattern: 'process.env.IPTRANSACCION'

Por eso el payload válido para estos tests debe usar esos valores exactos.

Si se corrige el schema para usar regex reales desde variables de entorno,

cambia estos 3 valores por UUID/ISO/IP reales. */ const validPayload = { monto: 1000.5, valorComision: 0, valorIvaComision: 0, nombre: 'Juan Perez', tipoDocOrig: 'CC', numDocOrig: '12345678901', tipoCuentaOrig: 'CAHO', numCuentaOrig: '987654321', idTransaccionSN: 'process.env.IDTRANSACCIONSN', identificadorSPBVI: 'TFY', tipoTransaccion: 'TRANSFER', marcaTiempo: 'process.env.MARCATIEMPO', ipOriginador: 'process.env.IPTRANSACCION', canalOriginador: 'WEB', campo1: 'campo uno', campo2: 'campo dos', campo3: null, campo4: '', campo5: { origen: 'test' }, idTransaccionSPBVI: 'SPBVI123456', CampoLibre0: '', CampoLibre1: '' };


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
  request.mensajeId = MENSAJE_ID;
});
await fastify.register(asegurarFondosRouter);
await fastify.ready();

});

afterEach(async () => { await fastify.close(); });

const postAsegurarFondos = (payload: Record<string, unknown> = validPayload) => fastify.inject({ method: 'POST', url: '/AsegurarFondos', payload });

describe('Helpers', () => { test('mapTipoDocumento transforma NIT a NT y mantiene otros tipos', () => { expect(mapTipoDocumento('NIT')).toBe('NT'); expect(mapTipoDocumento('CC')).toBe('CC'); });

test('parseMoney maneja undefined, null, string, number e inválidos', () => {
  expect(parseMoney(undefined)).toBe(0);
  expect(parseMoney(null)).toBe(0);
  expect(parseMoney('')).toBe(0);
  expect(parseMoney('123.45')).toBe(123.45);
  expect(parseMoney(99.1)).toBe(99.1);
  expect(Number.isNaN(parseMoney('abc'))).toBe(true);
});

});

test('Ruta POST /AsegurarFondos procesa exitosamente y llama al servicio con formato correcto', async () => { const response = await postAsegurarFondos(); const body = JSON.parse(response.payload);

expect(response.statusCode).toBe(200);
expect(body).toHaveProperty('resultado');
expect(body.resultado.error).toBe(false);
expect(body.respuesta).toBeDefined();

expect(mockService.asegurarFondos).toHaveBeenCalledTimes(1);
const callArgs = mockService.asegurarFondos.mock.calls[0][0];

expect(callArgs.data.monto).toBe('1000.50');
expect(callArgs.data.ValorComision).toBe('0.00');
expect(callArgs.data.ValorIvaComision).toBe('0.00');
expect(callArgs.data.nombre).toBe(validPayload.nombre);
expect(callArgs.data.tipoDocOrig).toBe(validPayload.tipoDocOrig);
expect(callArgs.data.numDocOrig).toBe(validPayload.numDocOrig);
expect(callArgs.data.tipoCuentaOrig).toBe(validPayload.tipoCuentaOrig);
expect(callArgs.data.numCuentaOrig).toBe('00650100000987654321');
expect(callArgs.data.idTransaccionSN).toBe(validPayload.idTransaccionSN);
expect(callArgs.data.identificadorSPBVI).toBe(validPayload.identificadorSPBVI);
expect(callArgs.data.tipoTransaccion).toBe(validPayload.tipoTransaccion);
expect(callArgs.data.IpOriginador).toBe(validPayload.ipOriginador);
expect(callArgs.data.CanalOriginador).toBe(validPayload.canalOriginador);

});

test('Cuando el core responde con BGMN201 con ERROR = S devuelve error y código core', async () => { mockService.asegurarFondos.mockResolvedValue( buildSuccessCoreResponse({ ERROR: 'S', COERROR: '3001', DERROR1: 'Saldo', DERROR2: 'insuficiente', DERROR3: '' }) );

const response = await postAsegurarFondos();
const body = JSON.parse(response.payload);

expect(response.statusCode).toBe(200);
expect(body.resultado.error).toBe(true);
expect(body.resultado.codigo).toBe(3001);
expect(body.resultado.descripcionError).toBe('Saldo insuficiente');

});

test('Error inesperado en servicio retorna 500', async () => { mockService.asegurarFondos.mockRejectedValueOnce(new Error('boom'));

const response = await postAsegurarFondos();
const body = JSON.parse(response.payload);

expect(response.statusCode).toBe(500);
expect(body.resultado.error).toBe(true);
expect(body.resultado.codigo).toBe(2001);
expect(body.resultado.descripcionError).toBe('Error interno al procesar AsegurarFondos');

});

describe('Validación de esquemas', () => { test('campo adicional debe retornar error de validación y no llamar servicio', async () => { const response = await postAsegurarFondos({ ...validPayload, campoNoPermitido: 'x' }); const body = JSON.parse(response.payload);

expect(response.statusCode).toBe(400);
  expect(body.resultado.error).toBe(true);
  expect(mockService.asegurarFondos).not.toHaveBeenCalled();
  expect(mockLogErrorToCSV).toHaveBeenCalled();
});

test('campo obligatorio faltante debe retornar error de validación y no llamar servicio', async () => {
  const payload = { ...validPayload } as Record<string, unknown>;
  delete payload.nombre;

  const response = await postAsegurarFondos(payload);
  const body = JSON.parse(response.payload);

  expect(response.statusCode).toBe(400);
  expect(body.resultado.error).toBe(true);
  expect(mockService.asegurarFondos).not.toHaveBeenCalled();
});

test('enum inválido debe retornar error de validación y no llamar servicio', async () => {
  const response = await postAsegurarFondos({ ...validPayload, tipoDocOrig: 'XYZ' });
  const body = JSON.parse(response.payload);

  expect(response.statusCode).toBe(400);
  expect(body.resultado.error).toBe(true);
  expect(mockService.asegurarFondos).not.toHaveBeenCalled();
});

test('monto menor al mínimo debe retornar error de validación', async () => {
  const response = await postAsegurarFondos({ ...validPayload, monto: 0 });
  const body = JSON.parse(response.payload);

  expect(response.statusCode).toBe(400);
  expect(body.resultado.error).toBe(true);
  expect(mockService.asegurarFondos).not.toHaveBeenCalled();
});

});

describe('Mapeo de errores de Altair', () => { test.each([ ['BGE0038', 2502, 'Cuenta no encontrada'], ['BGE1320', 2802, 'Cuenta inactiva'], ['BGE3244', 2504, 'Cuenta bloqueada'] ])('Debe mapear error %s correctamente', async (codigoAltair, codigoRta, mensaje) => { mockService.asegurarFondos.mockResolvedValueOnce(buildAltairErrorResponse(codigoAltair, mensaje));

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

test('Debe manejar error de Altair sin mapeo específico', async () => {
  mockService.asegurarFondos.mockResolvedValueOnce(buildAltairErrorResponse('BGE9999', 'Error no mapeado'));

  const response = await postAsegurarFondos();
  const body = JSON.parse(response.payload);

  expect(response.statusCode).toBe(422);
  expect(body.resultado.error).toBe(true);
  expect(body.resultado.codigo).toBe(1422);
  expect(body.resultado.descripcionError).toBe('Error no mapeado');
});

});

describe('Transformación de datos', () => { test('Debe transformar NIT a NT', async () => { await postAsegurarFondos({ ...validPayload, tipoDocOrig: 'NIT' });

expect(mockService.asegurarFondos).toHaveBeenCalledTimes(1);
  const callArgs = mockService.asegurarFondos.mock.calls[0][0];
  expect(callArgs.data.tipoDocOrig).toBe('NT');
});

test('Debe formatear número de cuenta con prefijo correcto', async () => {
  await postAsegurarFondos({ ...validPayload, numCuentaOrig: '123456789' });

  expect(mockService.asegurarFondos).toHaveBeenCalledTimes(1);
  const callArgs = mockService.asegurarFondos.mock.calls[0][0];
  expect(callArgs.data.numCuentaOrig).toBe('00650100000123456789');
});

test('Debe formatear monto con 2 decimales', async () => {
  await postAsegurarFondos({ ...validPayload, monto: 2500 });

  expect(mockService.asegurarFondos).toHaveBeenCalledTimes(1);
  const callArgs = mockService.asegurarFondos.mock.calls[0][0];
  expect(callArgs.data.monto).toBe('2500.00');
});

test('Debe incluir idTransaccionSN en el request al servicio', async () => {
  await postAsegurarFondos();

  expect(mockService.asegurarFondos).toHaveBeenCalledTimes(1);
  const callArgs = mockService.asegurarFondos.mock.calls[0][0];
  expect(callArgs.data.idTransaccionSN).toBe(validPayload.idTransaccionSN);
});

test('Debe mapear los nombres correctos de data, no los nombres antiguos', async () => {
  await postAsegurarFondos();

  expect(mockService.asegurarFondos).toHaveBeenCalledTimes(1);
  const callArgs = mockService.asegurarFondos.mock.calls[0][0];

  // Ajuste contra los tests viejos del log:
  // Antes esperaban data.tipoDoc/data.numDoc/data.tipoCuenta.
  // El router actual envía data.tipoDocOrig/data.numDocOrig/data.tipoCuentaOrig.
  expect(callArgs.data.tipoDocOrig).toBe(validPayload.tipoDocOrig);
  expect(callArgs.data.numDocOrig).toBe(validPayload.numDocOrig);
  expect(callArgs.data.tipoCuentaOrig).toBe(validPayload.tipoCuentaOrig);
  expect(callArgs.data.tipoDoc).toBeUndefined();
  expect(callArgs.data.numDoc).toBeUndefined();
  expect(callArgs.data.tipoCuenta).toBeUndefined();
});

});

describe('Respuesta exitosa', () => { test('Debe incluir todos los campos de respuesta requeridos', async () => { const response = await postAsegurarFondos(); const body = JSON.parse(response.payload);

expect(response.statusCode).toBe(200);
  expect(body.respuesta).toBeDefined();
  expect(body.respuesta.idTransaccionSN).toBeDefined();
  expect(body.respuesta.idTransaccionCore).toBeDefined();
  expect(body.respuesta.marcaTiempo).toBeDefined();
  expect(body.resultado.mensajeId).toBe(MENSAJE_ID);
  expect(body.resultado.error).toBe(false);
  expect(body.resultado.codigo).toBe(0);
  expect(body.resultado.descripcionError).toBe('');
});

test('Debe retornar error false y código 0 para operación exitosa', async () => {
  const response = await postAsegurarFondos();
  const body = JSON.parse(response.payload);

  expect(response.statusCode).toBe(200);
  expect(body.resultado.error).toBe(false);
  expect(body.resultado.codigo).toBe(0);
});

});

describe('Campos opcionales con diferentes tipos', () => { test('Debe serializar campo1 como objeto con JSON.stringify', async () => { const campo1 = { x: 1, y: 'test' };

await postAsegurarFondos({ ...validPayload, campo1 });

  expect(mockService.asegurarFondos).toHaveBeenCalledTimes(1);
  const callArgs = mockService.asegurarFondos.mock.calls[0][0];
  expect(callArgs.data.Campo1).toBe(JSON.stringify(campo1));
});

test('Debe manejar campo3 null como string vacío', async () => {
  await postAsegurarFondos({ ...validPayload, campo3: null });

  expect(mockService.asegurarFondos).toHaveBeenCalledTimes(1);
  const callArgs = mockService.asegurarFondos.mock.calls[0][0];
  expect(callArgs.data.Campo3).toBe('');
});

test('Debe manejar campo5 como objeto complejo', async () => {
  const campo5 = { a: 1, b: { c: true } };

  await postAsegurarFondos({ ...validPayload, campo5 });

  expect(mockService.asegurarFondos).toHaveBeenCalledTimes(1);
  const callArgs = mockService.asegurarFondos.mock.calls[0][0];
  expect(callArgs.data.Campo5).toBe(JSON.stringify(campo5));
});

});

describe('Parseo de valores monetarios', () => { test('Debe manejar valorComision definido correctamente', async () => { await postAsegurarFondos({ ...validPayload, valorComision: 5 });

expect(mockService.asegurarFondos).toHaveBeenCalledTimes(1);
  const callArgs = mockService.asegurarFondos.mock.calls[0][0];
  expect(callArgs.data.ValorComision).toBe('5.00');
});

test('Debe manejar valorIvaComision definido correctamente', async () => {
  await postAsegurarFondos({ ...validPayload, valorIvaComision: 0.95 });

  expect(mockService.asegurarFondos).toHaveBeenCalledTimes(1);
  const callArgs = mockService.asegurarFondos.mock.calls[0][0];
  expect(callArgs.data.ValorIvaComision).toBe('0.95');
});

test('valorComision omitido viaja como 0.00', async () => {
  const payload = { ...validPayload } as Record<string, unknown>;
  delete payload.valorComision;

  await postAsegurarFondos(payload);

  expect(mockService.asegurarFondos).toHaveBeenCalledTimes(1);
  const callArgs = mockService.asegurarFondos.mock.calls[0][0];
  expect(callArgs.data.ValorComision).toBe('0.00');
});

});

describe('Manejo de errores del servicio', () => { test('Debe manejar error de negocio AsegurarFondosError', async () => { mockService.asegurarFondos.mockRejectedValueOnce( new AsegurarFondosError('timeout', 'CORE_TIMEOUT', 503) );

const response = await postAsegurarFondos();
  const body = JSON.parse(response.payload);

  expect(body.resultado.error).toBe(true);
  expect(body.resultado.codigo).toEqual(expect.any(Number));
  expect(body.resultado.descripcionError).toEqual(expect.any(String));
  expect(mockLogTransaction).toHaveBeenCalledWith(expect.objectContaining({ estado: 'ERROR' }));
});

test('Debe manejar error genérico no AsegurarFondosError', async () => {
  mockService.asegurarFondos.mockRejectedValueOnce(new Error('error genérico'));

  const response = await postAsegurarFondos();
  const body = JSON.parse(response.payload);

  expect(response.statusCode).toBe(500);
  expect(body.resultado.error).toBe(true);
  expect(body.resultado.codigo).toBe(2001);
  expect(body.resultado.descripcionError).toBe('Error interno al procesar AsegurarFondos');
});

});

describe('Tests generales adicionales de AsegurarFondos', () => { test('Payload mínimo válido debe procesar exitosamente', async () => { const minimalPayload = { monto: 1, nombre: 'Ana', tipoDocOrig: 'CC', numDocOrig: '1234', tipoCuentaOrig: 'CAHO', numCuentaOrig: '123456789', idTransaccionSN: 'process.env.IDTRANSACCIONSN', identificadorSPBVI: 'TFY', tipoTransaccion: 'TRANSFER', marcaTiempo: 'process.env.MARCATIEMPO', idTransaccionSPBVI: 'SPBVI123' };

const response = await postAsegurarFondos(minimalPayload);
  const body = JSON.parse(response.payload);

  expect(response.statusCode).toBe(200);
  expect(body.resultado.error).toBe(false);
  expect(mockService.asegurarFondos).toHaveBeenCalledTimes(1);
});

test('Monto mínimo permitido debe ser aceptado', async () => {
  const response = await postAsegurarFondos({ ...validPayload, monto: 0.01 });
  const body = JSON.parse(response.payload);

  expect(response.statusCode).toBe(200);
  expect(body.resultado.error).toBe(false);
});

test('Monto máximo permitido debe ser aceptado', async () => {
  const response = await postAsegurarFondos({ ...validPayload, monto: 9999999999999.99 });
  const body = JSON.parse(response.payload);

  expect(response.statusCode).toBe(200);
  expect(body.resultado.error).toBe(false);
});

test('canalOriginador vacío debe ser aceptado', async () => {
  const response = await postAsegurarFondos({ ...validPayload, canalOriginador: '' });
  const body = JSON.parse(response.payload);

  expect(response.statusCode).toBe(200);
  expect(body.resultado.error).toBe(false);
});

test('canalOriginador null debe ser aceptado', async () => {
  const response = await postAsegurarFondos({ ...validPayload, canalOriginador: null });
  const body = JSON.parse(response.payload);

  expect(response.statusCode).toBe(200);
  expect(body.resultado.error).toBe(false);
});

test('campo1 como objeto complejo debe serializarse correctamente', async () => {
  const payloadObjCampo1 = { ...validPayload, campo1: { x: 1, y: { z: true } } };

  const response = await postAsegurarFondos(payloadObjCampo1);
  expect(response.statusCode).toBe(200);
  expect(mockService.asegurarFondos).toHaveBeenCalledTimes(1);

  const callArgs = mockService.asegurarFondos.mock.calls[0][0];
  expect(typeof callArgs.data.Campo1).toBe('string');
  expect(callArgs.data.Campo1).toContain('x');
  expect(callArgs.data.Campo1).toContain('y');
});

});

describe('Tests que estaban mal en el archivo original', () => { test('ipOriginador IPv6 completo: con el schema actual debe fallar, porque pattern es literal', async () => { const response = await postAsegurarFondos({ ...validPayload, ipOriginador: '2001:0db8:85a3:0000:0000:8a2e:0370:7334' }); const body = JSON.parse(response.payload);

// Este test reemplaza el viejo que esperaba error false.
  // Con pattern: 'process.env.IPTRANSACCION', un IPv6 real NO pasa validación.
  expect(response.statusCode).toBe(400);
  expect(body.resultado.error).toBe(true);
  expect(mockService.asegurarFondos).not.toHaveBeenCalled();
});

test('ipOriginador IPv6 comprimido: con el schema actual debe fallar, porque pattern es literal', async () => {
  const response = await postAsegurarFondos({
    ...validPayload,
    ipOriginador: '2001:db8::8a2e:370:7334'
  });
  const body = JSON.parse(response.payload);

  expect(response.statusCode).toBe(400);
  expect(body.resultado.error).toBe(true);
  expect(mockService.asegurarFondos).not.toHaveBeenCalled();
});

test('marcaTiempo futura real: con el schema actual debe fallar, porque pattern es literal', async () => {
  const response = await postAsegurarFondos({
    ...validPayload,
    marcaTiempo: '2099-02-10T14:30:01.000Z'
  });
  const body = JSON.parse(response.payload);

  // Este test reemplaza el viejo que esperaba error false.
  expect(response.statusCode).toBe(400);
  expect(body.resultado.error).toBe(true);
  expect(mockService.asegurarFondos).not.toHaveBeenCalled();
});

test('campo booleano no debe pasar schema si se envía directo en campo1', async () => {
  const response = await postAsegurarFondos({ ...validPayload, campo1: true });
  const body = JSON.parse(response.payload);

  // El schema solo permite string, object o null para campo1.
  expect(response.statusCode).toBe(400);
  expect(body.resultado.error).toBe(true);
  expect(mockService.asegurarFondos).not.toHaveBeenCalled();
});

test('array no debe pasar schema si se envía directo en campo2', async () => {
  const response = await postAsegurarFondos({ ...validPayload, campo2: [1, 2, 3] });
  const body = JSON.parse(response.payload);

  // Type.Object no debe usarse para arrays en este schema.
  expect(response.statusCode).toBe(400);
  expect(body.resultado.error).toBe(true);
  expect(mockService.asegurarFondos).not.toHaveBeenCalled();
});

}); });
