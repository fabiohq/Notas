 

   enum: TIPOS_DOCUMENTO_ARRAY, 

   description: 'Tipo de documento del destinatario. Valores válidos: CC, CE, NIT, TDI, PAS, PPT, PEP', 

   examples: ['CC'] 

 }), 

 numDocDest: Type.String({ description: 'Número de documento del destinatario (11 dígitos)', minLength: 4, maxLength: 11, pattern: '^[0-9]{4,11}$', examples: ['12345678901'] }),,  

 tipoCuentaDest: Type.String({,  

   enum: TIPOS_CUENTA_ARRAY,,  

   description: 'Tipo de cuenta del destinatario. Valores válidos: CAHO, CCTE, DBMO, DORD, DBMI', 

   minLength: 1, 

   maxLength: 4, 

   examples: ['CAHO'] 

 }),,  

 numCuentaDest: Type.String({ description: 'Número de cuenta del destinatario (mínimo 9 dígitos)', minLength: 9, maxLength: 12, pattern: '^[0-9]{9,12}$', examples: ['987654321'] }),,  

 monto: Type.Number({,  

   description: 'Monto de la transaccion. Numero con hasta 13 enteros y 2 decimales. Debe ser mayor que 0.', 

   minimum: 0.01, 

   maximum: 9999999999999.99, 

   examples: [1234567890123.45] 

 }), 

 valorComision: Type.Optional(Type.Number({ 

   description: 'Valor de la comision. Numero con hasta 13 enteros y 2 decimales.', 

   minimum: 0, 

   maximum: 9999999999999.99, 

   examples: [0],  

 })),,  

 valorIvaComision: Type.Optional(Type.Number({ 

   description: 'Valor del IVA aplicado a la comision. Numero con hasta 13 enteros y 2 decimales.', 

   minimum: 0, 

   maximum: 9999999999999.99, 

   examples: [0],  

 })),,  

 

 tipoDocOrig: Type.String({ 

   enum: TIPOS_DOCUMENTO_ARRAY, 

   description: 'Tipo de documento del originador. Valores válidos: CC, CE, NIT, TDI, PAS, PPT, PEP', 

   examples: ['CC'] 

 }), 

 numDocOrig: Type.String({ description: 'Número de documento del originador (11 dígitos)', minLength: 4, maxLength: 11, pattern: '^[0-9]{4,11}$', examples: ['12345678901'] }),,  

 tipoCuentaOrig: Type.String({,  

   enum: TIPOS_CUENTA_ARRAY,,  

   description: 'Tipo de cuenta del originador. Valores válidos: CAHO, CCTE, DBMO, DORD, DBMI', 

   minLength: 1, 

   maxLength: 4, 

   examples: ['CAHO'] 

 }),,  

 numCuentaOrig: Type.String({ description: 'Número de cuenta del originador (mínimo 9 dígitos)', minLength: 9, maxLength: 12, pattern: '^[0-9]{9,12}$', examples: ['987654321'] }),,  

 

 idTransaccionSN: Type.String({ description: 'Identificador único de la transacción en el sistema', minLength: 1, maxLength: 36, pattern: 'process.env.IDTRANSACCIONSN', examples: ['TX123456'] }),,  

 identificadorSistema: Type.String({ description: 'Código que identifica el sistema que realiza la transacción ONUS', minLength: 1, maxLength: 4, pattern: '^[A-Z0-9]{1,4}$', examples: ['NEU'] }),,  

 tipoTransaccion: Type.String({ description: 'Código o descripción que indique si es transferencia, pago, recaudo, solicitud, etc.', minLength: 1, maxLength: 20, pattern: '^[A-Z_]{1,20}$', examples: ['TRANSFER'] }), 

 

 marcaTiempo: Type.String({,  

   description: 'Fecha y hora del envío del consumo en formato ISO8601. Acepta con o sin Z final. Formatos: YYYY-MM-DDThh:mm:ss.sss o YYYY-MM-DDThh:mm:ss.sssZ', 

   pattern: 'process.env.MARCATIEMPO',,  

   minLength: 23,,  

   maxLength: 24, 

   examples: ['2025-02-10T14:30:00.000', '2025-02-10T14:30:00.000Z'] 

 }), 

 idTransaccionOriginal: Type.Optional(Type.Union([ 

   Type.String({ description: 'Identificador de la transacción original', maxLength: 36, examples: ['TRX123456'] }), 

   Type.Literal(''), 

   Type.Null() 

 ])), 

 

 ipOriginador: Type.Optional(Type.Union([,  

   Type.String({,  

     description: 'Dirección IP desde donde se realiza la transacción. Soporta IPv4 e IPv6. Ejemplo IPv6: 2001:0db8:85a3:0000:0000:8a2e:0370:7334', 

     minLength: 7, 

     maxLength: 50,,  

     pattern:'process.env.IPTRANSACCION',,  

   }),,  

   Type.Literal(''),,  

   Type.Null() 

 ])), 

 canalOriginador: Type.Optional(Type.Union([ 

   Type.String({ description: 'Nombre del canal por donde se está realizando la transacción', maxLength: 20, pattern: '^[A-Z]{1,20}$', examples: ['WEB'] }), 

   Type.Literal(''), 

   Type.Null() 

 ])), 

 

 campo1: Type.Optional(Type.Union([Type.String({ maxLength: 100 }), Type.Object({}, { additionalProperties: true }), Type.Null()])), 

 campo2: Type.Optional(Type.Union([Type.String({ maxLength: 100 }), Type.Object({}, { additionalProperties: true }), Type.Null()])), 

 campo3: Type.Optional(Type.Union([Type.String({ maxLength: 100 }), Type.Object({}, { additionalProperties: true }), Type.Null()])), 

 campo4: Type.Optional(Type.Union([Type.String({ maxLength: 100 }), Type.Object({}, { additionalProperties: true }), Type.Null()])), 

 campo5: Type.Optional(Type.Union([Type.String({ maxLength: 100 }), Type.Object({}, { additionalProperties: true }), Type.Null()])),,  

 CampoLibre0: Type.Optional(Type.String({ 

   description: 'Campo libre disponible 0 (opcional)', 

   maxLength: 100, 

   examples: ['', 'dato adicional 0'] 

 })), 

 CampoLibre1: Type.Optional(Type.String({ 

   description: 'Campo libre disponible 1 (opcional)', 

   maxLength: 100, 

   examples: ['', 'dato adicional 1'] 

 })) 

}, { additionalProperties: false });,  

 

/** 

* Schema for TransaccionOnUs success response 

*/ 

export const TransaccionOnUsResponseSchema = Type.Object({ 

 respuesta: Type.Optional(Type.Object({ 

   idTransaccionSN: Type.String({ description: 'Identificador único de la transacción en el sistema', maxLength: 36 }), 

   idTransaccionCore: Type.String({ description: 'Identificador de la transacción en el core', maxLength: 36 }), 

   marcaTiempo: Type.String({ format: 'date-time', description: 'Fecha y hora de la respuesta al consumo' }),,  

   CampoLibreSalida: Type.Optional(Type.String({ description: 'Campo libre de salida (opcional)', maxLength: 100, examples: ['', 'dato de salida'] })) 

 })),,  

 resultado: Type.Object({ 

   mensajeId: Type.String({ format: 'uuid', description: 'Identificador del mensaje enviado en la petición' }), 

   error: Type.Boolean({ description: 'Indicador de éxito o error en el proceso' }), 

   codigo: Type.Integer({ description: 'Código del resultado' }), 

   descripcionError: Type.String({ description: 'Descripción del error si existe', maxLength: 255 }) 

 }) 

}); 

 

export const ErrorResponseSchema = Type.Object({ 

 resultado: Type.Object({ 

   mensajeId: Type.String({ format: 'uuid' }), 

   error: Type.Boolean(), 

   codigo: Type.Integer(), 

   descripcionError: Type.String() 

 }) 

}); 

 
