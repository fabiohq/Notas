
> microservice-template@1.0.0 test
> cross-env NODE_ENV=test jest --detectOpenHandles --forceExit test/unitary/routers/AsegurarFondos.test.ts

 FAIL  test/unitary/routers/AsegurarFondos.test.ts (9.844 s)
  AsegurarFondos Router - POST /AsegurarFondos
    √ Debe registrar el router sin errores (445 ms)
    × Ruta POST /AsegurarFondos procesa exitosamente y llama al servicio con formato correcto (175 ms)
    √ Validación local (monto inválido) retorna código 1422 y error true (159 ms)
    √ Cuando el servicio retorna errores de Altair, se mapea y responde con código RTA (78 ms)
    √ Campos extra (additionalProperties) retorna CAMPO_INCORRECTO (73 ms)
    × Cuando el core responde con BGMN201 con ERROR = S devuelve error y código core (53 ms)
    × Error inesperado en servicio retorna 500 (53 ms)
    × Debe aceptar ipOriginador con formato IPv6 completo (52 ms)
    × Debe aceptar ipOriginador con formato IPv6 comprimido (48 ms)
    Validación de esquemas
      √ Debe rechazar payload sin campo requerido monto (51 ms)
      √ Debe rechazar tipoDocOrig inválido (42 ms)
      √ Debe rechazar identificadorSPBVI inválido (38 ms)
      √ Debe aceptar todos los valores válidos de tipoDocOrig (56 ms)
      √ Debe aceptar todos los valores válidos de tipoCuentaOrig (56 ms)
      √ Debe rechazar numDocOrig con formato incorrecto (36 ms)
      √ Debe rechazar numCuentaOrig con formato incorrecto (43 ms)
      √ Debe rechazar marcaTiempo con formato incorrecto (41 ms)
      √ Debe aceptar marcaTiempo con y sin Z al final (40 ms)
      √ Debe aceptar campos opcionales (44 ms)
    Mapeo de errores de Altair
      √ Debe manejar error de Altair sin mensaje (48 ms)
      √ Debe manejar error de Altair sin mapeo específico (código 1422) (43 ms)
      × Debe mapear error BGE0038 (Cuenta no encontrada) (42 ms)
      × Debe mapear error BGE1320 (Cuenta inactiva) (47 ms)
      × Debe mapear error BGE3244 (Cuenta bloqueada) (44 ms)
      √ Debe manejar error de Altair sin mapeo específico (42 ms)
    Transformación de datos
      × Debe transformar NIT a NT (40 ms)
      × Debe formatear número de cuenta con prefijo correcto (41 ms)
      × Debe formatear monto con 2 decimales (47 ms)
      × Debe incluir mensajeId en el request al servicio (52 ms)
    Manejo de errores del servicio
      × Debe manejar error de negocio (AsegurarFondosError) (44 ms)
      × Debe manejar error genérico (no AsegurarFondosError) (44 ms)
      √ Debe manejar AsegurarFondosError con código específico (42 ms)
      √ Debe manejar timeout del servicio (37 ms)
    Respuesta exitosa
      √ Debe incluir mensajeId en la respuesta (35 ms)
      × Debe incluir todos los campos de respuesta requeridos (33 ms)
      × Debe retornar error false y código 0 para operación exitosa (33 ms)
    Campos opcionales con diferentes tipos
      × Debe serializar campo1 como objeto con JSON.stringify (39 ms)
      √ Debe serializar campo2 como array con JSON.stringify (42 ms)
      × Debe manejar campo3 como número convirtiéndolo a string (41 ms)
      × Debe manejar campo4 como boolean convirtiéndolo a string (40 ms)
      × Debe manejar campo5 como objeto complejo (39 ms)
    Parseo de valores monetarios
      × Debe parsear monto como string correctamente (39 ms)
      × Debe manejar valorComision definido correctamente (45 ms)
      × Debe manejar valorIvaComision definido correctamente (42 ms)
      × Debe manejar valorComision como string (43 ms)
      √ Debe rechazar monto negativo (53 ms)
      √ Debe rechazar monto como string no numérico (36 ms)
      √ Debe rechazar monto null (49 ms)
      √ Debe rechazar monto undefined (57 ms)
      √ Debe rechazar monto como string vacío (50 ms)
    Formato de marcaTiempo
      √ Debe agregar milisegundos si no están presentes (48 ms)
      × Debe mantener milisegundos si ya están presentes (44 ms)
    Manejo de errores específicos de servicio
      × Debe manejar CORE_TIMEOUT con código 504 (42 ms)
      × Debe manejar CORE_CONNECTION_ERROR con código 503 (49 ms)
      × Debe manejar INVALID_AUTH_TOKEN con código 401 (40 ms)
      √ Debe manejar EXTERNAL_SERVICE_ERROR genérico (42 ms)
    Cobertura de catch de logTransaction en errores de negocio y genéricos
      × Debe cubrir catch de logTransaction en error de negocio (AsegurarFondosError) (63 ms)
      × Debe cubrir catch de logTransaction en error genérico (no AsegurarFondosError) (50 ms)
    Tests generales adicionales de AsegurarFondos
      × Payload mínimo válido debe procesar exitosamente (51 ms)
      √ Payload máximo válido debe procesar exitosamente (48 ms)
      × Monto mínimo permitido debe ser aceptado (46 ms)
      × Monto máximo permitido debe ser aceptado (49 ms)
      × canalOriginador vacío debe ser aceptado (47 ms)
      × canalOriginador null debe ser aceptado (52 ms)
      × campo1 como objeto complejo debe serializarse correctamente (53 ms)
      × marcaTiempo futura debe ser aceptada (47 ms)
      √ CampoLibre0 demasiado largo debe retornar error de validación (55 ms)
    Cobertura de catch de logTransaction en éxito
      √ Debe cubrir catch de logTransaction en éxito (61 ms)
    Cobertura edge de validación local y parseMoney
      √ Debe rechazar monto como string no numérico (parseMoney NaN) (51 ms)
      √ Debe rechazar monto null (48 ms)
      √ Debe rechazar monto undefined (49 ms)
      √ Debe rechazar monto como string vacío (37 ms)
    Cobertura de campos opcionales y reply de error genérico
      × Debe aceptar campo1 booleano y serializarlo (50 ms)
      × Debe aceptar campo2 como array y serializarlo (49 ms)
      × Debe aceptar campo3 como objeto y serializarlo (45 ms)
      √ Debe retornar error genérico si el reply de error no tiene mapeo (48 ms)
    Funciones utilitarias de AsegurarFondos
      √ mapTipoDocumento transforma NIT a NT (7 ms)
      √ mapTipoDocumento deja otros valores igual (9 ms)
      √ formatNumCuenta agrega padding y prefijos (8 ms)
      √ formatNumCuenta con cuenta vacía (9 ms)
      √ formatMarcaTiempo agrega .000 si no hay punto (6 ms)
      √ formatMarcaTiempo deja igual si ya tiene punto (8 ms)
      √ mapTipoDocumento: NIT se transforma en NT (9 ms)
      √ formatNumCuenta: rellena y concatena correctamente (8 ms)
      √ formatMarcaTiempo: agrega milisegundos si faltan (7 ms)
      √ formatMarcaTiempo: edge cases (9 ms)
    Cobertura de ramas y errores adicionales
      √ Debe cubrir catch de logTransaction en validación local (campo incorrecto) (57 ms)
      √ Debe cubrir catch de logTransaction en validación local (campo obligatorio) (44 ms)
      √ Debe cubrir catch de logTransaction en error monto mal informado (44 ms)
      √ Debe cubrir catch de logTransaction en error Altair (44 ms)
      √ Debe cubrir catch de logTransaction en error BGMN201 (49 ms)
      √ Debe cubrir catch de logTransaction en error genérico (49 ms)

  ● AsegurarFondos Router - POST /AsegurarFondos › Ruta POST /AsegurarFondos procesa exitosamente y llama al servicio con formato correcto

    expect(received).toBe(expected) // Object.is equality

    Expected: false
    Received: true

      132 |     const body = JSON.parse(response.payload);
      133 |     expect(body).toHaveProperty('resultado');
    > 134 |     expect(body.resultado.error).toBe(false);
          |                                  ^
      135 |     expect(body.respuesta).toBeDefined();
      136 |
      137 |     // Verificar que el servicio fue llamado con monto formateado y cuenta con prefijo

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:134:34)

  ● AsegurarFondos Router - POST /AsegurarFondos › Cuando el core responde con BGMN201 con ERROR = S devuelve error y código core

    expect(received).toBe(expected) // Object.is equality

    Expected: 3001
    Received: 1422

      230 |     const body = JSON.parse(response.payload);
      231 |     expect(body.resultado.error).toBe(true);
    > 232 |     expect(body.resultado.codigo).toBe(3001);
          |                                   ^
      233 |   });
      234 |
      235 |   test('Error inesperado en servicio retorna 500', async () => {

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:232:35)

  ● AsegurarFondos Router - POST /AsegurarFondos › Error inesperado en servicio retorna 500

    expect(received).toBe(expected) // Object.is equality

    Expected: 500
    Received: 200

      240 |
      241 |     const response = await fastify.inject({ method: 'POST', url: '/AsegurarFondos', payload: validPayload });
    > 242 |     expect(response.statusCode).toBe(500);
          |                                 ^
      243 |     const body = JSON.parse(response.payload);
      244 |     expect(body.resultado.error).toBe(true);
      245 |     expect(body.resultado.codigo).toBe(2001);

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:242:33)

  ● AsegurarFondos Router - POST /AsegurarFondos › Debe aceptar ipOriginador con formato IPv6 completo

    expect(received).toBe(expected) // Object.is equality

    Expected: false
    Received: true

      257 |     expect(response.statusCode).toBe(200);
      258 |     const body = JSON.parse(response.payload);
    > 259 |     expect(body.resultado.error).toBe(false);
          |                                  ^
      260 |   });
      261 |
      262 |   test('Debe aceptar ipOriginador con formato IPv6 comprimido', async () => {

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:259:34)

  ● AsegurarFondos Router - POST /AsegurarFondos › Debe aceptar ipOriginador con formato IPv6 comprimido

    expect(received).toBe(expected) // Object.is equality

    Expected: false
    Received: true

      271 |     expect(response.statusCode).toBe(200);
      272 |     const body = JSON.parse(response.payload);
    > 273 |     expect(body.resultado.error).toBe(false);
          |                                  ^
      274 |   });
      275 |
      276 |   describe('Validación de esquemas', () => {

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:273:34)

  ● AsegurarFondos Router - POST /AsegurarFondos › Mapeo de errores de Altair › Debe mapear error BGE0038 (Cuenta no encontrada)

    expect(received).toBe(expected) // Object.is equality

    Expected: 2502
    Received: 1422

      526 |       const body = JSON.parse(response.payload);
      527 |       expect(body.resultado.error).toBe(true);
    > 528 |       expect(body.resultado.codigo).toBe(2502);
          |                                     ^
      529 |     });
      530 |
      531 |     test('Debe mapear error BGE1320 (Cuenta inactiva)', async () => {

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:528:37)

  ● AsegurarFondos Router - POST /AsegurarFondos › Mapeo de errores de Altair › Debe mapear error BGE1320 (Cuenta inactiva)

    expect(received).toBe(expected) // Object.is equality

    Expected: 2802
    Received: 1422

      546 |       const body = JSON.parse(response.payload);
      547 |       expect(body.resultado.error).toBe(true);
    > 548 |       expect(body.resultado.codigo).toBe(2802);
          |                                     ^
      549 |     });
      550 |
      551 |     test('Debe mapear error BGE3244 (Cuenta bloqueada)', async () => {

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:548:37)

  ● AsegurarFondos Router - POST /AsegurarFondos › Mapeo de errores de Altair › Debe mapear error BGE3244 (Cuenta bloqueada)

    expect(received).toBe(expected) // Object.is equality

    Expected: 2504
    Received: 1422

      566 |       const body = JSON.parse(response.payload);
      567 |       expect(body.resultado.error).toBe(true);
    > 568 |       expect(body.resultado.codigo).toBe(2504);
          |                                     ^
      569 |     });
      570 |
      571 |     test('Debe manejar error de Altair sin mapeo específico', async () => {

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:568:37)

  ● AsegurarFondos Router - POST /AsegurarFondos › Transformación de datos › Debe transformar NIT a NT

    TypeError: Cannot read properties of undefined (reading '0')

      605 |       });
      606 |
    > 607 |       const callArgs = mockService.asegurarFondos.mock.calls[0][0];
          |                                                                ^
      608 |       expect(callArgs.data.tipoDocOrig).toBe('NT');
      609 |     });
      610 |

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:607:64)

  ● AsegurarFondos Router - POST /AsegurarFondos › Transformación de datos › Debe formatear número de cuenta con prefijo correcto

    TypeError: Cannot read properties of undefined (reading '0')

      616 |       });
      617 |
    > 618 |       const callArgs = mockService.asegurarFondos.mock.calls[0][0];
          |                                                                ^
      619 |       expect(callArgs.data.numCuentaOrig).toMatch(/^00650100[0-9]{12}$/);
      620 |       expect(callArgs.data.numCuentaOrig.length).toBe(20);
      621 |     });

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:618:64)

  ● AsegurarFondos Router - POST /AsegurarFondos › Transformación de datos › Debe formatear monto con 2 decimales

    TypeError: Cannot read properties of undefined (reading '0')

      628 |       });
      629 |
    > 630 |       const callArgs = mockService.asegurarFondos.mock.calls[0][0];
          |                                                                ^
      631 |       expect(typeof callArgs.data.monto).toBe('string');
      632 |       expect(callArgs.data.monto).toMatch(/^\d+\.\d{2}$/);
      633 |     });

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:630:64)

  ● AsegurarFondos Router - POST /AsegurarFondos › Transformación de datos › Debe incluir mensajeId en el request al servicio

    TypeError: Cannot read properties of undefined (reading '0')

      640 |       });
      641 |
    > 642 |       const callArgs = mockService.asegurarFondos.mock.calls[0][0];
          |                                                                ^
      643 |       expect(callArgs.cabecera).toBeDefined();
      644 |       expect(callArgs.data).toBeDefined();
      645 |     });

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:642:64)

  ● AsegurarFondos Router - POST /AsegurarFondos › Manejo de errores del servicio › Debe manejar error de negocio (AsegurarFondosError)

    expect(received).toBe(expected) // Object.is equality

    Expected: 500
    Received: 200

      660 |         payload: validPayload
      661 |       });
    > 662 |       expect(response.statusCode).toBe(500);
          |                                   ^
      663 |       const body = JSON.parse(response.payload);
      664 |       expect(body.resultado.error).toBe(true);
      665 |       expect(body.resultado.codigo).toBeDefined();

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:662:35)

  ● AsegurarFondos Router - POST /AsegurarFondos › Manejo de errores del servicio › Debe manejar error genérico (no AsegurarFondosError)

    expect(received).toBe(expected) // Object.is equality

    Expected: 500
    Received: 200

      674 |         payload: validPayload
      675 |       });
    > 676 |       expect(response.statusCode).toBe(500);
          |                                   ^
      677 |       const body = JSON.parse(response.payload);
      678 |       expect(body.resultado.error).toBe(true);
      679 |       expect(body.resultado.codigo).toBe(2001);

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:676:35)

  ● AsegurarFondos Router - POST /AsegurarFondos › Respuesta exitosa › Debe incluir todos los campos de respuesta requeridos

    expect(received).toHaveProperty(path)

    Expected path: "respuesta"
    Received path: []

    Received value: {"resultado": {"codigo": 1422, "descripcionError": "Error de validación. La información enviada en el payload contiene valores no válidos.", "error": true, "mensajeId": "3fa85f66-5717-4562-b3fc-2c963f66afa6"}}

      754 |
      755 |       const body = JSON.parse(response.payload);
    > 756 |       expect(body).toHaveProperty('respuesta');
          |                    ^
      757 |       expect(body).toHaveProperty('resultado');
      758 |       expect(body.respuesta).toHaveProperty('idTransaccionSN');
      759 |       expect(body.respuesta).toHaveProperty('idTransaccionCore');

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:756:20)

  ● AsegurarFondos Router - POST /AsegurarFondos › Respuesta exitosa › Debe retornar error false y código 0 para operación exitosa

    expect(received).toBe(expected) // Object.is equality

    Expected: false
    Received: true

      769 |
      770 |       const body = JSON.parse(response.payload);
    > 771 |       expect(body.resultado.error).toBe(false);
          |                                    ^
      772 |       expect(body.resultado.codigo).toBe(0);
      773 |       expect(body.resultado.descripcionError).toBe('');
      774 |     });

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:771:36)

  ● AsegurarFondos Router - POST /AsegurarFondos › Campos opcionales con diferentes tipos › Debe serializar campo1 como objeto con JSON.stringify

    TypeError: Cannot read properties of undefined (reading '0')

      791 |       });
      792 |
    > 793 |       const callArgs = mockService.asegurarFondos.mock.calls[0][0];
          |                                                                ^
      794 |       expect(typeof callArgs.data.Campo1).toBe('string');
      795 |       expect(callArgs.data.Campo1).toContain('key');
      796 |     });

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:793:64)

  ● AsegurarFondos Router - POST /AsegurarFondos › Campos opcionales con diferentes tipos › Debe manejar campo3 como número convirtiéndolo a string

    TypeError: Cannot read properties of undefined (reading '0')

      819 |       });
      820 |
    > 821 |       const callArgs = mockService.asegurarFondos.mock.calls[0][0];
          |                                                                ^
      822 |       expect(typeof callArgs.data.Campo3).toBe('string');
      823 |     });
      824 |

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:821:64)

  ● AsegurarFondos Router - POST /AsegurarFondos › Campos opcionales con diferentes tipos › Debe manejar campo4 como boolean convirtiéndolo a string

    TypeError: Cannot read properties of undefined (reading '0')

      832 |       });
      833 |
    > 834 |       const callArgs = mockService.asegurarFondos.mock.calls[0][0];
          |                                                                ^
      835 |       expect(typeof callArgs.data.Campo4).toBe('string');
      836 |     });
      837 |

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:834:64)

  ● AsegurarFondos Router - POST /AsegurarFondos › Campos opcionales con diferentes tipos › Debe manejar campo5 como objeto complejo

    TypeError: Cannot read properties of undefined (reading '0')

      845 |       });
      846 |
    > 847 |       const callArgs = mockService.asegurarFondos.mock.calls[0][0];
          |                                                                ^
      848 |       expect(typeof callArgs.data.Campo5).toBe('string');
      849 |       expect(callArgs.data.Campo5).toContain('nested');
      850 |     });

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:847:64)

  ● AsegurarFondos Router - POST /AsegurarFondos › Parseo de valores monetarios › Debe parsear monto como string correctamente

    TypeError: Cannot read properties of undefined (reading '0')

      868 |
      869 |       expect(response.statusCode).toBe(200);
    > 870 |       const callArgs = mockService.asegurarFondos.mock.calls[0][0];
          |                                                                ^
      871 |       expect(callArgs.data.monto).toBe('100000.50');
      872 |     });
      873 |

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:870:64)

  ● AsegurarFondos Router - POST /AsegurarFondos › Parseo de valores monetarios › Debe manejar valorComision definido correctamente

    TypeError: Cannot read properties of undefined (reading '0')

      881 |       });
      882 |
    > 883 |       const callArgs = mockService.asegurarFondos.mock.calls[0][0];
          |                                                                ^
      884 |       expect(callArgs.data.ValorComision).toBe('5000.00');
      885 |     });
      886 |

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:883:64)

  ● AsegurarFondos Router - POST /AsegurarFondos › Parseo de valores monetarios › Debe manejar valorIvaComision definido correctamente

    TypeError: Cannot read properties of undefined (reading '0')

      894 |       });
      895 |
    > 896 |       const callArgs = mockService.asegurarFondos.mock.calls[0][0];
          |                                                                ^
      897 |       expect(callArgs.data.ValorIvaComision).toBe('950.00');
      898 |     });
      899 |

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:896:64)

  ● AsegurarFondos Router - POST /AsegurarFondos › Parseo de valores monetarios › Debe manejar valorComision como string

    TypeError: Cannot read properties of undefined (reading '0')

      907 |       });
      908 |
    > 909 |       const callArgs = mockService.asegurarFondos.mock.calls[0][0];
          |                                                                ^
      910 |       expect(callArgs.data.ValorComision).toBe('3500.75');
      911 |     });
      912 |

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:909:64)

  ● AsegurarFondos Router - POST /AsegurarFondos › Formato de marcaTiempo › Debe mantener milisegundos si ya están presentes

    TypeError: Cannot read properties of undefined (reading '0')

      1015 |       });
      1016 |
    > 1017 |       const callArgs = mockService.asegurarFondos.mock.calls[0][0];
           |                                                                ^
      1018 |       expect(callArgs.data.marcaTiempo).toBe('2025-02-10T14:30:00.000');
      1019 |     });
      1020 |   });

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:1017:64)

  ● AsegurarFondos Router - POST /AsegurarFondos › Manejo de errores específicos de servicio › Debe manejar CORE_TIMEOUT con código 504

    expect(received).toBe(expected) // Object.is equality

    Expected: 500
    Received: 200

      1040 |       });
      1041 |       // The router returns status 500 for this error, but the code should be 500 as well
    > 1042 |       expect(response.statusCode).toBe(500);
           |                                   ^
      1043 |       const body = JSON.parse(response.payload);
      1044 |       expect(body.resultado.error).toBe(true);
      1045 |       expect(body.resultado.codigo).toBe(500);

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:1042:35)

  ● AsegurarFondos Router - POST /AsegurarFondos › Manejo de errores específicos de servicio › Debe manejar CORE_CONNECTION_ERROR con código 503

    expect(received).toBe(expected) // Object.is equality

    Expected: 500
    Received: 200

      1060 |       });
      1061 |       // The router returns staPErotus 500 for this error, but the code should be 500 as well
    > 1062 |       expect(response.statusCode).toBe(500);
           |                                   ^
      1063 |       const body = JSON.parse(response.payload);
      1064 |       expect(body.resultado.error).toBe(true);
      1065 |       expect(body.resultado.codigo).toBe(500);

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:1062:35)

  ● AsegurarFondos Router - POST /AsegurarFondos › Manejo de errores específicos de servicio › Debe manejar INVALID_AUTH_TOKEN con código 401

    expect(received).toBe(expected) // Object.is equality

    Expected: 500
    Received: 200

      1080 |       });
      1081 |       // The router returns status 500 for this error, but the code should be 500 as well
    > 1082 |       expect(response.statusCode).toBe(500);
           |                                   ^
      1083 |       const body = JSON.parse(response.payload);
      1084 |       expect(body.resultado.error).toBe(true);
      1085 |       expect(body.resultado.codigo).toBe(500);

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:1082:35)

  ● AsegurarFondos Router - POST /AsegurarFondos › Cobertura de catch de logTransaction en errores de negocio y genéricos › Debe cubrir catch de logTransaction en error de negocio (AsegurarFondosError)

    expect(received).toBe(expected) // Object.is equality

    Expected: 500
    Received: 1422

      1148 |       const body = JSON.parse(response.payload);
      1149 |       expect(body.resultado.error).toBe(true);
    > 1150 |       expect(body.resultado.codigo).toBe(500);
           |                                     ^
      1151 |       expect(response.statusCode).toBe(500);
      1152 |     });
      1153 |

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:1150:37)

  ● AsegurarFondos Router - POST /AsegurarFondos › Cobertura de catch de logTransaction en errores de negocio y genéricos › Debe cubrir catch de logTransaction en error genérico (no AsegurarFondosError)

    expect(received).toBe(expected) // Object.is equality

    Expected: 2001
    Received: 1422

      1162 |       const body = JSON.parse(response.payload);
      1163 |       expect(body.resultado.error).toBe(true);
    > 1164 |       expect(body.resultado.codigo).toBe(2001);
           |                                     ^
      1165 |       expect(response.statusCode).toBe(500);
      1166 |     });
      1167 |   });

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:1164:37)

  ● AsegurarFondos Router - POST /AsegurarFondos › Tests generales adicionales de AsegurarFondos › Payload mínimo válido debe procesar exitosamente

    expect(received).toBe(expected) // Object.is equality

    Expected: false
    Received: true

      1211 |       expect(response.statusCode).toBe(200);
      1212 |       const body = JSON.parse(response.payload);
    > 1213 |       expect(body.resultado.error).toBe(false);
           |                                    ^
      1214 |       expect(body.respuesta).toBeDefined();
      1215 |     });
      1216 |

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:1213:36)

  ● AsegurarFondos Router - POST /AsegurarFondos › Tests generales adicionales de AsegurarFondos › Monto mínimo permitido debe ser aceptado

    expect(received).toBe(expected) // Object.is equality

    Expected: false
    Received: true

      1240 |       expect(response.statusCode).toBe(200);
      1241 |       const body = JSON.parse(response.payload);
    > 1242 |       expect(body.resultado.error).toBe(false);
           |                                    ^
      1243 |     });
      1244 |
      1245 |     test('Monto máximo permitido debe ser aceptado', async () => {

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:1242:36)

  ● AsegurarFondos Router - POST /AsegurarFondos › Tests generales adicionales de AsegurarFondos › Monto máximo permitido debe ser aceptado

    expect(received).toBe(expected) // Object.is equality

    Expected: false
    Received: true

      1249 |       expect(response.statusCode).toBe(200);
      1250 |       const body = JSON.parse(response.payload);
    > 1251 |       expect(body.resultado.error).toBe(false);
           |                                    ^
      1252 |     });
      1253 |
      1254 |     test('canalOriginador vacío debe ser aceptado', async () => {

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:1251:36)

  ● AsegurarFondos Router - POST /AsegurarFondos › Tests generales adicionales de AsegurarFondos › canalOriginador vacío debe ser aceptado

    expect(received).toBe(expected) // Object.is equality

    Expected: false
    Received: true

      1258 |       expect(response.statusCode).toBe(200);
      1259 |       const body = JSON.parse(response.payload);
    > 1260 |       expect(body.resultado.error).toBe(false);
           |                                    ^
      1261 |     });
      1262 |
      1263 |     test('canalOriginador null debe ser aceptado', async () => {

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:1260:36)

  ● AsegurarFondos Router - POST /AsegurarFondos › Tests generales adicionales de AsegurarFondos › canalOriginador null debe ser aceptado

    expect(received).toBe(expected) // Object.is equality

    Expected: false
    Received: true

      1267 |       expect(response.statusCode).toBe(200);
      1268 |       const body = JSON.parse(response.payload);
    > 1269 |       expect(body.resultado.error).toBe(false);
           |                                    ^
      1270 |     });
      1271 |
      1272 |     test('campo1 como objeto complejo debe serializarse correctamente', async () => {

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:1269:36)

  ● AsegurarFondos Router - POST /AsegurarFondos › Tests generales adicionales de AsegurarFondos › campo1 como objeto complejo debe serializarse correctamente

    TypeError: Cannot read properties of undefined (reading '0')

      1275 |       const response = await fastify.inject({ method: 'POST', url: '/AsegurarFondos', payload: payloadObjCampo1 });
      1276 |       expect(response.statusCode).toBe(200);
    > 1277 |       const callArgs = mockService.asegurarFondos.mock.calls[0][0];
           |                                                                ^
      1278 |       expect(typeof callArgs.data.Campo1).toBe('string');
      1279 |       expect(callArgs.data.Campo1).toContain('x');
      1280 |       expect(callArgs.data.Campo1).toContain('y');

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:1277:64)

  ● AsegurarFondos Router - POST /AsegurarFondos › Tests generales adicionales de AsegurarFondos › marcaTiempo futura debe ser aceptada

    expect(received).toBe(expected) // Object.is equality

    Expected: false
    Received: true

      1288 |       expect(response.statusCode).toBe(200);
      1289 |       const body = JSON.parse(response.payload);
    > 1290 |       expect(body.resultado.error).toBe(false);
           |                                    ^
      1291 |     });
      1292 |
      1293 |     test('CampoLibre0 demasiado largo debe retornar error de validación', async () => {

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:1290:36)

  ● AsegurarFondos Router - POST /AsegurarFondos › Cobertura de campos opcionales y reply de error genérico › Debe aceptar campo1 booleano y serializarlo

    TypeError: Cannot read properties of undefined (reading '0')

      1385 |       expect([500]).toContain(response.statusCode);
      1386 |       if (response.statusCode === 500) {
    > 1387 |         const callArgs = mockService.asegurarFondos.mock.calls[0][0];
           |                                                                  ^
      1388 |         expect(callArgs.data.Campo1).toBe('true');
      1389 |       }
      1390 |     });

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:1387:66)

  ● AsegurarFondos Router - POST /AsegurarFondos › Cobertura de campos opcionales y reply de error genérico › Debe aceptar campo2 como array y serializarlo

    TypeError: Cannot read properties of undefined (reading '0')

      1395 |       expect([500]).toContain(response.statusCode);
      1396 |       if (response.statusCode === 500) {
    > 1397 |         const callArgs = mockService.asegurarFondos.mock.calls[0][0];
           |                                                                  ^
      1398 |         expect(callArgs.data.Campo2).toBe('[1,2,3]');
      1399 |       }
      1400 |     });

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:1397:66)

  ● AsegurarFondos Router - POST /AsegurarFondos › Cobertura de campos opcionales y reply de error genérico › Debe aceptar campo3 como objeto y serializarlo

    TypeError: Cannot read properties of undefined (reading '0')

      1405 |       expect([500]).toContain(response.statusCode);
      1406 |       if (response.statusCode === 500) {
    > 1407 |         const callArgs = mockService.asegurarFondos.mock.calls[0][0];
           |                                                                  ^
      1408 |         expect(callArgs.data.Campo3).toBe('{"a":1}');
      1409 |       }
      1410 |     });

      at Object.<anonymous> (test/unitary/routers/AsegurarFondos.test.ts:1407:66)

Test Suites: 1 failed, 1 total
Tests:       40 failed, 52 passed, 92 total
Snapshots:   0 total
Time:        10.624 s, estimated 14 s
Ran all test suites matching /test\\unitary\\routers\\AsegurarFondos.test.ts/i.
PS C:\Users\n595194\Downloads\Nueva carpeta\bnc-bsn0057-spitransaccionalautorizador>
