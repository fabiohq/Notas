spring.profiles.active: local
---
darwin:
  region: boae
  suffix:
  app-name: bsn049
  logging:
    format: GLUONLOG
    gluon-log:
      company: bnc
      componentName: msiam
      componentId: CHANGEIT_CMPT_ID
      componentType: microservice
      appId: CHANGEIT_APP_ID
    entity: ESP
    paas-app-version: "6.1.0"
  core:
    exceptions:
      error-format: GLUON
  security:
    white-list:
      - /**
    connectors:
      pkm-connector:
        pkm-endpoint:
          - ${env.pkm-endpoint:localhost://}
    caffeine:
      # disable null values in cache for performance reasons
      allow-null-values: false

datasource.url: "jdbc:postgresql://localhost:5434/postgres?currentSchema=authentication"
datasource.driver-class-name: "org.postgresql.Driver"
datasource.username: "postgres"
datasource.password: "gady"

spring:
  application:
    name: bnc-bsn049-msiam
  session:
    store-type: none
  cache:
    type: CAFFEINE #Activated cache caffeine by default (If you want to change the cache to JBoss DataGrid, check the documentacion in confluence)
    caffeine:
      spec: expireAfterWrite=10m #Specifies that each entry should be automatically removed from the cache once that duration has elapsed after the entry’s creation
  lifecycle.timeout-per-shutdown-phase: 2m
  datasource:
    url: "${datasource.url}"
    username: "${datasource.username}"
    password: "${datasource.password}"
    driver-class-name: "${datasource.driver-class-name}"

logging:
  level:
    com.santander.bnc.bsn049.bncbsn049msiam: DEBUG
    okhttp3: INFO
    root: WARN
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level [%X{traceId:-}, %X{spanId:-}] [${spring.application.name:}] %logger{36} - %ms%n"
management:
  endpoints:
    web:
      exposure:
        include: health,info,prometheus
  endpoint:
    health:
      show-details: always
      probes:
        enabled: true
      group:
        readiness:
          include: readinessState,ping,db
        liveness:
          include: livenessState

springdoc:
  swagger-ui:
    disable-swagger-default-url: true
    path: /swagger-ui.html

server:
  max-http-request-header-size: 128KB
  forward-headers-strategy: framework
  shutdown: graceful
  address: 0.0.0.0
  port: 8083
camel:
  springboot:
    main-run-controller: true
auth:
  x-santander-client-id: 263ec146
  jwt:
    iss: CO_ODS
    public-key: MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvtR/wGjrtb5FZMt5rhMixKynE61Sz9curpgPIYUwk/js8hvc8UlIK4vUMEb0RusUIKrccy4k3seX1Da8RcXbUeEy1VAM2SS5bFCsB5FWoGQkPgomrRVLfNWwlIb9ekn1Gal7Y84NzoxW2uJ0849phJlI8fa1snPHL396ZnwqEDEryFmbJZbdNc4zIarEc2hOYM/GTWc9RP5h2BLEU6nUD5TU94PM5AY+18WoVUOPQZ4wdRdST1D7Fq/8+BYMlPuwZMHZO2N8zhkIJm+744jGBQ8yeHubHO8E+wtlu4fqmQZNA1WissqRIMRnmS7bjh8hgn006omWrVWVAthXTT73iQIDAQAB
    exp-claim-name: exp
