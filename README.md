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
      componentName: mswatchliscreen
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
          - ${pkm-endpoint}
    caffeine:
      # disable null values in cache for performance reasons
      allow-null-values: false

spring:
  application:
    name: bnc-bsn049-mswatchliscreen
  session:
    store-type: none
  cache:
    type: CAFFEINE #Activated cache caffeine by default (If you want to change the cache to JBoss DataGrid, check the documentacion in confluence)
    caffeine:
      spec: expireAfterWrite=10m #Specifies that each entry should be automatically removed from the cache once that duration has elapsed after the entry’s creation
  lifecycle.timeout-per-shutdown-phase: 2m
  datasource:
    #url: ${spring.datasource.url}
    #username: ${spring.datasource.username}
    #password: ${spring.datasource.password}
    driver-class-name: oracle.jdbc.driver.OracleDriver
    # Configuration of connection pool. Please configure it according to the needs of the microservice.
    hikari:
      minimum-idle: 1
      maximum-pool-size: 2
      idle-timeout: 36000
      max-lifetime: 1800000
      connection-timeout: 20000
      leak-detection-threshold: 3000
  jpa:
    hibernate:
      ddl-auto: ${env.database.ddl-auto}
  config:
    import: message.properties, regex.properties
logging:
  level:
    com.santander.bnc.bsn049.bncbsn049mswatchliscreen: INFO
    root: WARN
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level [%X{traceId:-},%X{spanId:-}] [${spring.application.name:}] %logger{36} - %ms%n"
management:
  endpoint.health:
    show-details: ALWAYS

health:
  config:
    enabled: false

springdoc:
  swagger-ui:
    disable-swagger-default-url: true
    path: /swagger-ui.html

server:
  max-http-request-header-size: 128KB
  forward-headers-strategy: framework
  shutdown: graceful

EXEMPT_RISK_SOURCES: "9"

params:
  appName: WATCHLIST_SCREENING
  appVersion: api-service-v1
  vigiaService:
    url: "http://180.26.147.60:8070/WebServices/SOAP_VerificaTerceroV3.asmx"
    urn: "http://tempuri.org/F_VERIFICATERCEROV3"
    peOrigen: "TEST"
    pePorcentaje: "100"
    pePorcentajeCE: "100"
    pePorcentajeCC: "85"


# Servicio de Motor consulta datos basicos
engine:
  service-name: ${engine.service-name}
  protocol: ${kyc.vigia.protocol}
  host: ${engine.host}
  context: ${engine.context}
  trust-store: 
  trust-store-property: 
  mqRoute: ${engine.mqRoute}

#OneFcc
urlOneFcc: ${onefcc.url}
userOneFcc: ${onefcc.user}     
passOneFcc: ${onefcc.pass}        


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
      componentName: mscontracts
      componentId: CHANGEIT_CMPT_ID
      componentType: microservice
      appId: CHANGEIT_APP_ID
    entity: ESP
    paas-app-version: "6.1.0"
  core:
    exceptions:
      error-format: GLUON
  security:
    connectors:
      pkm-connector:
        pkm-endpoint:
          - ${PKM_ENDPOINT:localhost://}
    caffeine:
      # disable null values in cache for performance reasons
      allow-null-values: false
spring:
  application:
    name: bnc-bsn049-mscontracts
  session:
    store-type: none
  cache:
    type: CAFFEINE #Activated cache caffeine by default (If you want to change the cache to JBoss DataGrid, check the documentacion in confluence)
    caffeine:
      spec: expireAfterWrite=10m #Specifies that each entry should be automatically removed from the cache once that duration has elapsed after the entry’s creation
  lifecycle.timeout-per-shutdown-phase: 2m
  config:
    import: classpath:messages.properties, classpath:regex.properties

logging:
  level:
    com.santander.bnc.bsn049.bncbsn049mscontracts: INFO
    root: WARN
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level [%X{traceId:-}, %X{spanId:-}] [${spring.application.name:}] %logger{36} - %ms%n"

management:
  endpoint.health:
    show-details: ALWAYS

health:
  config:
    enabled: false

springdoc:
  swagger-ui:
    disable-swagger-default-url: true
    path: /swagger-ui.html

server:
  max-http-request-header-size: 128KB
  forward-headers-strategy: framework
  shutdown: graceful
  port: 8083

params:
  appName: CONTRACTS
  appVersion: api-services-v3
  commons:
    productCode: "04"
    subproductCode: "0250"
    currency: "COP"
    bankId: "0065"
    centerId: "0060"
  pepf:
    user: "@NETE781"
    numper: "03018043"
  sanba:
    mqRoute: "QCTFD"
    user: "@NETE781"
    channel: "60"

  frequencies: "90,180,270,360,540"
  settlements: "BGMF,RETF,ITEA"
  condition-codes: TMVC

bff-host: "backend-for-frontend-security-sanba-gui.apps.ocp4-preprod.cosanpre.corp"
bff-port: 443
bff-https: true

product-directory-host: "ms-productdirectory-cdt-core-dev.apps.ocp4-preprod.cosanpre.corp"
product-directory-port: 443
product-directory-https: true

term-deposit-parameters-host: "ms-termdepositparameters-cdt-core-dev.apps.ocp4-preprod.cosanpre.corp"
term-deposit-parameters-port: 443
term-deposit-parameters-https: true

banks-host: "ms-banksandbranches-cdt-core-dev.apps.ocp4-preprod.cosanpre.corp"
banks-port: 443
banks-https: true

integration:
  catalogue:
    - integrationType: sanba
      host: ${bff-host}
      port: ${bff-port}
      https: ${bff-https}
      endpoint: "/service-engine/procesar/"
      timeOutConn: 1000
      timeOutRead: 1000
    - integrationType: product-directory
      host: ${product-directory-host}
      port: ${product-directory-port}
      https: ${product-directory-https}
      endpoint: "/v3/products/"      
      timeOutConn: 1000
      timeOutRead: 1000
    - integrationType: term-deposit-parameters
      host: ${term-deposit-parameters-host}
      port: ${term-deposit-parameters-port}
      https: ${term-deposit-parameters-https}
      endpoint: "/v1/term_deposit_parameters/"
      timeOutConn: 1000
      timeOutRead: 1000
    - integrationType: banks
      host: ${banks-host}
      port: ${banks-port}
      https: ${banks-https}
      endpoint: "/v2/banks/"
      timeOutConn: 1000
      timeOutRead: 1000


service-route-trx:
  BP17: simulacionCDTBP17S171
  PEPF: modificarMantencionPersonaNaturalInfAdicional
  BP01: altaCuentaPlazoODS
  BP02: altaIpfOdsCtaExterna
  BP31: SBCDTTI01-ConsultaCDTDATTitular2654
  BP13: consultaDatosIPF
  BP49: SBCCG001ConsultaDetalladaMovimientos2652
  BP21: modificacionDatosIPF
