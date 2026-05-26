Sí, con el log completo queda claro:
Error raíz:
BadRequestException: Document download whitelist is not configured
Está envuelto dentro de:
DocumentHandlingFailure
Qué significa:
El endpoint uploadDocument intenta subir un documento usando una URL, pero antes valida si el host de esa URL está permitido. Esa validación falla porque la whitelist de descarga de documentos no está configurada.
La clase clave es:
DocumentUriValidator.validateHostWhitelist
Causa más probable:
En el ambiente stg, falta una property/env/config tipo whitelist para URLs permitidas del document manager.
Flujo donde falla:
UploadDocumentControllerImpl.uploadDocument
→ OkMbDocumentService.uploadV2
→ DocumentUploaderClient.uploadDocument
→ DocumentUploaderClient.sendDocument
→ DocumentUriValidator.validateHostWhitelist
→ falla por whitelist no configurada.
Revisa la configuración de mb-api-doc-manager-co:5.0.4, especialmente propiedades relacionadas con:
document download whitelist
download whitelist
allowed hosts
document uri whitelist
host whitelist
