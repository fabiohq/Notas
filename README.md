
             %%
             %%%
          %   %%%                    %%%%                                                                   %%%%
          %%   %%%%               %%     %%                                                                   %%
          %%%%   %%%              %%      %                              %                                    %%
       %%   %%%   %%%%%           %%%%           %%%        %  %%      %%%       %%%        %  %%         %%% %%       %%%       %  %%
   %%%%%%%   %%%% %%%%%%%%          %%%%%%     %%  %%%    %%%   %%%   %%%%%    %%   %%    %%%   %%%    %%%    %%    %%    %%  %%%%%  %
 %%%%%%%%%%%  %%%%%%%%%%%%%            %%%%         %%    %%%    %%    %%%          %%    %%%    %%   %%%     %%   %%%%%%%%%%   %%
 %%%%%%%%%%%%  %%%%%%%%%%%%      %       %%%   %%%%%%%    %%%    %%    %%%     %%%%%%%    %%%    %%   %%%     %%   %%%          %%
  %%%%%%%%%%%%%%%%%%%%%%%%       %%      %%   %%    %%    %%%    %%    %%%    %%    %%    %%%    %%   %%%     %%    %%%         %%
     %%%%%%%%%%%%%%%%%%           %%%%%%%%     %%%% %%%  %%%%% %%%%%%   %%%%   %%%% %%%  %%%%% %%%%%%   %%%%% %%%    %%%%%%   %%%%%%

:: Spring Boot  (v3.4.4) ::                                                                  :: DARWIN (v6.1.0) ::

2026-05-14 13:54:28.877 [main] INFO  [, ] [bnc-bsn049-mswatchliscreen] com.santander.bnc.bsn049.bncbsn049mswatchliscreen.Application - Starting Application v1.1.1 using Java 17.0.19 with PID 1 (/opt/app/app.jar started by 1001050000 in /opt/app)s
2026-05-14 13:54:28.893 [main] INFO  [, ] [bnc-bsn049-mswatchliscreen] com.santander.bnc.bsn049.bncbsn049mswatchliscreen.Application - The following 1 profile is active: "local"s
2026-05-14 13:54:36.158 [main] WARN  [, ] [bnc-bsn049-mswatchliscreen] com.zaxxer.hikari.util.DriverDataSource - Registered driver with driverClassName=oracle.jdbc.driver.OracleDriver was not found, trying direct instantiation.s
2026-05-14 13:54:38.566 [main] WARN  [, ] [bnc-bsn049-mswatchliscreen] com.zaxxer.hikari.util.DriverDataSource - Registered driver with driverClassName=oracle.jdbc.driver.OracleDriver was not found, trying direct instantiation.s
2026-05-14 13:54:39.595 [main] WARN  [, ] [bnc-bsn049-mswatchliscreen] org.hibernate.engine.jdbc.spi.SqlExceptionHelper - SQL Error: 257, SQLState: 64000s
2026-05-14 13:54:39.595 [main] ERROR [, ] [bnc-bsn049-mswatchliscreen] org.hibernate.engine.jdbc.spi.SqlExceptionHelper - ORA-00257: Archiver error. Connect AS SYSDBA only until resolved.

https://docs.oracle.com/error-help/db/ora-00257/s
2026-05-14 13:54:39.597 [main] WARN  [, ] [bnc-bsn049-mswatchliscreen] org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator - HHH000342: Could not obtain connection to query metadatas
org.hibernate.exception.GenericJDBCException: unable to obtain isolated JDBC connection [ORA-00257: Archiver error. Connect AS SYSDBA only until resolved.

https://docs.oracle.com/error-help/db/ora-00257/] [n/a]
	at org.hibernate.exception.internal.StandardSQLExceptionConverter.convert(StandardSQLExceptionConverter.java:63) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.engine.jdbc.spi.SqlExceptionHelper.convert(SqlExceptionHelper.java:108) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.engine.jdbc.spi.SqlExceptionHelper.convert(SqlExceptionHelper.java:94) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.resource.transaction.backend.jdbc.internal.JdbcIsolationDelegate.delegateWork(JdbcIsolationDelegate.java:116) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.getJdbcEnvironmentUsingJdbcMetadata(JdbcEnvironmentInitiator.java:320) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.initiateService(JdbcEnvironmentInitiator.java:129) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.initiateService(JdbcEnvironmentInitiator.java:81) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.boot.registry.internal.StandardServiceRegistryImpl.initiateService(StandardServiceRegistryImpl.java:130) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.service.internal.AbstractServiceRegistryImpl.createService(AbstractServiceRegistryImpl.java:263) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.service.internal.AbstractServiceRegistryImpl.initializeService(AbstractServiceRegistryImpl.java:238) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.service.internal.AbstractServiceRegistryImpl.getService(AbstractServiceRegistryImpl.java:215) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.boot.model.relational.Database.<init>(Database.java:45) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.boot.internal.InFlightMetadataCollectorImpl.getDatabase(InFlightMetadataCollectorImpl.java:226) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.boot.internal.InFlightMetadataCollectorImpl.<init>(InFlightMetadataCollectorImpl.java:194) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.boot.model.process.spi.MetadataBuildingProcess.complete(MetadataBuildingProcess.java:171) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.metadata(EntityManagerFactoryBuilderImpl.java:1442) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:1513) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.springframework.orm.jpa.vendor.SpringHibernateJpaPersistenceProvider.createContainerEntityManagerFactory(SpringHibernateJpaPersistenceProvider.java:66) ~[spring-orm-6.2.5.jar!/:6.2.5]
	at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.createNativeEntityManagerFactory(LocalContainerEntityManagerFactoryBean.java:390) ~[spring-orm-6.2.5.jar!/:6.2.5]
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.buildNativeEntityManagerFactory(AbstractEntityManagerFactoryBean.java:419) ~[spring-orm-6.2.5.jar!/:6.2.5]
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.afterPropertiesSet(AbstractEntityManagerFactoryBean.java:400) ~[spring-orm-6.2.5.jar!/:6.2.5]
	at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.afterPropertiesSet(LocalContainerEntityManagerFactoryBean.java:366) ~[spring-orm-6.2.5.jar!/:6.2.5]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1859) ~[spring-beans-6.2.5.jar!/:6.2.5]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1808) ~[spring-beans-6.2.5.jar!/:6.2.5]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:601) ~[spring-beans-6.2.5.jar!/:6.2.5]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:523) ~[spring-beans-6.2.5.jar!/:6.2.5]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:339) ~[spring-beans-6.2.5.jar!/:6.2.5]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:347) [spring-beans-6.2.5.jar!/:6.2.5]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:337) [spring-beans-6.2.5.jar!/:6.2.5]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:207) [spring-beans-6.2.5.jar!/:6.2.5]
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:970) [spring-context-6.2.5.jar!/:6.2.5]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:627) [spring-context-6.2.5.jar!/:6.2.5]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:146) [spring-boot-3.4.4.jar!/:3.4.4]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:752) [spring-boot-3.4.4.jar!/:3.4.4]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:439) [spring-boot-3.4.4.jar!/:3.4.4]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:318) [spring-boot-3.4.4.jar!/:3.4.4]
	at org.springframework.boot.builder.SpringApplicationBuilder.run(SpringApplicationBuilder.java:149) [spring-boot-3.4.4.jar!/:3.4.4]
	at com.santander.bnc.bsn049.bncbsn049mswatchliscreen.Application.main(Application.java:38) [!/:1.1.1]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[?:?]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) ~[?:?]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[?:?]
	at java.base/java.lang.reflect.Method.invoke(Method.java:569) ~[?:?]
	at org.springframework.boot.loader.launch.Launcher.launch(Launcher.java:102) [app.jar:1.1.1]
	at org.springframework.boot.loader.launch.Launcher.launch(Launcher.java:64) [app.jar:1.1.1]
	at org.springframework.boot.loader.launch.JarLauncher.main(JarLauncher.java:40) [app.jar:1.1.1]
Caused by: java.sql.SQLException: ORA-00257: Archiver error. Connect AS SYSDBA only until resolved.

https://docs.oracle.com/error-help/db/ora-00257/
	at oracle.jdbc.driver.T4CTTIoer11.processError(T4CTTIoer11.java:709) ~[ojdbc11-23.5.0.24.07.jar!/:23.5.0.24.07]
	at oracle.jdbc.driver.T4CTTIoer11.processError(T4CTTIoer11.java:604) ~[ojdbc11-23.5.0.24.07.jar!/:23.5.0.24.07]
	at oracle.jdbc.driver.T4CTTIoer11.processError(T4CTTIoer11.java:599) ~[ojdbc11-23.5.0.24.07.jar!/:23.5.0.24.07]
	at oracle.jdbc.driver.T4CTTIoauthenticate.processError(T4CTTIoauthenticate.java:899) ~[ojdbc11-23.5.0.24.07.jar!/:23.5.0.24.07]
	at oracle.jdbc.driver.T4CTTIfun.receive(T4CTTIfun.java:1100) ~[ojdbc11-23.5.0.24.07.jar!/:23.5.0.24.07]
	at oracle.jdbc.driver.T4CTTIfun.doRPC(T4CTTIfun.java:408) ~[ojdbc11-23.5.0.24.07.jar!/:23.5.0.24.07]
	at oracle.jdbc.driver.T4CTTIoauthenticate.doOSESSKEY(T4CTTIoauthenticate.java:828) ~[ojdbc11-23.5.0.24.07.jar!/:23.5.0.24.07]
	at oracle.jdbc.driver.T4CConnection.authenticateWithPassword(T4CConnection.java:2033) ~[ojdbc11-23.5.0.24.07.jar!/:23.5.0.24.07]
	at oracle.jdbc.driver.T4CConnection.authenticateUserForLogon(T4CConnection.java:1989) ~[ojdbc11-23.5.0.24.07.jar!/:23.5.0.24.07]
	at oracle.jdbc.driver.T4CConnection.logon(T4CConnection.java:1090) ~[ojdbc11-23.5.0.24.07.jar!/:23.5.0.24.07]
	at oracle.jdbc.driver.PhysicalConnection.connect(PhysicalConnection.java:1206) ~[ojdbc11-23.5.0.24.07.jar!/:23.5.0.24.07]
	at oracle.jdbc.driver.T4CDriverExtension.getConnection(T4CDriverExtension.java:105) ~[ojdbc11-23.5.0.24.07.jar!/:23.5.0.24.07]
	at oracle.jdbc.driver.OracleDriver.connect(OracleDriver.java:886) ~[ojdbc11-23.5.0.24.07.jar!/:23.5.0.24.07]
	at oracle.jdbc.driver.OracleDriver.connect(OracleDriver.java:693) ~[ojdbc11-23.5.0.24.07.jar!/:23.5.0.24.07]
	at com.zaxxer.hikari.util.DriverDataSource.getConnection(DriverDataSource.java:137) ~[HikariCP-5.1.0.jar!/:?]
	at com.zaxxer.hikari.pool.PoolBase.newConnection(PoolBase.java:360) ~[HikariCP-5.1.0.jar!/:?]
	at com.zaxxer.hikari.pool.PoolBase.newPoolEntry(PoolBase.java:202) ~[HikariCP-5.1.0.jar!/:?]
	at com.zaxxer.hikari.pool.HikariPool.createPoolEntry(HikariPool.java:461) ~[HikariCP-5.1.0.jar!/:?]
	at com.zaxxer.hikari.pool.HikariPool.checkFailFast(HikariPool.java:550) ~[HikariCP-5.1.0.jar!/:?]
	at com.zaxxer.hikari.pool.HikariPool.<init>(HikariPool.java:98) ~[HikariCP-5.1.0.jar!/:?]
	at com.zaxxer.hikari.HikariDataSource.getConnection(HikariDataSource.java:111) ~[HikariCP-5.1.0.jar!/:?]
	at org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl.getConnection(DatasourceConnectionProviderImpl.java:126) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess.obtainConnection(JdbcEnvironmentInitiator.java:467) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.resource.transaction.backend.jdbc.internal.JdbcIsolationDelegate.delegateWork(JdbcIsolationDelegate.java:61) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	... 41 more
2026-05-14 13:54:39.607 [main] ERROR [, ] [bnc-bsn049-mswatchliscreen] org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean - Failed to initialize JPA EntityManagerFactory: Unable to create requested service [org.hibernate.engine.jdbc.env.spi.JdbcEnvironment] due to: Unable to determine Dialect without JDBC metadata (please set 'jakarta.persistence.jdbc.url' for common cases or 'hibernate.dialect' when a custom Dialect implementation must be provided)s
2026-05-14 13:54:39.608 [main] WARN  [, ] [bnc-bsn049-mswatchliscreen] org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext - Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Unable to create requested service [org.hibernate.engine.jdbc.env.spi.JdbcEnvironment] due to: Unable to determine Dialect without JDBC metadata (please set 'jakarta.persistence.jdbc.url' for common cases or 'hibernate.dialect' when a custom Dialect implementation must be provided)s
2026-05-14 13:54:39.720 [main] ERROR [, ] [bnc-bsn049-mswatchliscreen] org.springframework.boot.SpringApplication - Application run faileds
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Unable to create requested service [org.hibernate.engine.jdbc.env.spi.JdbcEnvironment] due to: Unable to determine Dialect without JDBC metadata (please set 'jakarta.persistence.jdbc.url' for common cases or 'hibernate.dialect' when a custom Dialect implementation must be provided)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1812) ~[spring-beans-6.2.5.jar!/:6.2.5]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:601) ~[spring-beans-6.2.5.jar!/:6.2.5]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:523) ~[spring-beans-6.2.5.jar!/:6.2.5]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:339) ~[spring-beans-6.2.5.jar!/:6.2.5]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:347) ~[spring-beans-6.2.5.jar!/:6.2.5]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:337) ~[spring-beans-6.2.5.jar!/:6.2.5]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:207) ~[spring-beans-6.2.5.jar!/:6.2.5]
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:970) ~[spring-context-6.2.5.jar!/:6.2.5]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:627) ~[spring-context-6.2.5.jar!/:6.2.5]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:146) ~[spring-boot-3.4.4.jar!/:3.4.4]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:752) [spring-boot-3.4.4.jar!/:3.4.4]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:439) [spring-boot-3.4.4.jar!/:3.4.4]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:318) [spring-boot-3.4.4.jar!/:3.4.4]
	at org.springframework.boot.builder.SpringApplicationBuilder.run(SpringApplicationBuilder.java:149) [spring-boot-3.4.4.jar!/:3.4.4]
	at com.santander.bnc.bsn049.bncbsn049mswatchliscreen.Application.main(Application.java:38) [!/:1.1.1]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[?:?]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) ~[?:?]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[?:?]
	at java.base/java.lang.reflect.Method.invoke(Method.java:569) ~[?:?]
	at org.springframework.boot.loader.launch.Launcher.launch(Launcher.java:102) [app.jar:1.1.1]
	at org.springframework.boot.loader.launch.Launcher.launch(Launcher.java:64) [app.jar:1.1.1]
	at org.springframework.boot.loader.launch.JarLauncher.main(JarLauncher.java:40) [app.jar:1.1.1]
Caused by: org.hibernate.service.spi.ServiceException: Unable to create requested service [org.hibernate.engine.jdbc.env.spi.JdbcEnvironment] due to: Unable to determine Dialect without JDBC metadata (please set 'jakarta.persistence.jdbc.url' for common cases or 'hibernate.dialect' when a custom Dialect implementation must be provided)
	at org.hibernate.service.internal.AbstractServiceRegistryImpl.createService(AbstractServiceRegistryImpl.java:276) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.service.internal.AbstractServiceRegistryImpl.initializeService(AbstractServiceRegistryImpl.java:238) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.service.internal.AbstractServiceRegistryImpl.getService(AbstractServiceRegistryImpl.java:215) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.boot.model.relational.Database.<init>(Database.java:45) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.boot.internal.InFlightMetadataCollectorImpl.getDatabase(InFlightMetadataCollectorImpl.java:226) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.boot.internal.InFlightMetadataCollectorImpl.<init>(InFlightMetadataCollectorImpl.java:194) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.boot.model.process.spi.MetadataBuildingProcess.complete(MetadataBuildingProcess.java:171) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.metadata(EntityManagerFactoryBuilderImpl.java:1442) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:1513) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.springframework.orm.jpa.vendor.SpringHibernateJpaPersistenceProvider.createContainerEntityManagerFactory(SpringHibernateJpaPersistenceProvider.java:66) ~[spring-orm-6.2.5.jar!/:6.2.5]
	at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.createNativeEntityManagerFactory(LocalContainerEntityManagerFactoryBean.java:390) ~[spring-orm-6.2.5.jar!/:6.2.5]
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.buildNativeEntityManagerFactory(AbstractEntityManagerFactoryBean.java:419) ~[spring-orm-6.2.5.jar!/:6.2.5]
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.afterPropertiesSet(AbstractEntityManagerFactoryBean.java:400) ~[spring-orm-6.2.5.jar!/:6.2.5]
	at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.afterPropertiesSet(LocalContainerEntityManagerFactoryBean.java:366) ~[spring-orm-6.2.5.jar!/:6.2.5]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1859) ~[spring-beans-6.2.5.jar!/:6.2.5]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1808) ~[spring-beans-6.2.5.jar!/:6.2.5]
	... 21 more
Caused by: org.hibernate.HibernateException: Unable to determine Dialect without JDBC metadata (please set 'jakarta.persistence.jdbc.url' for common cases or 'hibernate.dialect' when a custom Dialect implementation must be provided)
	at org.hibernate.engine.jdbc.dialect.internal.DialectFactoryImpl.determineDialect(DialectFactoryImpl.java:191) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.engine.jdbc.dialect.internal.DialectFactoryImpl.buildDialect(DialectFactoryImpl.java:87) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.getJdbcEnvironmentWithDefaults(JdbcEnvironmentInitiator.java:181) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.getJdbcEnvironmentUsingJdbcMetadata(JdbcEnvironmentInitiator.java:392) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.initiateService(JdbcEnvironmentInitiator.java:129) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.initiateService(JdbcEnvironmentInitiator.java:81) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.boot.registry.internal.StandardServiceRegistryImpl.initiateService(StandardServiceRegistryImpl.java:130) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.service.internal.AbstractServiceRegistryImpl.createService(AbstractServiceRegistryImpl.java:263) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.service.internal.AbstractServiceRegistryImpl.initializeService(AbstractServiceRegistryImpl.java:238) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.service.internal.AbstractServiceRegistryImpl.getService(AbstractServiceRegistryImpl.java:215) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.boot.model.relational.Database.<init>(Database.java:45) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.boot.internal.InFlightMetadataCollectorImpl.getDatabase(InFlightMetadataCollectorImpl.java:226) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.boot.internal.InFlightMetadataCollectorImpl.<init>(InFlightMetadataCollectorImpl.java:194) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.boot.model.process.spi.MetadataBuildingProcess.complete(MetadataBuildingProcess.java:171) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.metadata(EntityManagerFactoryBuilderImpl.java:1442) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:1513) ~[hibernate-core-6.6.11.Final.jar!/:6.6.11.Final]
	at org.springframework.orm.jpa.vendor.SpringHibernateJpaPersistenceProvider.createContainerEntityManagerFactory(SpringHibernateJpaPersistenceProvider.java:66) ~[spring-orm-6.2.5.jar!/:6.2.5]
	at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.createNativeEntityManagerFactory(LocalContainerEntityManagerFactoryBean.java:390) ~[spring-orm-6.2.5.jar!/:6.2.5]
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.buildNativeEntityManagerFactory(AbstractEntityManagerFactoryBean.java:419) ~[spring-orm-6.2.5.jar!/:6.2.5]
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.afterPropertiesSet(AbstractEntityManagerFactoryBean.java:400) ~[spring-orm-6.2.5.jar!/:6.2.5]
	at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.afterPropertiesSet(LocalContainerEntityManagerFactoryBean.java:366) ~[spring-orm-6.2.5.jar!/:6.2.5]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1859) ~[spring-beans-6.2.5.jar!/:6.2.5]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1808) ~[spring-beans-6.2.5.jar!/:6.2.5]
	... 21 more
