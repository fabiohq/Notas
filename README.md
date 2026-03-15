completo)
Justificación de Falso Positivo
El valor utilizado en el encabezado HTTP Authorization no proviene de entrada directa del usuario.
El token JWT es obtenido desde un endpoint de autenticación de backend confiable (/login) mediante credenciales de servicio en el servidor. Antes de utilizarse en el encabezado HTTP, el token pasa por un proceso de validación estricta en el servidor que incluye:
Validación de valores nulos
Restricción de longitud máxima (MAX_LEN)
Validación mediante expresión regular de tipo whitelist que asegura el formato JWT (^[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+$)
Esta validación únicamente permite caracteres compatibles con JWT y evita explícitamente caracteres de control como retorno de carro (CR), salto de línea (LF), espacios u otros caracteres que pudieran permitir ataques de HTTP Header Injection o HTTP Response Splitting.
Posteriormente el token validado es aplicado utilizando el método seguro de Spring HttpHeaders.setBearerAuth(), el cual establece correctamente el encabezado Authorization.
Debido a estas validaciones y controles implementados, el riesgo de Header Manipulation queda mitigado, por lo que este hallazgo se considera un Falso Positivo.