PS C:\Users\x830582\Downloads\Transaccional 200526\bnc-bsn0057-spitransaccionalautorizador> npm test -- test/unitary/server/index.test.ts    

  

> microservice-template@1.0.0 test 

> cross-env NODE_ENV=test jest --detectOpenHandles --forceExit test/unitary/server/index.test.ts 

  

PASS  test/unitary/server/index.test.ts (53.673 s) 

  Server - Inicialización y Configuración 

    Inicialización del Servidor 

      √ debería crear una instancia válida de Fastify (13051 ms) 

      √ debería tener la configuración de Ajv correcta (785 ms) 

      √ debería tener configurado el logger (146 ms) 

      √ debería tener el decorador mensajeId en el request (168 ms) 

    Hook onRequest - Generación de mensajeId 

      √ debería generar un mensajeId único para cada request (217 ms) 

      √ debería adjuntar el mensajeId al objeto request (191 ms) 

      √ debería manejar el error cuando generateMessageId falla (132 ms) 

      √ debería manejar el error cuando generateMessageId retorna null (123 ms) 

      √ debería generar un mensajeId diferente para cada request (159 ms) 

    Health Check Endpoint 

      √ debería responder con status 200 en /health (151 ms) 

      √ debería devolver status "ok" en /health (122 ms) 

      √ debería incluir timestamp en /health (119 ms) 

      √ debería incluir uptime del proceso en /health (200 ms) 

      √ debería responder correctamente a múltiples health checks (348 ms) 

    Error Handler Global 

      √ debería manejar errores de validación correctamente (435 ms) 

      √ debería manejar errores internos del servidor (183 ms) 

      √ debería manejar errores con statusCode personalizado (161 ms) 

      √ debería loggear el error con contexto completo (190 ms) 

    Not Found Handler 

      √ debería devolver 404 para rutas no existentes (103 ms) 

      √ debería devolver formato de error correcto para 404 (163 ms) 

      √ debería incluir el mensajeId en respuesta 404 (224 ms) 

      √ debería loggear warning para rutas no encontradas (119 ms) 

      √ debería manejar diferentes métodos HTTP en rutas inexistentes (165 ms) 

    Registro de Routers 

      √ debería registrar correctamente los routers de la aplicación (84 ms) 

      √ debería tener múltiples rutas disponibles (101 ms) 

    Configuración de Seguridad 

      √ debería tener configurado requestIdHeader (89 ms) 

      √ debería tener configurado requestIdLogLabel (71 ms) 

      √ debería tener disableRequestLogging en false (60 ms) 

    Manejo de Ciclo de Vida 

      √ debería poder cerrar el servidor correctamente (75 ms) 

      √ debería ejecutar el hook onRequest para cada request (125 ms) 

    Edge Cases y Validaciones 

      √ debería manejar payload vacío correctamente (153 ms) 

      √ debería manejar headers personalizados (129 ms) 

      √ debería manejar query parameters (147 ms) 

      √ debería manejar múltiples requests concurrentes (204 ms) 

    Integración con Error Codes 

      √ debería usar getErrorCodeNumber correctamente (111 ms) 

      √ debería usar getHttpStatusForError correctamente (114 ms) 

      √ debería usar getErrorDescription correctamente (120 ms) 

    Función start() - Inicio del Servidor 

      √ debería llamar a server.listen con configuración correcta (147 ms) 

      √ debería tener el método listen disponible para iniciar el servidor (99 ms) 

      √ debería tener el logger configurado para producción (98 ms) 

    Process Signal Handlers - Manejo de Señales 

      √ debería registrar handlers para SIGINT y SIGTERM (321 ms) 

      √ debería registrar handler para uncaughtException (318 ms) 

      √ debería registrar handler para unhandledRejection (263 ms) 

      √ debería manejar señal SIGINT correctamente (239 ms) 

      √ debería manejar señal SIGTERM correctamente (299 ms) 

      √ debería manejar uncaughtException correctamente (329 ms) 

      √ debería manejar unhandledRejection correctamente (292 ms) 

    Configuración de Entorno 

      √ debería configurar trustProxy cuando NODE_ENV es production (67 ms) 

      √ no debería iniciar el servidor cuando NODE_ENV es test (113 ms) 

    Error Validation con attachValidation 

      √ debería manejar error.validation cuando existe (147 ms) 

      √ debería incluir detalles de validación en la respuesta de error (173 ms) 

      √ debería manejar correctamente validaciones de tipo (186 ms) 

  

Test Suites: 1 passed, 1 total 

Tests:       52 passed, 52 total 

Snapshots:   0 total 

Time:        58.019 s 

Ran all test suites matching /test\\unitary\\server\\index.test.ts/i. 

PS C:\Users\x830582\Downloads\Transaccional 200526\bnc-bsn0057-spitransaccionalautorizador> npm test -- test/unitary/routers/TransaccionOnUs.test.ts    

  

> microservice-template@1.0.0 test 

> cross-env NODE_ENV=test jest --detectOpenHandles --forceExit test/unitary/routers/TransaccionOnUs.test.ts 

  

  console.log 

    Database pool created successfully: default 

  

      at DatabaseErrorLogger.initialize (src/lib/shared/databaseErrorLogger.ts:58:15) 

  

FAIL  test/unitary/routers/TransaccionOnUs.test.ts (59.745 s) 

  TransaccionOnUs Router - POST /TransaccionOnUs 

    Registro del router 

      √ Debe registrar el router sin errores (1679 ms) 

      √ Debe registrar la ruta POST /TransaccionOnUs (359 ms) 

    Casos exitosos 

      × Debe procesar una transacción OnUs exitosamente (2534 ms) 

      × Debe llamar al servicio con los datos correctos (285 ms) 

      × Debe incluir idTransaccionSN en la respuesta (620 ms) 

      × Debe manejar campos opcionales nulos correctamente (394 ms) 

      × Debe transformar NIT a NT en tipoDocDest (237 ms) 

      × Debe transformar NIT a NT en tipoDocOrig (220 ms) 

    Validación de esquemas 

      √ Debe retornar 400 cuando falta el campo monto (225 ms) 

      √ Debe retornar 400 cuando el tipoDocDest es inválido (255 ms) 

      √ Debe retornar 400 cuando el tipoDocOrig es inválido (205 ms) 

      √ Debe retornar 400 cuando el monto es negativo (208 ms) 

      √ Debe retornar 400 cuando faltan múltiples campos requeridos (267 ms) 

      √ Debe retornar 400 cuando falta numDocDest (217 ms) 

      √ Debe retornar 400 cuando falta numCuentaDest (301 ms) 

    Manejo de errores del servicio 

      × Debe manejar TransaccionOnUsError y mapear códigos de error (474 ms) 

      √ Debe mapear error CORE_CONNECTION_ERROR correctamente (289 ms) 

      √ Debe mapear error INVALID_PAYLOAD correctamente (550 ms) 

      √ Debe mapear error TRANSACCION_VALIDATION_ERROR correctamente (199 ms) 

      × Debe manejar errores inesperados con código 500 (214 ms) 

      √ Debe incluir mensajeId en todos los errores del servicio (179 ms) 

    Trazabilidad y logging 

      √ Debe incluir mensajeId en todas las respuestas (118 ms) 

      √ Debe incluir mensajeId en respuestas de error de validación (127 ms) 

      √ Debe incluir mensajeId en respuestas de error del servicio (121 ms) 

    Integración con tipos y constantes 

      √ Debe aceptar todos los tipos de documento válidos (252 ms) 

      √ Debe aceptar todos los tipos de cuenta válidos para destino (222 ms) 

      √ Debe aceptar todos los tipos de cuenta válidos para origen (255 ms) 

    Estructura de la respuesta 

      × La respuesta exitosa debe tener la estructura correcta (127 ms) 

      √ La respuesta de error debe tener la estructura correcta (159 ms) 

    Transformaciones de datos 

      × Debe formatear numCuentaDest con padding de 20 dígitos (142 ms) 

      × Debe formatear numCuentaOrig con padding de 20 dígitos (146 ms) 

      × Debe mantener marcaTiempo cuando ya tiene milisegundos (147 ms) 

      × Debe enviar monto como number (137 ms) 

    Manejo de respuesta con error del core 

      × Debe detectar error cuando ERROR=S (147 ms) 

      × Debe concatenar DERROR1, DERROR2 y DERROR3 correctamente (132 ms) 

      × Debe manejar DERROR con campos vacíos (116 ms) 

    Validaciones adicionales de campos 

      √ Debe rechazar tipoCuentaDest de más de 4 caracteres (129 ms) 

      √ Debe rechazar tipoCuentaOrig de más de 4 caracteres (173 ms) 

      √ Debe rechazar numDocDest vacío (170 ms) 

      √ Debe rechazar numDocDest de más de 15 caracteres (133 ms) 

      √ Debe rechazar marcaTiempo en formato inválido (104 ms) 

      √ Debe aceptar ValorComision como opcional (156 ms) 

      √ Debe aceptar ValorIvaComision como opcional (103 ms) 

      √ Debe rechazar ValorComision negativa (137 ms) 

      √ Debe rechazar ValorIvaComision negativa (123 ms) 

      √ Debe rechazar tipo de cuenta no válido en destino (136 ms) 

      √ Debe rechazar tipo de cuenta no válido en origen (115 ms) 

    Transformaciones y formateo adicionales 

      × Debe transformar cuenta corta a 20 dígitos (250 ms) 

      × Debe mantener marcaTiempo con milisegundos existentes (172 ms) 

      × Debe enviar ValorComision por defecto como 0 (133 ms) 

      × Debe enviar ValorIvaComision por defecto como 0 (176 ms) 

      × Debe convertir todos los tipos de documento a NT correctamente (153 ms) 

      × No debe transformar tipos de documento diferentes a NIT (121 ms) 

    Campos opcionales adicionales 

      × Debe aceptar campo1 como objeto (133 ms) 

      √ Debe aceptar ipOriginador como opcional (134 ms) 

      √ Debe aceptar canalOriginador como opcional (230 ms) 

      √ Debe aceptar idTransaccionOriginal como opcional (218 ms) 

    Casos extremos y edge cases 

      √ Debe manejar monto de valor cero (137 ms) 

      × Debe manejar monto muy grande (129 ms) 

      × Debe manejar COERROR como string vacío (124 ms) 

    Mapeo de errores de Altair (BGE) 

      √ Debe mapear BGE0007 a código RTA 1422 (152 ms) 

      × Debe mapear BGE0038 a código RTA 2801 (cuenta no encontrada) (122 ms) 

      × Debe mapear BGE3244 a código RTA 2804 (cuenta bloqueada) (157 ms) 

      × Debe mapear BGE1320 a código RTA 2802 (cuenta inactiva) (152 ms) 

      × Debe mapear BGE5281 a código RTA 2809 (cuenta cancelada) (170 ms) 

      × Debe mapear BGE1191 a código RTA 1005 (datos incompletos) (129 ms) 

      × Debe retornar error genérico 1422 para código BGE no mapeado (124 ms) 

      × Debe priorizar errores[] sobre BGMN011.ERROR cuando ambos existen (119 ms) 

      √ Debe retornar error si monto no es un número (101 ms) 

      × Debe loggear error si falla logTransaction en error interno (94 ms) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Casos exitosos › Debe procesar una transacción OnUs exitosamente 

  

    expect(received).toHaveProperty(path) 

  

    Expected path: "respuesta" 

    Received path: [] 

  

    Received value: {"resultado": {"codigo": 1422, "descripcionError": "Error de validación. La información enviada en el payload contiene valores no válidos.", "error": true, "mensajeId": "3fa85f64-5717-4562-b3fc-2c963f66afa6"}} 

  

      145 | 

      146 |       const body = JSON.parse(response.body); 

    > 147 |       expect(body).toHaveProperty('respuesta'); 

          |                    ^ 

      148 |       expect(body).toHaveProperty('resultado'); 

      149 |       expect(body.resultado.error).toBe(false); 

      150 |       expect(body.resultado.codigo).toBe(0); 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:147:20) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Casos exitosos › Debe llamar al servicio con los datos correctos 

  

    expect(jest.fn()).toHaveBeenCalledTimes(expected) 

  

    Expected number of calls: 1 

    Received number of calls: 0 

  

      164 | 

      165 |       // Assert 

    > 166 |       expect(mockService.transaccionOnUs).toHaveBeenCalledTimes(1); 

          |                                           ^ 

      167 |       const callArgs = mockService.transaccionOnUs.mock.calls[0][0]; 

      168 | 

      169 |       expect(callArgs).toHaveProperty('cabecera'); 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:166:43) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Casos exitosos › Debe incluir idTransaccionSN en la respuesta 

  

    expect(received).toHaveProperty(path) 

  

    Matcher error: received value must not be null nor undefined 

  

    Received has value: undefined 

  

      187 |       // Assert 

      188 |       const body = JSON.parse(response.body); 

    > 189 |       expect(body.respuesta).toHaveProperty('idTransaccionSN'); 

          |                              ^ 

      190 |       expect(body.respuesta.idTransaccionSN).toBe('TX123456'); 

      191 |     }); 

      192 | 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:189:30) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Casos exitosos › Debe manejar campos opcionales nulos correctamente 

  

    expect(jest.fn()).toHaveBeenCalled() 

  

    Expected number of calls: >= 1 

    Received number of calls:    0 

  

      205 |       // Assert 

      206 |       expect(response.statusCode).toBe(200); 

    > 207 |       expect(mockService.transaccionOnUs).toHaveBeenCalled(); 

          |                                           ^ 

      208 |     }); 

      209 | 

      210 |     test('Debe transformar NIT a NT en tipoDocDest', async () => { 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:207:43) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Casos exitosos › Debe transformar NIT a NT en tipoDocDest 

  

    TypeError: Cannot read properties of undefined (reading '0') 

  

      221 | 

      222 |       // Assert 

    > 223 |       const callArgs = mockService.transaccionOnUs.mock.calls[0][0]; 

          |                                                                 ^ 

      224 |       expect(callArgs.data.tipoDocDest).toBe('NT'); 

      225 |     }); 

      226 | 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:223:65) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Casos exitosos › Debe transformar NIT a NT en tipoDocOrig 

  

    TypeError: Cannot read properties of undefined (reading '0') 

  

      238 | 

      239 |       // Assert 

    > 240 |       const callArgs = mockService.transaccionOnUs.mock.calls[0][0]; 

          |                                                                 ^ 

      241 |       expect(callArgs.data.tipoDocOrig).toBe('NT'); 

      242 |     }); 

      243 |   }); 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:240:65) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Manejo de errores del servicio › Debe manejar TransaccionOnUsError y mapear códigos de error 

  

    expect(received).toContain(expected) // indexOf 

  

    Expected value: 200 

    Received array: [400, 422, 500, 502, 503, 504] 

  

      403 |       // Assert 

      404 |       // Verifica que es un error del servicio 

    > 405 |       expect([400, 422, 500, 502, 503, 504]).toContain(response.statusCode); 

          |                                              ^ 

      406 |       const body = JSON.parse(response.body); 

      407 |       expect(body.resultado.error).toBe(true); 

      408 |       expect(body.resultado.codigo).toBeGreaterThan(0); 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:405:46) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Manejo de errores del servicio › Debe manejar errores inesperados con código 500 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 500 

    Received: 200 

  

      490 | 

      491 |       // Assert 

    > 492 |       expect(response.statusCode).toBe(500); 

          |                                   ^ 

      493 |       const body = JSON.parse(response.body); 

      494 |       expect(body.resultado.error).toBe(true); 

      495 |       expect(body.resultado.codigo).toBe(2001); 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:492:35) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Estructura de la respuesta › La respuesta exitosa debe tener la estructura correcta 

  

    expect(received).toHaveProperty(path) 

  

    Expected path: "respuesta" 

    Received path: [] 

  

    Received value: {"resultado": {"codigo": 1422, "descripcionError": "Error de validación. La información enviada en el payload contiene valores no válidos.", "error": true, "mensajeId": "3fa85f64-5717-4562-b3fc-2c963f66afa6"}} 

  

      659 | 

      660 |       // Verificar estructura de respuesta 

    > 661 |       expect(body).toHaveProperty('respuesta'); 

          |                    ^ 

      662 |       expect(body.respuesta).toHaveProperty('idTransaccionSN'); 

      663 |       expect(body.respuesta).toHaveProperty('idTransaccionCore'); 

      664 |       expect(body.respuesta).toHaveProperty('marcaTiempo'); 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:661:20) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Transformaciones de datos › Debe formatear numCuentaDest con padding de 20 dígitos 

  

    TypeError: Cannot read properties of undefined (reading '0') 

  

      721 | 

      722 |       // Assert 

    > 723 |       const callArgs = mockService.transaccionOnUs.mock.calls[0][0]; 

          |                                                                 ^ 

      724 |       expect(callArgs.data.numCuentaDest).toBe('00650100000987654321'); 

      725 |       expect(callArgs.data.numCuentaDest.length).toBe(20); 

      726 |     }); 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:723:65) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Transformaciones de datos › Debe formatear numCuentaOrig con padding de 20 dígitos 

  

    TypeError: Cannot read properties of undefined (reading '0') 

  

      738 | 

      739 |       // Assert 

    > 740 |       const callArgs = mockService.transaccionOnUs.mock.calls[0][0]; 

          |                                                                 ^ 

      741 |       expect(callArgs.data.numCuentaOrig).toBe('00650100000987654321'); 

      742 |       expect(callArgs.data.numCuentaOrig.length).toBe(20); 

      743 |     }); 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:740:65) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Transformaciones de datos › Debe mantener marcaTiempo cuando ya tiene milisegundos 

  

    TypeError: Cannot read properties of undefined (reading '0') 

  

      756 | 

      757 |       // Assert 

    > 758 |       const callArgs = mockService.transaccionOnUs.mock.calls[0][0]; 

          |                                                                 ^ 

      759 |       expect(callArgs.data.marcaTiempo).toBe('2025-02-10T14:30:00.000'); 

      760 |     }); 

      761 | 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:758:65) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Transformaciones de datos › Debe enviar monto como number 

  

    TypeError: Cannot read properties of undefined (reading '0') 

  

      772 | 

      773 |       // Assert 

    > 774 |       const callArgs = mockService.transaccionOnUs.mock.calls[0][0]; 

          |                                                                 ^ 

      775 |       expect(typeof callArgs.data.monto).toBe('string'); 

      776 |       expect(callArgs.data.monto).toBe((validPayload.monto).toFixed(2)); 

      777 |     }); 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:774:65) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Manejo de respuesta con error del core › Debe detectar error cuando ERROR=S 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 100 

    Received: 1422 

  

      813 |       const body = JSON.parse(response.body); 

      814 |       expect(body.resultado.error).toBe(true); 

    > 815 |       expect(body.resultado.codigo).toBe(100); 

          |                                     ^ 

      816 |       // Verificar que descripcionError contenga el texto correcto (con padding) 

      817 |       expect(body.resultado.descripcionError).toContain('Error en'); 

      818 |       expect(body.resultado.descripcionError).toContain('la transacción'); 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:815:37) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Manejo de respuesta con error del core › Debe concatenar DERROR1, DERROR2 y DERROR3 correctamente 

  

    expect(received).toContain(expected) // indexOf 

  

    Expected substring: "Parte 1" 

    Received string:    "Error de validación. La información enviada en el payload contiene valores no válidos." 

  

      850 |       // Con el nuevo mapeo, los campos se concatenan con posiciones fijas 

      851 |       // DERROR1 (100 chars) + DERROR2 (100 chars) + DERROR3 (55 chars) 

    > 852 |       expect(body.resultado.descripcionError).toContain('Parte 1'); 

          |                                               ^ 

      853 |       expect(body.resultado.descripcionError).toContain('Parte 2'); 

      854 |       expect(body.resultado.descripcionError).toContain('Parte 3'); 

      855 |     }); 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:852:47) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Manejo de respuesta con error del core › Debe manejar DERROR con campos vacíos 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: "Solo este mensaje" 

    Received: "Error de validación. La información enviada en el payload contiene valores no válidos." 

  

      883 |       // Assert 

      884 |       const body = JSON.parse(response.body); 

    > 885 |       expect(body.resultado.descripcionError).toBe('Solo este mensaje'); 

          |                                               ^ 

      886 |     }); 

      887 |   }); 

      888 | 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:885:47) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Transformaciones y formateo adicionales › Debe transformar cuenta corta a 20 dígitos 

  

    TypeError: Cannot read properties of undefined (reading '0') 

  

      1088 | 

      1089 |       // Assert 

    > 1090 |       const callArgs = mockService.transaccionOnUs.mock.calls[0][0]; 

           |                                                                 ^ 

      1091 |       expect(callArgs.data.numCuentaDest).toBe('00650100000000000123'); 

      1092 |       expect(callArgs.data.numCuentaDest.length).toBe(20); 

      1093 |     }); 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:1090:65) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Transformaciones y formateo adicionales › Debe mantener marcaTiempo con milisegundos existentes 

  

    TypeError: Cannot read properties of undefined (reading '0') 

  

      1106 | 

      1107 |       // Assert 

    > 1108 |       const callArgs = mockService.transaccionOnUs.mock.calls[0][0]; 

           |                                                                 ^ 

      1109 |       expect(callArgs.data.marcaTiempo).toBe('2025-02-10T14:30:00.000'); 

      1110 |     }); 

      1111 | 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:1108:65) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Transformaciones y formateo adicionales › Debe enviar ValorComision por defecto como 0 

  

    TypeError: Cannot read properties of undefined (reading '0') 

  

      1124 | 

      1125 |       // Assert 

    > 1126 |       const callArgs = mockService.transaccionOnUs.mock.calls[0][0]; 

           |                                                                 ^ 

      1127 |       expect(callArgs.data.ValorComision).toBe('0.00'); 

      1128 |     }); 

      1129 | 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:1126:65) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Transformaciones y formateo adicionales › Debe enviar ValorIvaComision por defecto como 0 

  

    TypeError: Cannot read properties of undefined (reading '0') 

  

      1142 | 

      1143 |       // Assert 

    > 1144 |       const callArgs = mockService.transaccionOnUs.mock.calls[0][0]; 

           |                                                                 ^ 

      1145 |       expect(callArgs.data.ValorIvaComision).toBe('0.00'); 

      1146 |     }); 

      1147 | 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:1144:65) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Transformaciones y formateo adicionales › Debe convertir todos los tipos de documento a NT correctamente 

  

    TypeError: Cannot read properties of undefined (reading '0') 

  

      1159 | 

      1160 |       // Assert 

    > 1161 |       const callArgs = mockService.transaccionOnUs.mock.calls[0][0]; 

           |                                                                 ^ 

      1162 |       expect(callArgs.data.tipoDocDest).toBe('NT'); 

      1163 |       expect(callArgs.data.tipoDocOrig).toBe('NT'); 

      1164 |     }); 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:1161:65) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Transformaciones y formateo adicionales › No debe transformar tipos de documento diferentes a NIT 

  

    TypeError: Cannot read properties of undefined (reading '0') 

  

      1177 | 

      1178 |       // Assert 

    > 1179 |       const callArgs = mockService.transaccionOnUs.mock.calls[0][0]; 

           |                                                                 ^ 

      1180 |       expect(callArgs.data.tipoDocDest).toBe('CC'); 

      1181 |       expect(callArgs.data.tipoDocOrig).toBe('CE'); 

      1182 |     }); 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:1179:65) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Campos opcionales adicionales › Debe aceptar campo1 como objeto 

  

    TypeError: Cannot read properties of undefined (reading '0') 

  

      1203 |       // Assert 

      1204 |       expect(response.statusCode).toBe(200); 

    > 1205 |       const callArgs = mockService.transaccionOnUs.mock.calls[0][0]; 

           |                                                                 ^ 

      1206 |       expect(callArgs.data.Campo1).toBe(JSON.stringify({ key: 'value' })); 

      1207 |     }); 

      1208 | 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:1205:65) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Casos extremos y edge cases › Debe manejar monto muy grande 

  

    TypeError: Cannot read properties of undefined (reading '0') 

  

      1298 |       // Assert 

      1299 |       expect(response.statusCode).toBe(200); 

    > 1300 |       const callArgs = mockService.transaccionOnUs.mock.calls[0][0]; 

           |                                                                 ^ 

      1301 |       expect(callArgs.data.monto).toBe((999999999).toFixed(2)); 

      1302 |     }); 

      1303 | 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:1300:65) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Casos extremos y edge cases › Debe manejar COERROR como string vacío 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 0 

    Received: 1422 

  

      1324 |       // Assert 

      1325 |       const body = JSON.parse(response.body); 

    > 1326 |       expect(body.resultado.codigo).toBe(0); 

           |                                     ^ 

      1327 |     }); 

      1328 |   }); 

      1329 | 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:1326:37) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Mapeo de errores de Altair (BGE) › Debe mapear BGE0038 a código RTA 2801 (cuenta no encontrada) 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 2801 

    Received: 1422 

  

      1425 |       const body = JSON.parse(response.body); 

      1426 |       expect(body.resultado.error).toBe(true); 

    > 1427 |       expect(body.resultado.codigo).toBe(2801); 

           |                                     ^ 

      1428 |       expect(body.resultado.descripcionError).toContain('cuenta destino no fue encontrada'); 

      1429 |     }); 

      1430 | 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:1427:37) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Mapeo de errores de Altair (BGE) › Debe mapear BGE3244 a código RTA 2804 (cuenta bloqueada) 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 2804 

    Received: 1422 

  

      1471 |       const body = JSON.parse(response.body); 

      1472 |       expect(body.resultado.error).toBe(true); 

    > 1473 |       expect(body.resultado.codigo).toBe(2804); 

           |                                     ^ 

      1474 |       expect(body.resultado.descripcionError).toContain('cuenta destino está bloqueada'); 

      1475 |     }); 

      1476 | 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:1473:37) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Mapeo de errores de Altair (BGE) › Debe mapear BGE1320 a código RTA 2802 (cuenta inactiva) 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 2802 

    Received: 1422 

  

      1517 |       const body = JSON.parse(response.body); 

      1518 |       expect(body.resultado.error).toBe(true); 

    > 1519 |       expect(body.resultado.codigo).toBe(2802); 

           |                                     ^ 

      1520 |       expect(body.resultado.descripcionError).toContain('cuenta destino está inactiva'); 

      1521 |     }); 

      1522 | 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:1519:37) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Mapeo de errores de Altair (BGE) › Debe mapear BGE5281 a código RTA 2809 (cuenta cancelada) 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 2809 

    Received: 1422 

  

      1563 |       const body = JSON.parse(response.body); 

      1564 |       expect(body.resultado.error).toBe(true); 

    > 1565 |       expect(body.resultado.codigo).toBe(2809); 

           |                                     ^ 

      1566 |       expect(body.resultado.descripcionError).toContain('Cuenta cancelada o saldada'); 

      1567 |     }); 

      1568 | 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:1565:37) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Mapeo de errores de Altair (BGE) › Debe mapear BGE1191 a código RTA 1005 (datos incompletos) 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 1005 

    Received: 1422 

  

      1609 |       const body = JSON.parse(response.body); 

      1610 |       expect(body.resultado.error).toBe(true); 

    > 1611 |       expect(body.resultado.codigo).toBe(1005); 

           |                                     ^ 

      1612 |       expect(body.resultado.descripcionError).toContain('cliente no tiene datos completos'); 

      1613 |     }); 

      1614 | 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:1611:37) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Mapeo de errores de Altair (BGE) › Debe retornar error genérico 1422 para código BGE no mapeado 

  

    expect(received).toContain(expected) // indexOf 

  

    Expected substring: "Error no mapeado" 

    Received string:    "Error de validación. La información enviada en el payload contiene valores no válidos." 

  

      1656 |       expect(body.resultado.error).toBe(true); 

      1657 |       expect(body.resultado.codigo).toBe(1422); 

    > 1658 |       expect(body.resultado.descripcionError).toContain('Error no mapeado'); 

           |                                               ^ 

      1659 |     }); 

      1660 | 

      1661 |     test('Debe priorizar errores[] sobre BGMN011.ERROR cuando ambos existen', async () => { 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:1658:47) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Mapeo de errores de Altair (BGE) › Debe priorizar errores[] sobre BGMN011.ERROR cuando ambos existen 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 2801 

    Received: 1422 

  

      1700 |       expect(response.statusCode).toBe(200); 

      1701 |       const body = JSON.parse(response.body); 

    > 1702 |       expect(body.resultado.codigo).toBe(2801); // BGE0038 mapeado 

           |                                     ^ 

      1703 |       expect(body.resultado.descripcionError).toContain('cuenta destino no fue encontrada'); 

      1704 |       expect(body.resultado.descripcionError).not.toContain('Este error no debe aparecer'); 

      1705 |     }); 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:1702:37) 

  

  ● TransaccionOnUs Router - POST /TransaccionOnUs › Mapeo de errores de Altair (BGE) › Debe loggear error si falla logTransaction en error interno 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 2001 

    Received: 1422 

  

      1734 |       const body = JSON.parse(response.body); 

      1735 |       expect(body.resultado.error).toBe(true); 

    > 1736 |       expect(body.resultado.codigo).toBe(2001); 

           |                                     ^ 

      1737 |     }); 

      1738 |   }); 

      1739 | }); 

  

      at Object.<anonymous> (test/unitary/routers/TransaccionOnUs.test.ts:1736:37) 

  

Test Suites: 1 failed, 1 total 

Tests:       33 failed, 37 passed, 70 total 

Snapshots:   0 total 

Time:        63.191 s, estimated 74 s 

Ran all test suites matching /test\\unitary\\routers\\TransaccionOnUs.test.ts/i. 

PS C:\Users\x830582\Downloads\Transaccional 200526\bnc-bsn0057-spitransaccionalautorizador> ^C 

PS C:\Users\x830582\Downloads\Transaccional 200526\bnc-bsn0057-spitransaccionalautorizador>  

 
