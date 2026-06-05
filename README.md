logging.level.com.zaxxer.hikari=DEBUG
spring.datasource.hikari.leak-detection-threshold=30000
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=2


SELECT username, machine, program, module, status, COUNT(*) total
FROM v$session
WHERE username = 'USUARIO_APP'
GROUP BY username, machine, program, module, status
ORDER BY total DESC;

para ver sesiones activas reales:
SELECT sid, serial#, username, machine, program, module,
       status, event, wait_class, seconds_in_wait, sql_id
FROM v$session
WHERE username = 'USUARIO_APP'
ORDER BY seconds_in_wait DESC;
