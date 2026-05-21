test('Debe manejar campo3 como número convirtiéndolo a string', async () => {
  const payloadConNumeroCampo3 = {
    ...validPayload,
    campo3: 12345
  };

  await fastify.inject({
    method: 'POST',
    url: '/AsegurarFondos',
    payload: payloadConNumeroCampo3
  });

  expect(mockService.asegurarFondos).toHaveBeenCalled();

  const callArgs = mockService.asegurarFondos.mock.calls[0][0];

  expect(callArgs.data.Campo3).toBe('12345');
});


test('Debe manejar campo4 como boolean convirtiéndolo a string', async () => {
  const payloadConBooleanCampo4 = {
    ...validPayload,
    campo4: true
  };

  await fastify.inject({
    method: 'POST',
    url: '/AsegurarFondos',
    payload: payloadConBooleanCampo4
  });

  expect(mockService.asegurarFondos).toHaveBeenCalled();

  const callArgs = mockService.asegurarFondos.mock.calls[0][0];

  expect(callArgs.data.Campo4).toBe('true');
});



test('Debe parsear monto como string correctamente', async () => {
  const payloadConMontoString = {
    ...validPayload,
    monto: '100000.50'
  };

  const response = await fastify.inject({
    method: 'POST',
    url: '/AsegurarFondos',
    payload: payloadConMontoString
  });

  expect(response.statusCode).toBe(200);

  expect(mockService.asegurarFondos).toHaveBeenCalled();

  const callArgs = mockService.asegurarFondos.mock.calls[0][0];

  expect(callArgs.data.monto).toBe('100000.50');
});


test('marcaTiempo real con milisegundos debe fallar con el schema actual', async () => {
  const payloadConMilisegundos = {
    ...validPayload,
    marcaTiempo: '2025-02-10T14:30:00.123'
  };

  const response = await fastify.inject({
    method: 'POST',
    url: '/AsegurarFondos',
    payload: payloadConMilisegundos
  });

  const body = JSON.parse(response.payload);

  expect(body.resultado.error).toBe(true);
  expect(body.resultado.codigo).toBe(1422);
  expect(mockService.asegurarFondos).not.toHaveBeenCalled();
});

