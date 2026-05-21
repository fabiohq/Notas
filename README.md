
const validPayload = {
  monto: 100000,
  valorComision: 0,
  valorIvaComision: 0,
  nombre: 'Juan',
  tipoDocOrig: 'CC',
  numDocOrig: '12345678901',
  tipoCuentaOrig: 'CAHO',
  numCuentaOrig: '987654321',
  idTransaccionSN: 'process.env.IDTRANSACCIONSN',
  identificadorSPBVI: 'TFY',
  tipoTransaccion: 'TRANSFER',
  marcaTiempo: 'process.env.MARCATIEMPO',
  hubConcentrador: 'HUB001',
  ipOriginador: 'process.env.IPTRANSACCION',
  canalOriginador: 'WEB',
  campo1: 'valor1',
  idTransaccionSPBVI: 'SPBVI123456'
} as any;


jest
  .mocked(asegurarFondosService.createAsegurarFondosService)
  .mockReturnValue(mockService);


expect(mockService.asegurarFondos).toHaveBeenCalledTimes(1);

const callArgs = mockService.asegurarFondos.mock.calls[0][0];


test('ipOriginador IPv6 completo debe fallar con el schema actual', async () => {
  mockService.asegurarFondos.mockResolvedValue(mockSuccessResponse);

  await fastify.register(asegurarFondosRouter);
  await fastify.ready();

  const response = await fastify.inject({
    method: 'POST',
    url: '/AsegurarFondos',
    payload: {
      ...validPayload,
      ipOriginador: '2001:0db8:85a3:0000:0000:8a2e:0370:7334'
    }
  });

  const body = JSON.parse(response.payload);

  expect(body.resultado.error).toBe(true);
  expect(body.resultado.codigo).toBe(1422);
  expect(mockService.asegurarFondos).not.toHaveBeenCalled();
});




test('marcaTiempo real con/sin Z debe fallar con el schema actual', async () => {
  const responseWithZ = await fastify.inject({
    method: 'POST',
    url: '/AsegurarFondos',
    payload: {
      ...validPayload,
      marcaTiempo: '2025-02-10T14:30:00.000Z'
    }
  });

  expect(JSON.parse(responseWithZ.payload).resultado.error).toBe(true);

  const responseWithoutZ = await fastify.inject({
    method: 'POST',
    url: '/AsegurarFondos',
    payload: {
      ...validPayload,
      marcaTiempo: '2025-02-10T14:30:00.000'
    }
  });

  expect(JSON.parse(responseWithoutZ.payload).resultado.error).toBe(true);
});



test('campo2 como array debe ser rechazado por schema', async () => {
  const response = await fastify.inject({
    method: 'POST',
    url: '/AsegurarFondos',
    payload: {
      ...validPayload,
      campo2: ['item1', 'item2']
    }
  });

  const body = JSON.parse(response.payload);

  expect(body.resultado.error).toBe(true);
  expect(body.resultado.codigo).toBe(1422);
  expect(mockService.asegurarFondos).not.toHaveBeenCalled();
});


test('campo3 como número debe ser rechazado por schema', async () => {
  const response = await fastify.inject({
    method: 'POST',
    url: '/AsegurarFondos',
    payload: {
      ...validPayload,
      campo3: 12345
    }
  });

  const body = JSON.parse(response.payload);

  expect(body.resultado.error).toBe(true);
  expect(mockService.asegurarFondos).not.toHaveBeenCalled();
});


test('campo4 como boolean debe ser rechazado por schema', async () => {
  const response = await fastify.inject({
    method: 'POST',
    url: '/AsegurarFondos',
    payload: {
      ...validPayload,
      campo4: true
    }
  });

  const body = JSON.parse(response.payload);

  expect(body.resultado.error).toBe(true);
  expect(mockService.asegurarFondos).not.toHaveBeenCalled();
});


test('monto como string debe ser rechazado por schema Type.Number', async () => {
  const response = await fastify.inject({
    method: 'POST',
    url: '/AsegurarFondos',
    payload: {
      ...validPayload,
      monto: '100000.50'
    }
  });

  const body = JSON.parse(response.payload);

  expect(body.resultado.error).toBe(true);
  expect(mockService.asegurarFondos).not.toHaveBeenCalled();
});
