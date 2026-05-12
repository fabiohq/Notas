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
      componentName: msdtcnsntmngmnt
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

spring:
  application:
    name: bnc-bsn049-msdtcnsntmngmnt
  session:
    store-type: none
  cache:
    type: CAFFEINE #Activated cache caffeine by default (If you want to change the cache to JBoss DataGrid, check the documentacion in confluence)
    caffeine:
      spec: expireAfterWrite=10m #Specifies that each entry should be automatically removed from the cache once that duration has elapsed after the entry’s creation
  lifecycle.timeout-per-shutdown-phase: 2m
  config:
    import: classpath:messages.properties, classpath:regex.properties, classpath:movementconcept.properties

logging:
  level:
    com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt: INFO
    okhttp3: DEBUG
    okhttp3.OkHttpClient: DEBUG
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
          include: readinessState,ping,externalApis
        liveness:
          include: livenessState
observability:
  external-apis: 
    timeout-ms: 5000
    checks: 
      - name: backend-for-frontend 
        url: http://${bff-host}/actuator/health
        critical: false 
        accepted-statuses:
          - 200
          - 404
      - name: product-directory
        url: http://${product-directory-host}/actuator/health
        critical: false
        accepted-statuses:
          - 200
          - 404
      - name: term-deposit-parameters
        url: http://${term-deposit-parameters-host}/actuator/health
        critical: false
        accepted-statuses:
          - 200
          - 404
      - name: banks-and-branches
        url: http://${banks-host}/actuator/health
        critical: false
        accepted-statuses:
          - 200
          - 404

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
  appName: DATA_CONSENT_MANAGEMENT
  appVersion: api-services-v5
  commons:
    productCode: "04"
    subproductCode: "0250"
    currency: "COP"
    bankId: "0065"
    centerId: "0060"
  frequencies: "90,180,270,360,540"
  settlements: "BGMF,RETF,ITEA"
  condition-codes: TMVC
  sanba:
    mqRoute: "QCTFD"
    user: "@NETE781"
    channel: "60"


bff-host: "backend-for-frontend-sanba-gui.apps.ocp4-preprod.cosanpre.corp"
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
  BP49: SBCCG001ConsultaDetalladaMovimientos2652
  PEMFV: modificarMantencionPersonaNaturalInfAdicional

clients:
  product-directory:
    amount-range: "{product_id}/amount_range"
	
	
	
# App name for exceptions
exceptions.app.name=bnc-bsn049-msdtcnsntmngmnt

# Custom description and message for my_exception
my_exception.description=my description
my_exception.code=1001


ms_name= DATA_CONSENT_MANAGEMENT
ms_version= api-services-v5

errors.msName= DATA_CONSENT_MANAGEMENT
errors.msVersion= api-services-v5
errors.level= error
errors.functional = P-F
errors.technical = P-T

#Errores generales
errors.general.null= Cannot be null
errors.general.empty= Cannot be empty
errors.genetal.blank= Cannot be blank
errors.general.invalid_value = invalid value
errors.general.invalid_format= invalid format
errors.general.invalid_length= invalid length
errors.general.invalid_length_11= Invalid length (Max. 11)
errors.general.unhandled_exception= unhandled exception
errors.general.blank_data = Cannot be blank
errors.general.amount_over_limit = 'amount.amount' is over the range for the product
errors.general.amount_under_limit = 'amount.amount' is under the range for the product
errors.general.frequency_not_found = 'periodicity.frequency' not found
errors.general.periodtypecode_not_found = 'periodicity.periodTypeCode' not found
errors.general.settlementconditioncode_not_found = 'settlementConditionCode' not found
errors.general.productcode_not_found = 'product.productCode' not found
errors.general.subproductid_not_found = 'product.subproduct.subproductId' not found
errors.general.accountIdType_not_found = 'deposits.placement.destinationFunds.accountIdTytpeValidation' not found
errors.general.purposeCode_not_found = 'deposits.placement.purposeCode' not found
errors.general.bankId_not_found = 'deposit.placement.destinationFunds.bankcode' not found
errors.general.statuscode_null = 'statusInfo.statusCode' Cannot be null

#Errores especï¿½ficos
errors.termsdeposits.invalidCode = 'placement_status': Invalid Code


regex.error.code = P-F-9400

regex.type.only_numbers= ^[0-9]+$
regex.type.only_numbers.error = Invalid format (must be numbers)

regex.type.strict_length_20 = ^[0-9]{20,20}$
regex.type.strict_length_20_error = Invalid length (must be 20)

regex.type.strict_length_8 = ^[0-9]{8,8}$
regex.type.strict_length_8_error = Invalid length (must be 8)

regex.type.strict_length_11 = ^[0-9]{11,11}$
regex.type.strict_length_11_error = Invalid length (must be 11)

regex.type.strict_char_length_2 = ^[a-zA-Z]{2,2}$
regex.type.strict_char_length_2_error = Invalid length (must be 2)

regex.type.text_20_length = ^[\\p{L} ]{1,20}+$
regex.type.text_20_length_error = Invalid length (max 20)

regex.type.text_20_format = ^[\\p{L} ]+$
regex.type.text_20_format_error = Invalid format

regex.type.text_40_length = ^[\\p{L} ]{1,40}+$
regex.type.text_40_length_error = Invalid length (max 40)

regex.type.text_40_format = ^[\\p{L} ]+$
regex.type.text_40_format_error = Invalid format

regex.type.county_code_length = ^[\\p{L} ]{2,2}$
regex.type.county_code_length_error = Invalid Code (2 characters)

regex.type.county_code_format = ^[\\p{L} ]$
regex.type.county_code_format_error = Invalid format

regex.type.gender_code_format = ^[HM]{1,1}$
regex.type.gender_code_format_error = The genderCode can only contain letters (M or H)

regex.type.gender_code_length = ^[\\w\\W]{1,1}$
regex.type.gender_code_length_error = Size must be between 1 and 1

regex.type.address_length = ^[\\w\\W]{1,17}$
regex.type.address_length_error = Invalid length (max 17)

regex.type.address_format = [0-9a-zA-ZÃ¡Ã©Ã­Ã±Ã³ÃºÃ¼ÃÃÃÃÃÃÃ]+
regex.type.address_format_error = Invalid format

regex.type.email = ^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,6}$
regex.type.email_error = Invalid format

regex.type.phone_length = ^[0-9]{10,10}$
regex.type.phone_length_error = Invalid length (must be 10)

regex.type.phone_format = ^[0-9]+$
regex.type.phone_format_error = Invalid format (only numbers)

regex.type.international_code_length = ^[0-9]{2,3}$
regex.type.international_code_length_error = Invalid length (must be 3)

regex.type.international_code_format = ^[0-9]+$
regex.type.international_code_format_error = Invalid format (only numbers)

regex.type.birthday_date = ^[0-9]{4}-[0-9]{2}-[0-9]{2}$
regex.type.birthday_date_error = Invalid date format

regex.type.issue_date = ^[0-9]{4}-[0-9]{2}-[0-9]{2}$
regex.type.issue_date_error = Invalid date format

regex.type.amount_format = ^\\d+,\\d{2}$
regex.type.amount_format_error = Invalid Amount format (valid format is ####,##)

regex.type.product_code_length = ^[0-9]{2,2}$
regex.type.product_code_length_error = Invalid length (must be 2)

regex.type.product_code_format = ^[0-9]+$
regex.type.product_code_format_error = Invalid format (only numbers)

regex.type.subproduct_code_length = ^[0-9]{4,4}$
regex.type.subproduct_code_length_error = Invalid length (must be 4)

regex.type.subproduct_code_format = ^[0-9]+$
regex.type.subproduct_code_format_error = Invalid format (only numbers)

regex.type.frecuency_code_format = ^[0-9]+$
regex.type.frecuency_code_format_error = Frequency invalid format (only numbers)

regex.type.periodtype_code_length = ^[a-zA-Z]{1,1}$
regex.type.periodtype_code_length_error = Invalid length (must be 1)

regex.type.periodtype_code_format = ^[a-zA-Z]+$
regex.type.periodtype_code_format_error = invalid format (only letters)

regex.type.settlementcondition_code_length = ^[A-Z]{1,1}$
regex.type.settlementcondition_code_length_error = Invalid length (must be 1)

regex.type.settlementcondition_code_format = ^[a-zA-Z]+$
regex.type.settlementcondition_code_format_error = invalid format (only letters)

regex.type.placement_format = ^[0-9]{5}-[0-9]{5}$
regex.type.placement_format_error = Invalid format (00000-00000)

regex.type.bank_code_length = ^[0-9]{4,4}$
regex.type.bank_code_length_error = Invalid length (must be 4)

regex.type.bank_code_format = ^[0-9]+$
regex.type.bank_code_format_error = Invalid format (only numbers)

regex.type.center_code_length = ^[0-9]{4,4}$
regex.type.center_code_length_error = Invalid length (must be 4)

regex.type.center_code_format = ^[0-9]+$
regex.type.center_code_format_error = Invalid format (only numbers)

regex.type.accountIdType_code_length = ^[A-Z]{2,2}$
regex.type.accountIdType_code_length_error = Invalid length (must be 2)

regex.type.accountIdType_code_format = ^[A-Z]+$
regex.type.accountIdType_code_format_error = Invalid format (only letters)

regex.type.bankId_code_length = ^[0-9]{4,4}$
regex.type.bankId_code_length_error = Invalid length (must be 4)

regex.type.bankId_code_format = ^[0-9]+$
regex.type.bankId_code_format_error = Invalid format (only numbers)

regex.type.nationalIdentification_code_length = ^[0-9]{1,17}$
regex.type.nationalIdentification_code_length_error = Invalid length (max 17 characters)

regex.type.nationalIdentification_code_format = ^[0-9]+$
regex.type.nationalIdentification_code_format_error = Invalid format (only numbers)

regex.type.purposeCode_code_length = ^[A-Z0-9]{2}$
regex.type.purposeCode_code_length_error = Invalid length (must be 2)

regex.type.initialTotalInvested_amount_format = ^\\d+,\\d{2}$
regex.type.initialTotalInvested_amount_format_error = Invalid Amount format (valid format is ####,##)

regex.type.currency_code_format = ^[A-Z]+$
regex.type.currency_code_format_error = Invalid format (only letters)

regex.type.settlementConcept_code_length = ^[A-Z]{4,4}$
regex.type.settlementConcept_code_length_error = Invalid length (must be 4)

regex.type.settlementConcept_code_format = ^[A-Z]+$
regex.type.settlementConcept_code_format_error = invalid format (only letters)

regex.type.settlementConcept_typeCode_length = ^[A-Z]{1,1}$
regex.type.settlementConcept_typeCode_length_error = Invalid length (must be 4)

regex.type.settlementConcept_typeCode_format = ^[A-Z]+$
regex.type.settlementConcept_typeCode_format_error = invalid format (only letters)

regex.type.settlementConcept_rate_format = ^\\d+,\\d{4}$
regex.type.settlementConcept_rate_format_error = Invalid Amount format (valid format is ####,####)

regex.type.party_id_format = ^[0-9]+$
regex.type.party_id_format_error = Invalid format (only numbers)

regex.type.party_id_length = ^[0-9]{8}$
regex.type.party_id_length_error = Invalid length (must be 8)

regex.type.status_code_format = ^[SN]{1,1}$
regex.type.status_code_format_error = The statusCode can only contain letters (S or N)

regex.type.status_code_length = ^[a-zA-Z]{1}$
regex.type.status_code_length_error = Invalid length (must be 1)



<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<!-- Definition of the artifact -->
	<groupId>com.santander.bnc.bsn049</groupId>
	<artifactId>bnc-bsn049-msdtcnsntmngmnt</artifactId>
	<version>1.1.0</version>
	<packaging>jar</packaging>
	<!-- Information about the application -->
	<name>bnc-bsn049-msdtcnsntmngmnt</name>
	<description>MS Data Consent Management</description>
	<!-- Use starter parent -->
	<parent>
		<groupId>com.santander.darwin</groupId>
		<artifactId>darwin-spring-boot-starter-parent</artifactId>
		<version>6.1.0</version>
	</parent>

	<!-- Java compile version -->
	<properties>
		<java.version>17</java.version>
		<!-- JaCoCo -->
		<jacoco.version>0.8.12</jacoco.version>
		<!-- Archivo .exec -->
		<jacoco.dest.file>${project.build.directory}/jacoco/jacoco.exec</jacoco.dest.file>
		<!-- Carpeta reporte -->
		<jacoco.report.dir>${project.build.directory}/site/jacoco</jacoco.report.dir>
	</properties>
	<profiles>
		<profile>
			<id>env-jacoco</id>
			<activation>
				<property>
					<name>env.JACOCO_DEST_FILE</name>
				</property>
			</activation>
			<properties>
				<jacoco.dest.file>${env.JACOCO_DEST_FILE}</jacoco.dest.file>
			</properties>
		</profile>
		<profile>
			<id>env-jacoco-report</id>
			<activation>
				<property>
					<name>env.JACOCO_REPORT_DIR</name>
				</property>
			</activation>
			<properties>
				<jacoco.report.dir>${env.JACOCO_REPORT_DIR}</jacoco.report.dir>
			</properties>
		</profile>
	</profiles>


	<dependencies>
	    <!-- Spring Boot Actuator dependency -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>io.micrometer</groupId>
			<artifactId>micrometer-registry-prometheus</artifactId>
		</dependency>
		<!-- Servlet WebApp starter	-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<!-- OpenAPI dependency -->
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
		</dependency>
		<!-- Santander Darwin libraries -->
		<!-- Core dependency -->
		<dependency>
			<groupId>com.santander.darwin</groupId>
			<artifactId>darwin-spring-boot-starter-core</artifactId>
		</dependency>
		<!-- Logging dependency -->
		<dependency>
			<groupId>com.santander.darwin</groupId>
			<artifactId>darwin-spring-boot-starter-logging-basic</artifactId>
		</dependency>
		<!-- Cache dependency -->
		<dependency>
			<groupId>com.santander.darwin</groupId>
			<artifactId>darwin-spring-boot-starter-cache-caffeine</artifactId>
		</dependency>
		<!-- Authentication dependency -->
		<dependency>
			<groupId>com.santander.darwin</groupId>
			<artifactId>darwin-spring-boot-starter-authentication</artifactId>
		</dependency>
		<!-- Omnichannel dependency -->
		<dependency>
			<groupId>com.santander.darwin</groupId>
			<artifactId>darwin-spring-boot-starter-omnichannel</artifactId>
		</dependency>
		<!-- End Santander Darwin libraries -->
		<!--    Provided dependencies    -->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<scope>provided</scope>
		</dependency>
		<!-- Test Dependencies -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<!-- Junit 5 Dependencies -->
		<dependency>
			<groupId>org.junit.jupiter</groupId>
			<artifactId>junit-jupiter</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.junit.platform</groupId>
			<artifactId>junit-platform-launcher</artifactId>
			<scope>test</scope>
		</dependency>
		<!-- Enables any legacy JUnit 3 and JUnit 4 tests you may have. Not needed for JUnit 5 tests. -->
		<dependency>
			<groupId>org.junit.vintage</groupId>
			<artifactId>junit-vintage-engine</artifactId>
			<scope>test</scope>
		</dependency>
		<!-- Spring security dependency for testing -->
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-test</artifactId>
			<scope>test</scope>
		</dependency>

		<!-- implementation 'org.springframework.boot:spring-boot-starter-validation:3.2.2' -->
		<!-- Source: https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>

		<!-- runtimeOnly 'org.postgresql:postgresql' -->
		<!-- Source: https://mvnrepository.com/artifact/org.postgresql/postgresql 
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
		</dependency>
		-->
		<!-- implementation 'org.modelmapper:modelmapper:2.4.4' -->
		<!-- Source: https://mvnrepository.com/artifact/org.modelmapper/modelmapper -->
		<dependency>
			<groupId>org.modelmapper</groupId>
			<artifactId>modelmapper</artifactId>
			<version>3.2.6</version>
		</dependency>

		<!-- implementation 'com.squareup.retrofit2:retrofit:2.9.0' -->
		<!-- Source: https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit -->
		<dependency>
			<groupId>com.squareup.retrofit2</groupId>
			<artifactId>retrofit</artifactId>
			<version>3.0.0</version>
		</dependency>

		<!-- implementation 'com.squareup.retrofit2:converter-gson:2.9.0' -->
		<!-- Source: https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-gson 
		<dependency>
			<groupId>com.squareup.retrofit2</groupId>
			<artifactId>converter-gson</artifactId>
			<version>3.0.0</version>
		</dependency>
		-->
		<!-- implementation 'com.squareup.retrofit2:converter-jackson:2.9.0' -->
		<!-- Source: https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-jackson -->
		<dependency>
			<groupId>com.squareup.retrofit2</groupId>
			<artifactId>converter-jackson</artifactId>
			<version>3.0.0</version>
		</dependency>

		<!-- implementation 'com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2' -->
		<!-- Source: https://mvnrepository.com/artifact/com.squareup.okhttp3/logging-interceptor -->
		<dependency>
			<groupId>com.squareup.okhttp3</groupId>
			<artifactId>logging-interceptor</artifactId>
		</dependency>

		<!-- implementation 'org.apache.commons:commons-lang3:3.0'    -->
		<!-- Source: https://mvnrepository.com/artifact/org.apache.commons/commons-lang3 -->
		<dependency>
			<groupId>org.apache.commons</groupId>
			<artifactId>commons-lang3</artifactId>
		</dependency>

		<!-- implementation 'com.santander.integration:ig-cdt-common:1.0.0' -->
		<dependency>
			<groupId>com.santander.bnc.bsn049</groupId>
			<artifactId>bnc-bsn049-igcdtcommon</artifactId>
			<version>1.0.0-SNAPSHOT</version>
		</dependency>
		<!--
		<dependency>
			<groupId>com.santander.integration</groupId>
			<artifactId>ig-cdt-common</artifactId>
		</dependency>
		-->
		<!-- Source: https://mvnrepository.com/artifact/io.micrometer/micrometer-tracing-bridge-brave -->
		<dependency>
			<groupId>io.micrometer</groupId>
			<artifactId>micrometer-tracing-bridge-brave</artifactId>
			<version>1.6.4</version>
			<scope>compile</scope>
		</dependency>
		<!-- Source:
		https://mvnrepository.com/artifact/io.micrometer/micrometer-tracing -->
		<dependency>
			<groupId>io.micrometer</groupId>
			<artifactId>micrometer-tracing</artifactId>
			<version>1.6.4</version>
			<scope>compile</scope>
		</dependency>
	</dependencies>

	<build>
		<!-- Build plugins -->
		<plugins>
			<!-- Spring Boot Maven Plugin -->
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
			<!-- Maven Plugin for the encoding -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-resources-plugin</artifactId>
				<configuration>
					<encoding>${project.build.sourceEncoding}</encoding>
					<propertiesEncoding>ISO-8859-1</propertiesEncoding>
				</configuration>
			</plugin>
			<!-- ================================================= -->
			<!-- JACOCO -->
			<!-- ================================================= -->
			<plugin>
				<groupId>org.jacoco</groupId>
				<artifactId>jacoco-maven-plugin</artifactId>
				<version>${jacoco.version}</version>
				<executions>
					<!-- Activa cobertura al correr tests -->
					<execution>
						<id>prepare-agent</id>
						<goals>
							<goal>prepare-agent</goal>
						</goals>

						<configuration>
							<destFile>${jacoco.dest.file}</destFile>
							<append>true</append>
						</configuration>
					</execution>
					<!-- Genera reporte -->
					<execution>
						<id>report</id>
						<phase>verify</phase>
						<goals>
							<goal>report</goal>
						</goals>
						<configuration>
							<dataFile>${jacoco.dest.file}</dataFile>
							<outputDirectory>${jacoco.report.dir}</outputDirectory>
						</configuration>
					</execution>
				</executions>
			</plugin>
		</plugins>
		<!-- End build Plugins -->
		<resources>
			<resource>
				<directory>${project.basedir}/src/main/resources</directory>
				<filtering>true</filtering>
				<includes>
					<include>**/*.properties</include>
					<include>**/*.yml</include>
					<include>**/*.yaml</include>
					<include>**/banner.txt</include>
					<include>**/darwinchannels.json</include>
				</includes>
			</resource>
		</resources>
	</build>
</project>
