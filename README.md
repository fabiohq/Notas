test('Debe mantener marcaTiempo cuando usa el valor válido del schema actual', async () => {
  await fastify.inject({
    method: 'POST',
    url: '/TransaccionOnUs',
    payload: {
      ...validPayload,
      marcaTiempo: 'process.env.MARCATIEMPO'
    }
  });

  expect(mockService.transaccionOnUs).toHaveBeenCalled();

  const callArgs = mockService.transaccionOnUs.mock.calls[0][0];
  expect(callArgs.data.marcaTiempo).toBe('process.env.MARCATIEMPO');
});



test('Debe mantener marcaTiempo con el valor permitido por el schema actual', async () => {
  await fastify.inject({
    method: 'POST',
    url: '/TransaccionOnUs',
    payload: {
      ...validPayload,
      marcaTiempo: 'process.env.MARCATIEMPO'
    }
  });

  expect(mockService.transaccionOnUs).toHaveBeenCalled();

  const callArgs = mockService.transaccionOnUs.mock.calls[0][0];
  expect(callArgs.data.marcaTiempo).toBe('process.env.MARCATIEMPO');
});