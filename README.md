package com.santander.bnc.bsn049.bncbsn049savekycservice.domain.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.bean.DesiredException;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.bean.Questions;

@Repository
public class QuestionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(QuestionRepository.class);

    /**
     * Obtiene los registros de informaci\u00F3n asociados a los diferentes
     * t\u00E9rminos o nomenclaturas de las direcciones.
     * 
     * @return Listado de t\u00E9rminos o nomenclaturas de las direcdiones.
     * @see AddressTermsBean
     */
    public Questions getQuestion(String id) {
        Questions questionBeanList = null;

        String sql = "SELECT * FROM CDTKYC.QUESTION WHERE KEY = ?";

        try {
            questionBeanList = jdbcTemplate.queryForObject(sql, new QuestionRowMapper(), id);
        } catch (DataAccessException e) {
            logger.error("Data was not recorded. Error executing query", e);
            return questionBeanList;
        } catch (Exception e) {
            logger.error("Data was not recorded.", e);
            return questionBeanList;
        }
        return questionBeanList;

    }

    public static class QuestionRowMapper implements RowMapper<Questions> {

        public Questions mapRow(ResultSet resultSet, int row) throws SQLException {
            Questions questionBean = new Questions();
            questionBean.setQuestionId(resultSet.getString("key"));
            questionBean.setDescription(resultSet.getString("name"));
            questionBean.setVigia(resultSet.getString("vigia"));

            return questionBean;
        }
    }

    public String getCIUU(String name) throws DesiredException {
        String questionBeanList = null;

        String sql = "SELECT RISK FROM CDTKYC.ANSWER WHERE KEY = ?";

        try {
            questionBeanList = jdbcTemplate.queryForObject(sql, new AnswerRowMapper(), name);
        } catch (DataAccessException e) {
            logger.error("Error table RISK. Error executing query", e);
            throw new DesiredException("questionId: 2d747251-8d68-4a59-82bd-7838ec1485b3 - not valid", 404, e);
        } catch (Exception e) {
            logger.error("Error table RISK.", e);
            throw new DesiredException("questionId: 2d747251-8d68-4a59-82bd-7838ec1485b3 - not valid", 404, e);
        }
        return questionBeanList;

    }

    public static class AnswerRowMapper implements RowMapper<String> {

        public String mapRow(ResultSet resultSet, int row) throws SQLException {
            String risk = resultSet.getString("risk");

            return risk;
        }
    }

    public String getAnswerCode(String key) throws DesiredException {
        String questionBeanList = null;

        String sql = "SELECT NAME FROM CDTKYC.ANSWER WHERE KEY = ?";


        try {
            questionBeanList = jdbcTemplate.queryForObject(sql, new AnswerCodeRowMapper(), key);
        } catch (DataAccessException e) {
           logger.error("Error getting answer code Error executing query", e);
            throw new DesiredException("questionId: " + key, 404, e);
        } catch (Exception e) {
            logger.error("Error getting answer code.", e);
            throw new DesiredException("questionId: " + key, 404, e);
        }
        return questionBeanList;

    }

    public static class AnswerCodeRowMapper implements RowMapper<String> {

        public String mapRow(ResultSet resultSet, int row) throws SQLException {
            String name = resultSet.getString("name");

            return name;
        }
    }

    public void addForm(String key, String document, String documentType, String dateStart, String dateEnd,
            String request, String response, String penumpe, boolean facta, boolean PEP, boolean CRS, String activity,
            String profession, String cIIU, String incomes, String expenses, String passives, String assets,
            String patrimony, boolean tinRequiredEU, String tinEU, String codeCRS, boolean tinRequiredCRS,
            String tinCRS) throws DesiredException {

        try {
            Date date_Start = new SimpleDateFormat("yyyy-MM-dd").parse(dateStart);
            dateStart = new SimpleDateFormat("dd/MM/yyyy").format(date_Start);
            logger.info("date_Start: " + date_Start + "dateStart: " + dateStart);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            logger.error("could not be posible parse dates '{}':'{}' .",
                    dateStart, e);
        }

        try {
            Date date_End = new SimpleDateFormat("yyyy-mm-dd").parse(dateEnd);
            dateEnd = new SimpleDateFormat("dd/mm/yyyy").format(date_End);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            logger.error("could not be posible parse dates '{}':'{}' .",
                    dateEnd, e);
        }

        String sql = "INSERT INTO CDTKYC.KYC_FORM (KEY, DOCUMENT,DOCUMENT_TYPE,START_DATE,END_DATE,REQUEST,RESPONSE,PENUMPE,FATCA,PEP,CRS,ACTIVITY,"
                + "PROFESSION,CIIU,INCOMES,EXPENSES,PASSIVES,ASSETS,PATRIMONY,TIN_EU,NUMBER_TIN_EU,CODE_CRF,TIN_CRF,NUMBER_TIN_CRF) "
                + "VALUES(?, ?, ?, to_date(?,'dd/mm/yyyy'), to_date(?,'dd/mm/yyyy'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            jdbcTemplate.update(sql, key, document, documentType, dateStart, dateEnd, request, response, penumpe, facta,
                    PEP, CRS, activity, profession, cIIU, incomes,
                    expenses, passives, assets, patrimony, tinRequiredEU, tinEU, codeCRS, tinRequiredCRS, tinCRS);
            logger.debug(
                    "The registration KYC form '{}' was successful.",
                    key);
        } catch (DuplicateKeyException e) {
            logger.error(" KYC form could duplicate registered in database with the identifier '{}': '{}'.",
                    key, e.getMessage(), e);
            throw new DesiredException("'knowYourCustomerQuestionnaires.questionnaireId': duplicado", 400, e);
        } catch (Exception exception) {
            logger.error(" KYC form could not be registered in database with the identifier '{}': '{}' .",
                    key, exception.getMessage(), exception);
            throw new DesiredException(
                    "'knowYourCustomerQuestionnaires.questionnaireId': could not be registered in database with the identifier  "
                            + key,
                    400, exception);
        }
    }
}



package com.santander.bnc.bsn049.bncbsn049savekycservice.domain.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.bean.DesiredException;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.bean.FormKYCBean;

@Repository
@RequestMapping("/v2/know_your_customer")
public class FormKYCRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(FormKYCRepository.class);

    /**
     * Obtiene los registros de informaci\u00F3n asociados a los diferentes
     * t\u00E9rminos o nomenclaturas de las direcciones.
     * 
     * @return Listado de t\u00E9rminos o nomenclaturas de las direcdiones.
     * @throws DesiredException
     * @see AddressTermsBean
     */
    public FormKYCBean getFormPenumpe(String key) throws DesiredException {
        FormKYCBean questionBeanList = null;

        String sql = "SELECT * FROM \r\n"
                + "(SELECT * FROM CDTKYC.KYC_FORM WHERE PENUMPE = ? ORDER BY TO_DATE( START_DATE, 'dd/mm/yyyy') DESC)\r\n"
                + " WHERE ROWNUM = 1";

        try {
            questionBeanList = jdbcTemplate.queryForObject(sql, new AnswerRowMapper(), key);

        } catch (BadSqlGrammarException e) {
            logger.error("KYC_FORM data was not found.", e);
            throw new DesiredException("'party_id': not found", 404, e);
        } catch (Exception e) {
            logger.error("KYC_FORM data was not found.", e);
            throw new DesiredException("KYC_FORM data not found", 404, e);
        }
        return questionBeanList;

    }

    public FormKYCBean getFormKYC(String key) throws DesiredException {
        FormKYCBean questionBeanList = null;

        String sql = "SELECT * FROM CDTKYC.KYC_FORM WHERE KEY = ?";

        try {
            questionBeanList = jdbcTemplate.queryForObject(sql, new AnswerRowMapper(), key);

        } catch (BadSqlGrammarException e) {
            logger.error("KYC_FORM data was not found.", e);
            throw new DesiredException("'kyc_resolution_id': not found", 404, e);
        } catch (Exception e) {
            logger.error("KYC_FORM data was not found.", e);
            throw new DesiredException("KYC_FORM data not found", 404,e);
        }
        return questionBeanList;

    }

    public static class AnswerRowMapper implements RowMapper<FormKYCBean> {

        public FormKYCBean mapRow(ResultSet resultSet, int row) throws SQLException {
            FormKYCBean formKYCBean = new FormKYCBean();
            formKYCBean.setRequest(resultSet.getString("request"));
            formKYCBean.setResponse(resultSet.getString("response"));
            formKYCBean.setKey(resultSet.getString("key"));

            return formKYCBean;
        }
    }

}

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<!-- Definition of the artifact -->
	<groupId>com.santander.bnc.bsn049</groupId>
	<artifactId>bnc-bsn049-savekycservice</artifactId>
	<version>1.2.3</version>
	<packaging>jar</packaging>
	<!-- Information about the application -->
	<name>bnc-bsn049-savekycservice</name>
	<description>save-kyc-service</description>
	<!-- Use starter parent -->
	<parent>
		<groupId>com.santander.darwin</groupId>
		<artifactId>darwin-spring-boot-starter-parent</artifactId>
		<version>6.3.5</version>
	</parent>

	<!-- Java compile version -->
	<properties>
		<java.version>17</java.version>
	</properties>


	<dependencies>
		<!-- Spring Boot Actuator dependency -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
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
			<artifactId>darwin-spring-boot-starter-logging-kafka</artifactId>
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
		<!-- Webservice dependency -->
		<dependency>
			<groupId>com.santander.darwin</groupId>
			<artifactId>darwin-spring-boot-starter-webservice</artifactId>
		</dependency>
		<!-- End Santander Darwin libraries -->
		<!-- JPA dependencies -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<!-- Oracle dependencies -->
		<dependency>
			<groupId>com.oracle.database.jdbc</groupId>
			<artifactId>ojdbc11</artifactId>
		</dependency>
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
		<!-- Enables any legacy JUnit 3 and JUnit 4 tests you may have. Not needed for JUnit 5
		tests. -->
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
		<!-- H2 dependencies -->
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>test</scope>
		</dependency>
		<!-- Manejo de conexiones JDBC para spring boot -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jdbc</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-openfeign</artifactId>
		</dependency>
		<dependency>
			<groupId>jakarta.xml.bind</groupId>
			<artifactId>jakarta.xml.bind-api</artifactId>
		</dependency>
		<dependency>
			<groupId>com.sun.xml.messaging.saaj</groupId>
			<artifactId>saaj-impl</artifactId>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.springframework.ws/spring-ws-core -->
		<dependency>
			<groupId>org.springframework.ws</groupId>
			<artifactId>spring-ws-core</artifactId>
		</dependency>
		<!-- https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api -->
		<dependency>
			<groupId>com.sun.xml.ws</groupId>
			<artifactId>jaxws-rt</artifactId>
			<version>2.3.7</version>
		</dependency>
		<dependency>
			<groupId>org.glassfish.jaxb</groupId>
			<artifactId>jaxb-runtime</artifactId>
		</dependency>
		<dependency>
			<groupId>javax.xml.bind</groupId>
			<artifactId>jaxb-api</artifactId>
			<version>2.3.1</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/javax.activation/activation -->
		<dependency>
			<groupId>javax.activation</groupId>
			<artifactId>activation</artifactId>
			<version>1.1</version>
		</dependency>
		<dependency>
			<groupId>com.fasterxml.jackson.dataformat</groupId>
			<artifactId>jackson-dataformat-xml</artifactId>
		</dependency>
		<!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
		<dependency>
			<groupId>com.google.code.gson</groupId>
			<artifactId>gson</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-ui</artifactId>
			<version>1.5.12</version>
		</dependency>
		<!-- Documentación de API's (Swagger) -->
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-boot-starter</artifactId>
			<version>3.0.0</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-api</artifactId>
			<version>0.11.5</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-impl</artifactId>
			<version>0.11.5</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-jackson</artifactId>
			<version>0.11.5</version> <!-- or 0.11.2 for Java 8 compatibility -->
			<scope>runtime</scope>
		</dependency>

		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-core</artifactId>
			<version>2.21.1</version>
			<scope>compile</scope>
		</dependency>
		<!-- Source: https://mvnrepository.com/artifact/org.springframework.security/spring-security-core -->
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-core</artifactId>
			<version>6.5.4</version>
			<scope>compile</scope>
		</dependency>
	</dependencies>

	<build>
		<!-- Build plugins -->
		<plugins>
			<!-- Arsenal JPA Code Generator for Database Entities and Repositories -->
			<plugin>
				<groupId>com.santander.ars</groupId>
				<artifactId>gln-back-arsenal-jpa-codegen-maven-plugin</artifactId>
				<executions>
					<execution>
						<phase>generate-sources</phase>
						<goals>
							<goal>generate</goal>
						</goals>
					</execution>
				</executions>
				<configuration>
					<sqlScript>${project.basedir}/src/main/resources/schema.sql</sqlScript>
					<entityPackage>
						com.santander.bnc.bsn049.bncbsn049savekycservice.infrastructure.adapters.output.jpa.data</entityPackage>
					<repositoryPackage>
						com.santander.bnc.bsn049.bncbsn049savekycservice.infrastructure.adapters.output.jpa.repository</repositoryPackage>
					<fileOverride>false</fileOverride> <!-- This property is false by default -->
				</configuration>
			</plugin>
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
			<!-- Jacoco Maven Plugin for coverage -->
			<plugin>
				<groupId>org.jacoco</groupId>
				<artifactId>jacoco-maven-plugin</artifactId>
				<executions>
					<execution>
						<id>default-prepare-agent</id>
						<goals>
							<goal>prepare-agent</goal>
						</goals>
					</execution>
					<execution>
						<id>default-report</id>
						<goals>
							<goal>report</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-surefire-plugin</artifactId>
				<configuration>
					<skipTests>true</skipTests>
				</configuration>
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
					<include>schema.sql</include>
					<include>**/darwinchannels.json</include>
				</includes>
			</resource>
		</resources>
	</build>
</project>

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
      componentName: savekycservice
      componentId: CHANGEIT_CMPT_ID
      componentType: microservice
      appId: CHANGEIT_APP_ID
    entity: ESP
    paas-app-version: "6.1.0"
    kafka:
      server: ${env.logging-server}
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
    name: bnc-bsn049-savekycservice
  session:
    store-type: none
  cache:
    type: CAFFEINE #Activated cache caffeine by default (If you want to change the cache to JBoss DataGrid, check the documentacion in confluence)
    caffeine:
      spec: expireAfterWrite=10m #Specifies that each entry should be automatically removed from the cache once that duration has elapsed after the entry’s creation
  lifecycle.timeout-per-shutdown-phase: 2m
  datasource:
    url: ${spring.datasource.url}
    username: ${spring.datasource.username}
    password: ${spring.datasource.password}
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
      ddl-auto: update


logging.level:
  com.santander.bnc.bsn049.bncbsn049savekycservice.Application: INFO
  root: INFO

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

# Servicio de Motor consulta datos basicos
engine:
  service-name: ${engine.service-name}
  protocol: ${kyc.vigia.protocol}
  host: ${engine.host}
  context: ${engine.context}
  trust-store: 
  trust-store-property: 
  mqRoute: ${engine.mqRoute}
# Servicio de vigia
vigia:
  service-name: ${kyc.vigia.service-name}
  protocol: ${kyc.vigia.protocol}
  host: ${kyc.vigia.host}
  context: ${kyc.vigia.risk.context}
  trust-store: 
  trust-store-property: 
# Servicio de vigia listas PEP
vigiaListas:
  service-name: engine-orchestrator
  protocol: http
  host: ${kyc.vigia.host}
  context: ${kyc.vigia.listas.context}
  trust-store: 
  trust-store-property: 
#Meses validos para formulario
valid-month:  12
porcentageVigia: 100
#OneFcc
urlOneFcc: ${onefcc.url}
userOneFcc: ${onefcc.user}     
passOneFcc: ${onefcc.pass}

#Nivel de riesgo
urlriskLevel: ${risklevel.url}


