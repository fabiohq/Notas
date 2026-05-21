  

PS C:\Users\x830582\Downloads\Transaccional 200526\bnc-bsn0057-spitransaccionalautorizador> npm test -- test/unitary/routers/ConfirmarAbono.test.ts       

  

> microservice-template@1.0.0 test 

> cross-env NODE_ENV=test jest --detectOpenHandles --forceExit test/unitary/routers/ConfirmarAbono.test.ts 

  

{"level":30,"time":1779335503082,"pid":30080,"hostname":"IOCODC0X830582P","msg":"Ruta /confirmarabono registrada correctamente"} 

{"level":30,"time":1779335503217,"pid":30080,"hostname":"IOCODC0X830582P","reqId":"req-1","req":{"method":"POST","url":"/ConfirmarAbono","host":"localhost:80","remoteAddress":"127.0.0.1"},"msg":"incoming request"} 

{"level":30,"time":1779335503228,"pid":30080,"hostname":"IOCODC0X830582P","reqId":"req-1","mensajeId":"3fa85f64-5717-4562-b3fc-2c963f66afa6","body":{"monto":500000,"tipoDoc":"CC","numDoc":"123456789","tipoCuenta":"CAHO","numCuenta":"987654321","idTransaccionSN":"TX123456","identificadorSPBVI":"SiX","idTransaccionCore":"TX6345655","idTransaccionSPBVI":"TX6345657","EstadoTransaccion":"ACTC","marcaTiempo":"2025-02-10T14:30:01.000","IpOriginador":"192.10.1.24","CanalOriginador":"WEB","Campo1":"valor1","Campo2":"valor2"},"msg":"Procesando confirmación de abono"} 

{"level":50,"time":1779335503230,"pid":30080,"hostname":"IOCODC0X830582P","reqId":"req-1","servicio":"CONF-ABONO","mensajeId":"3fa85f64-5717-4562-b3fc-2c963f66afa6","errorType":"VALIDATION_ERROR","code":"VALIDATION_ERROR","statusCode":400} 

{"level":30,"time":1779335503280,"pid":30080,"hostname":"IOCODC0X830582P","reqId":"req-1","res":{"statusCode":400},"responseTime":56.98550000041723,"msg":"request completed"} 

FAIL  test/unitary/routers/ConfirmarAbono.test.ts (21.968 s) 

  ConfirmarAbono Router - POST /ConfirmarAbono 

    Registro del router 

      √ Debe registrar el router sin errores (791 ms) 

      √ Debe registrar la ruta POST /ConfirmarAbono (108 ms) 

    Casos exitosos 

      × Debe procesar una confirmación de abono exitosamente (227 ms) 

      × Debe llamar al servicio con los parámetros correctos (127 ms) 

      √ Debe incluir el mensajeId en la respuesta (121 ms) 

    Manejo de errores de Altair con código 200 

      × Debe devolver código 200 con error cuando es un error de Altair (107 ms) 

      × Debe incluir mensaje original de Altair en la descripción de error (111 ms) 

    Validación de schema 

      √ Debe rechazar payload sin campo requerido monto (95 ms) 

      √ Debe rechazar payload con tipoDoc inválido (94 ms) 

      √ Debe rechazar payload con tipoCuenta inválido (98 ms) 

      √ Debe rechazar payload con identificadorSPBVI inválido (110 ms) 

      × Debe aceptar campos opcionales como null (75 ms) 

    Manejo de errores del servicio 

      × Debe manejar error de negocio del servicio (103 ms) 

      × Debe manejar error interno inesperado (84 ms) 

      × Debe manejar error de timeout del core (86 ms) 

      × Debe manejar error de conexión al core (99 ms) 

    Logging 

      √ Debe incluir información de trazabilidad en los logs (554 ms) 

    Validación de IPv6 

      × Debe aceptar IpOriginador con formato IPv6 completo (137 ms) 

      × Debe aceptar IpOriginador con formato IPv6 comprimido (135 ms) 

      × Debe aceptar IpOriginador con formato IPv4 estándar (111 ms) 

    Cobertura adicional de branches 

      × Debe manejar respuesta sin campo data (125 ms) 

      × Debe manejar error de Altair con isAltairError (97 ms) 

      × Debe manejar error de Altair sin codigoAltair (85 ms) 

      × Debe manejar ConfirmarAbonoError específico (105 ms) 

      × Debe manejar error genérico (no ConfirmarAbonoError) (139 ms) 

      × Debe manejar error como string (152 ms) 

      × Debe manejar error null (142 ms) 

      × Debe incluir CampoLibreSalida cuando existe (201 ms) 

      × Debe usar string vacío para CampoLibreSalida cuando no existe (220 ms) 

      × Debe usar IpOriginador del body cuando está disponible (169 ms) 

      × Debe transformar campos correctamente (162 ms) 

      × Debe manejar campos opcionales Campo1-Campo5 con null (183 ms) 

      × Debe manejar CampoLibre0 con valor (160 ms) 

      × Debe manejar CampoLibre1 undefined con string vacío (166 ms) 

      × Debe manejar HubConcentrador undefined (262 ms) 

      × Debe manejar idTransaccionOriginal undefined (109 ms) 

      √ Debe concatenar DERROR1, DERROR2 y DERROR3 en descripción (99 ms) 

      × Debe manejar ConfirmarAbonoError con diferentes códigos de error (78 ms) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Casos exitosos › Debe procesar una confirmación de abono exitosamente 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 200 

    Received: 400 

  

      164 | 

      165 |       // Assert 

    > 166 |       expect(response.statusCode).toBe(200); 

          |                                   ^ 

      167 |       expect(response.json()).toMatchObject({ 

      168 |         respuesta: { 

      169 |           idTransaccionSN: 'TX123456', 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:166:35) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Casos exitosos › Debe llamar al servicio con los parámetros correctos 

  

    expect(jest.fn()).toHaveBeenCalledTimes(expected) 

  

    Expected number of calls: 1 

    Received number of calls: 0 

  

      191 | 

      192 |       // Assert 

    > 193 |       expect(mockConfirmarAbonoFn).toHaveBeenCalledTimes(1); 

          |                                    ^ 

      194 |       expect(mockConfirmarAbonoFn).toHaveBeenCalledWith( 

      195 |         expect.objectContaining({ 

      196 |           cabecera: expect.objectContaining({ 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:193:36) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Manejo de errores de Altair con código 200 › Debe devolver código 200 con error cuando es un error de Altair 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 500 

    Received: 400 

  

      247 | 

      248 |       // Assert 

    > 249 |       expect(response.statusCode).toBe(500); 

          |                                   ^ 

      250 |       expect(response.json()).toMatchObject({ 

      251 |         resultado: { 

      252 |           mensajeId: '3fa85f64-5717-4562-b3fc-2c963f66afa6', 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:249:35) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Manejo de errores de Altair con código 200 › Debe incluir mensaje original de Altair en la descripción de error 

  

    expect(received).toContain(expected) // indexOf 

  

    Expected substring: "Error interno al procesar la confirmación de abono" 

    Received string:    "Error en la validación de los datos enviados" 

  

      271 |       // Assert 

      272 |       const body = response.json(); 

    > 273 |       expect(body.resultado.descripcionError).toContain('Error interno al procesar la confirmación de abono'); 

          |                                               ^ 

      274 |     }); 

      275 |   }); 

      276 | 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:273:47) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Validación de schema › Debe aceptar campos opcionales como null 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 200 

    Received: 400 

  

      374 | 

      375 |       // Assert 

    > 376 |       expect(response.statusCode).toBe(200); 

          |                                   ^ 

      377 |     }); 

      378 |   }); 

      379 | 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:376:35) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Manejo de errores del servicio › Debe manejar error de negocio del servicio 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 401 

    Received: 400 

  

      401 | 

      402 |       // Assert 

    > 403 |       expect(response.statusCode).toBe(401); 

          |                                   ^ 

      404 |       expect(response.json()).toMatchObject({ 

      405 |         resultado: { 

      406 |           mensajeId: '3fa85f64-5717-4562-b3fc-2c963f66afa6', 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:403:35) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Manejo de errores del servicio › Debe manejar error interno inesperado 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 500 

    Received: 400 

  

      423 | 

      424 |       // Assert 

    > 425 |       expect(response.statusCode).toBe(500); 

          |                                   ^ 

      426 |       expect(response.json()).toMatchObject({ 

      427 |         resultado: { 

      428 |           mensajeId: '3fa85f64-5717-4562-b3fc-2c963f66afa6', 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:425:35) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Manejo de errores del servicio › Debe manejar error de timeout del core 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 504 

    Received: 400 

  

      451 | 

      452 |       // Assert 

    > 453 |       expect(response.statusCode).toBe(504); 

          |                                   ^ 

      454 |       expect(response.json()).toMatchObject({ 

      455 |         resultado: { 

      456 |           error: true, 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:453:35) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Manejo de errores del servicio › Debe manejar error de conexión al core 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 503 

    Received: 400 

  

      477 | 

      478 |       // Assert 

    > 479 |       expect(response.statusCode).toBe(503); 

          |                                   ^ 

      480 |       expect(response.json()).toMatchObject({ 

      481 |         resultado: { 

      482 |           error: true, 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:479:35) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Validación de IPv6 › Debe aceptar IpOriginador con formato IPv6 completo 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 200 

    Received: 400 

  

      535 | 

      536 |       // Assert 

    > 537 |       expect(response.statusCode).toBe(200); 

          |                                   ^ 

      538 |       expect(response.json().resultado.error).toBe(false); 

      539 |     }); 

      540 | 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:537:35) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Validación de IPv6 › Debe aceptar IpOriginador con formato IPv6 comprimido 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 200 

    Received: 400 

  

      552 | 

      553 |       // Assert 

    > 554 |       expect(response.statusCode).toBe(200); 

          |                                   ^ 

      555 |       expect(response.json().resultado.error).toBe(false); 

      556 |     }); 

      557 | 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:554:35) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Validación de IPv6 › Debe aceptar IpOriginador con formato IPv4 estándar 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 200 

    Received: 400 

  

      569 | 

      570 |       // Assert 

    > 571 |       expect(response.statusCode).toBe(200); 

          |                                   ^ 

      572 |       expect(response.json().resultado.error).toBe(false); 

      573 |     }); 

      574 |   }); 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:571:35) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Cobertura adicional de branches › Debe manejar respuesta sin campo data 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 500 

    Received: 400 

  

      601 | 

      602 |       // Assert 

    > 603 |       expect(response.statusCode).toBe(500); 

          |                                   ^ 

      604 |       expect(response.json().resultado.error).toBe(true); 

      605 |       expect(response.json().resultado.codigo).toBe(2001); 

      606 |     }); 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:603:35) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Cobertura adicional de branches › Debe manejar error de Altair con isAltairError 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 500 

    Received: 400 

  

      625 | 

      626 |       // Assert 

    > 627 |       expect(response.statusCode).toBe(500); 

          |                                   ^ 

      628 |       expect(response.json().resultado.error).toBe(true); 

      629 |       expect(response.json().resultado.codigo).toBe(2001); 

      630 |       expect(response.json().resultado.descripcionError).toContain('Error interno al procesar la confirmación de abono'); 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:627:35) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Cobertura adicional de branches › Debe manejar error de Altair sin codigoAltair 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 500 

    Received: 400 

  

      648 | 

      649 |       // Assert 

    > 650 |       expect(response.statusCode).toBe(500); 

          |                                   ^ 

      651 |       expect(response.json().resultado.error).toBe(true); 

      652 |       expect(response.json().resultado.codigo).toBe(2001); 

      653 |     }); 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:650:35) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Cobertura adicional de branches › Debe manejar ConfirmarAbonoError específico 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 504 

    Received: 400 

  

      670 | 

      671 |       // Assert 

    > 672 |       expect(response.statusCode).toBe(504); 

          |                                   ^ 

      673 |       expect(response.json().resultado.error).toBe(true); 

      674 |       expect(response.json().resultado.codigo).toBe(2004); 

      675 |     }); 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:672:35) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Cobertura adicional de branches › Debe manejar error genérico (no ConfirmarAbonoError) 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 500 

    Received: 400 

  

      687 | 

      688 |       // Assert 

    > 689 |       expect(response.statusCode).toBe(500); 

          |                                   ^ 

      690 |       expect(response.json().resultado.error).toBe(true); 

      691 |       expect(response.json().resultado.codigo).toBe(2001); 

      692 |       expect(response.json().resultado.descripcionError).toContain('Error interno'); 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:689:35) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Cobertura adicional de branches › Debe manejar error como string 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 500 

    Received: 400 

  

      705 | 

      706 |       // Assert 

    > 707 |       expect(response.statusCode).toBe(500); 

          |                                   ^ 

      708 |       expect(response.json().resultado.error).toBe(true); 

      709 |       expect(response.json().resultado.codigo).toBe(2001); 

      710 |     }); 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:707:35) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Cobertura adicional de branches › Debe manejar error null 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 500 

    Received: 400 

  

      722 | 

      723 |       // Assert 

    > 724 |       expect(response.statusCode).toBe(500); 

          |                                   ^ 

      725 |       expect(response.json().resultado.error).toBe(true); 

      726 |       expect(response.json().resultado.codigo).toBe(2001); 

      727 |     }); 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:724:35) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Cobertura adicional de branches › Debe incluir CampoLibreSalida cuando existe 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 200 

    Received: 400 

  

      748 | 

      749 |       // Assert 

    > 750 |       expect(response.statusCode).toBe(200); 

          |                                   ^ 

      751 |       expect(response.json().respuesta.CampoLibreSalida).toBe(undefined); 

      752 |     }); 

      753 | 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:750:35) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Cobertura adicional de branches › Debe usar string vacío para CampoLibreSalida cuando no existe 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 200 

    Received: 400 

  

      779 | 

      780 |       // Assert 

    > 781 |       expect(response.statusCode).toBe(200); 

          |                                   ^ 

      782 |       expect(response.json().respuesta.CampoLibreSalida).toBe(undefined); 

      783 |     }); 

      784 | 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:781:35) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Cobertura adicional de branches › Debe usar IpOriginador del body cuando está disponible 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 200 

    Received: 400 

  

      795 | 

      796 |       // Assert 

    > 797 |       expect(response.statusCode).toBe(200); 

          |                                   ^ 

      798 |       const callArgs = mockConfirmarAbonoFn.mock.calls[0][0]; 

      799 |       expect(callArgs.data.IpOriginador).toBe(validPayload.IpOriginador); 

      800 |     }); 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:797:35) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Cobertura adicional de branches › Debe transformar campos correctamente 

  

    TypeError: Cannot read properties of undefined (reading '0') 

  

      812 | 

      813 |       // Assert 

    > 814 |       const callArgs = mockConfirmarAbonoFn.mock.calls[0][0]; 

          |                                                          ^ 

      815 |       expect(callArgs.data.tipoDoc).toBe(validPayload.tipoDoc); 

      816 |       expect(callArgs.data.numDoc).toContain(validPayload.numDoc); 

      817 |       expect(callArgs.data.tipoCuenta).toBe(validPayload.tipoCuenta); 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:814:58) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Cobertura adicional de branches › Debe manejar campos opcionales Campo1-Campo5 con null 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 200 

    Received: 400 

  

      839 | 

      840 |       // Assert 

    > 841 |       expect(response.statusCode).toBe(200); 

          |                                   ^ 

      842 |       const callArgs = mockConfirmarAbonoFn.mock.calls[0][0]; 

      843 |       expect(callArgs.data.Campo1).toBe(''); 

      844 |       expect(callArgs.data.Campo2).toBe(''); 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:841:35) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Cobertura adicional de branches › Debe manejar CampoLibre0 con valor 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 200 

    Received: 400 

  

      861 | 

      862 |       // Assert 

    > 863 |       expect(response.statusCode).toBe(200); 

          |                                   ^ 

      864 |       const callArgs = mockConfirmarAbonoFn.mock.calls[0][0]; 

      865 |       expect(callArgs.data.CampoLibre0).toBe('dato_libre_0'); 

      866 |     }); 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:863:35) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Cobertura adicional de branches › Debe manejar CampoLibre1 undefined con string vacío 

  

    TypeError: Cannot read properties of undefined (reading '0') 

  

      880 | 

      881 |       // Assert 

    > 882 |       const callArgs = mockConfirmarAbonoFn.mock.calls[0][0]; 

          |                                                          ^ 

      883 |       expect(callArgs.data.CampoLibre1).toBe(''); 

      884 |     }); 

      885 | 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:882:58) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Cobertura adicional de branches › Debe manejar HubConcentrador undefined 

  

    TypeError: Cannot read properties of undefined (reading '0') 

  

      898 | 

      899 |       // Assert 

    > 900 |       const callArgs = mockConfirmarAbonoFn.mock.calls[0][0]; 

          |                                                          ^ 

      901 |       expect(callArgs.data.HubConcentrador).toBe(''); 

      902 |     }); 

      903 | 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:900:58) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Cobertura adicional de branches › Debe manejar idTransaccionOriginal undefined 

  

    TypeError: Cannot read properties of undefined (reading '0') 

  

      916 | 

      917 |       // Assert 

    > 918 |       const callArgs = mockConfirmarAbonoFn.mock.calls[0][0]; 

          |                                                          ^ 

      919 |       expect(callArgs.data.idTransaccionOriginal).toBe(''); 

      920 |     }); 

      921 | 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:918:58) 

  

  ● ConfirmarAbono Router - POST /ConfirmarAbono › Cobertura adicional de branches › Debe manejar ConfirmarAbonoError con diferentes códigos de error 

  

    expect(received).toBe(expected) // Object.is equality 

  

    Expected: 503 

    Received: 400 

  

      972 | 

      973 |         // Assert 

    > 974 |         expect(response.statusCode).toBe(errorCase.expectedStatus); 

          |                                     ^ 

      975 |         expect(response.json().resultado.error).toBe(true); 

      976 |         expect(response.json().resultado.codigo).toBe(errorCase.expectedCodigo); 

      977 |       } 

  

      at Object.<anonymous> (test/unitary/routers/ConfirmarAbono.test.ts:974:37) 

  

Test Suites: 1 failed, 1 total 

Tests:       29 failed, 9 passed, 38 total 

Snapshots:   0 total 

Time:        24.246 s 

Ran all test suites matching /test\\unitary\\routers\\ConfirmarAbono.test.ts/i. 

PS C:\Users\x830582\Downloads\Transaccional 200526\bnc-bsn0057-spitransaccionalautorizador>  
