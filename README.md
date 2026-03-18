
🔄 Flujo Técnico Actualizado
El microservicio Watchlist Screening recibe la solicitud de validación.
Se realiza el consumo de la API de OneFCC, enviando:
Tipo de documento
Número de documento
La API retorna información de coincidencias, incluyendo:
Coincidencias en listas restrictivas
Indicador de cliente PEP
Se implementa la siguiente lógica:
Si el cliente está en listas restrictivas → se mantiene en la respuesta (flujo normal de control)
Si el cliente es identificado como PEP:
Se excluye de la lista de coincidencias retornadas
Se evita que sea interpretado como restricción
Se permite continuar el flujo hacia el frontend
🧠 Lógica de Negocio Aplicada
La condición de PEP se considera un atributo informativo o de monitoreo, no restrictivo para este producto.
Se desacopla el concepto de:
Riesgo (PEP) vs
Restricción (listas sancionatorias)
🏗️ Impacto en el Microservicio
Se modifica la lógica de transformación de la respuesta del servicio de OneFCC.
Se introduce un filtro que:
Identifica registros marcados como PEP
Los excluye del payload de respuesta hacia el frontend
✅ Resultado Esperado
Los clientes PEP pueden:
Continuar el proceso de onboarding
Crear productos CDT sin bloqueo indebido
Se mantiene:
Cumplimiento normativo
Separación correcta de reglas de negocio
📝 Nota Técnica
Este ajuste no elimina la información de PEP del sistema, sino que:
Evita que afecte la decisión automática del flujo
Permite que otros procesos (ej. monitoreo o análisis de riesgo) puedan seguir utilizándola si es necesario