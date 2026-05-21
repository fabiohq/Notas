PS C:\Users\x830582\Downloads\Transaccional 200526\bnc-bsn0057-spitransaccionalautorizador> npm test -- test/unitary/routers/TransaccionOnUs.test.ts      

  

> microservice-template@1.0.0 test 

> cross-env NODE_ENV=test jest --detectOpenHandles --forceExit test/unitary/routers/TransaccionOnUs.test.ts 

  

  console.log 

    Database pool created successfully: default 

  

      at DatabaseErrorLogger.initialize (src/lib/shared/databaseErrorLogger.ts:58:15) 

  

FAIL  test/unitary/routers/TransaccionOnUs.test.ts (48.948 s) 

  TransaccionOnUs Router - POST /TransaccionOnUs 

    Registro del router 

      √ Debe registrar el router sin errores (1793 ms) 

      √ Debe registrar la ruta POST /TransaccionOnUs (302 ms) 

    Casos exitosos 

      √ Debe procesar una transacción OnUs exitosamente (1712 ms) 

      √ Debe llamar al servicio con los datos correctos (229 ms) 

      √ Debe incluir idTransaccionSN en la respuesta (257 ms) 

      √ Debe manejar campos opcionales nulos correctamente (285 ms) 

      √ Debe transformar NIT a NT en tipoDocDest (252 ms) 

      √ Debe transformar NIT a NT en tipoDocOrig (229 ms) 

    Validación de esquemas 

      √ Debe retornar 400 cuando falta el campo monto (353 ms) 

      √ Debe retornar 400 cuando el tipoDocDest es inválido (249 ms) 

      √ Debe retornar 400 cuando el tipoDocOrig es inválido (244 ms) 

      √ Debe retornar 400 cuando el monto es negativo (238 ms) 

      √ Debe retornar 400 cuando faltan múltiples campos requeridos (222 ms) 

      √ Debe retornar 400 cuando falta numDocDest (218 ms) 

      √ Debe retornar 400 cuando falta numCuentaDest (190 ms) 

    Manejo de errores del servicio 

      √ Debe manejar TransaccionOnUsError y mapear códigos de error (239 ms) 

      √ Debe mapear error CORE_CONNECTION_ERROR correctamente (220 ms) 

      √ Debe mapear error INVALID_PAYLOAD correctamente (249 ms) 

      √ Debe mapear error TRANSACCION_VALIDATION_ERROR correctamente (230 ms) 

      √ Debe manejar errores inesperados con código 500 (328 ms) 

      √ Debe incluir mensajeId en todos los errores del servicio (236 ms) 

    Trazabilidad y logging 

      √ Debe incluir mensajeId en todas las respuestas (235 ms) 

      √ Debe incluir mensajeId en respuestas de error de validación (231 ms) 

      √ Debe incluir mensajeId en respuestas de error del servicio (211 ms) 

    Integración con tipos y constantes 

      √ Debe aceptar todos los tipos de documento válidos (411 ms) 

      √ Debe aceptar todos los tipos de cuenta válidos para destino (363 ms) 

      √ Debe aceptar todos los tipos de cuenta válidos para origen (396 ms) 

    Estructura de la respuesta 

      √ La respuesta exitosa debe tener la estructura correcta (213 ms) 

      √ La respuesta de error debe tener la estructura correcta (195 ms) 

    Transformaciones de datos 

      √ Debe formatear numCuentaDest con padding de 20 dígitos (219 ms) 

      √ Debe formatear numCuentaOrig con padding de 20 dígitos (198 ms) 

      × Debe mantener marcaTiempo cuando ya tiene milisegundos (234 ms) 

      √ Debe enviar monto como number (206 ms) 

    Manejo de respuesta con error del core 

      √ Debe detectar error cuando ERROR=S (211 ms) 

      √ Debe concatenar DERROR1, DERROR2 y DERROR3 correctamente (195 ms) 

      √ Debe manejar DERROR con campos vacíos (330 ms) 

    Validaciones adicionales de campos 

      √ Debe rechazar tipoCuentaDest de más de 4 caracteres (204 ms) 

      √ Debe rechazar tipoCuentaOrig de más de 4 caracteres (205 ms) 

      √ Debe rechazar numDocDest vacío (255 ms) 

      √ Debe rechazar numDocDest de más de 15 caracteres (213 ms) 

      √ Debe rechazar marcaTiempo en formato inválido (230 ms) 

      √ Debe aceptar ValorComision como opcional (204 ms) 

      √ Debe aceptar ValorIvaComision como opcional (187 ms) 

      √ Debe rechazar ValorComision negativa (253 ms) 

      √ Debe rechazar ValorIvaComision negativa (208 ms) 

      √ Debe rechazar tipo de cuenta no válido en destino (199 ms) 

      √ Debe rechazar tipo de cuenta no válido en origen (202 ms) 

    Transformaciones y formateo adicionales 

      √ Debe transformar cuenta corta a 20 dígitos (173 ms) 

      × Debe mantener marcaTiempo con milisegundos existentes (188 ms) 

      √ Debe enviar ValorComision por defecto como 0 (187 ms) 

      √ Debe enviar ValorIvaComision por defecto como 0 (209 ms) 

      √ Debe convertir todos los tipos de documento a NT correctamente (234 ms) 

      √ No debe transformar tipos de documento diferentes a NIT (182 ms) 

    Campos opcionales adicionales 

      √ Debe aceptar campo1 como objeto (219 ms) 

      √ Debe aceptar ipOriginador como opcional (201 ms) 

      √ Debe aceptar canalOriginador como opcional (187 ms) 

      √ Debe aceptar idTransaccionOriginal como opcional (205 ms) 

    Casos extremos y edge cases 

      √ Debe manejar monto de valor cero (198 ms) 

      √ Debe manejar monto muy grande (197 ms) 

      √ Debe manejar COERROR como string vacío (216 ms) 

    Mapeo de errores de Altair (BGE) 

      √ Debe mapear BGE0007 a código RTA 1422 (197 ms) 

      √ Debe mapear BGE0038 a código RTA 2801 (cuenta no encontrada) (181 ms) 

      √ Debe mapear BGE3244 a código RTA 2804 (cuenta bloqueada) (211 ms) 

      √ Debe mapear BGE1320 a código RTA 2802 (cuenta inactiva) (174 ms) 

      √ Debe mapear BGE5281 a código RTA 2809 (cuenta cancelada) (384 ms) 

      √ Debe mapear BGE1191 a código RTA 1005 (datos incompletos) (214 ms) 

      √ Debe retornar error genérico 1422 para código BGE no mapeado (245 ms) 

      √ Debe priorizar errores[] sobre BGMN011.ERROR cuando ambos existen (165 ms) 

      √ Debe retornar error si monto no es un número (218 ms) 

      √ Debe loggear error si falla logTransaction en error interno (213 ms) 

  

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

  

Test Suites: 1 failed, 1 total 

Tests:       2 failed, 68 passed, 70 total 

Snapshots:   0 total 

Time:        53.096 s 

Ran all test suites matching /test\\unitary\\routers\\TransaccionOnUs.test.ts/i. 

PS C:\Users\x830582\Downloads\Transaccional 200526\bnc-bsn0057-spitransaccionalautorizador>  
