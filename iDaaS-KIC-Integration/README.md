# iDaaS KIC Integration
iDaaS KIC Integration is specifically designed to be a comprehensive integration tier for iDaaS. As parts of what it enables it includes
API endpoints, defined topic endpoints, parsing and persistence layers for the data it injests. All of the key settings needed are provided within properties for simplifying the implementation needs. The iDaaS KIC architecture is intended to enable two critical forms of connectivity to its capabilities: direct connectivity via topics/queues and service endpoint connectivity. This decision was made to provide teams the greatest amount of flexibility.

![iDaaS Dataflow](https://github.com/Project-Herophilus/Project-Herophilus-Assets/blob/main/Platform/Images/iDAAS-Platform/Architectures/iDaaS-KIC.png)

## Add-Ons
This module contains three supporting directories. The intent of these artifacts to enable resources to work locally: <br/>
+ DataTier: DDL that support this implementation
+ Grafana-Dashboards: Some sample dashboards to help quickly see the data in the RDBMS data tier

## Data Persistence Flow Steps
Data can be pushed into specific topics from other iDaaS components OR by using the endpoints this solution provides.

1. The integration application subscribes to all the data from the specific topic.<br/>
2. The data will be processed from the topic and the header attributes will all be parsed.<br/>
3. Depending on the settings in the application.properties the header attributes can go to files, or a relational
database table. <br/>

# Platform
In this section we will cover various aspects of this module.

## Pre-Requisites
For all iDaaS design patterns it should be assumed that you will either install as part of this effort, or have the following:
1. An existing Kafka (or some flavor of it) up and running. Red Hat currently implements AMQ-Streams based on Apache Kafka; however, we
   have implemented iDaaS with numerous Kafka implementations. Please see the following files we have included to try and help: <br/>
    - [Kafka](https://github.com/Project-Herophilus/Project-Herophilus-Assets/Kafka.md): Kafka for non-Windows implementations.
    - [KafkaWindows](https://github.com/Project-Herophilus/Project-Herophilus-Assets/KafkaWindows.md): Kafka for Windows
      specific implementations. </br>
      No matter the platform chosen it is important to know that the Kafka out of the box implementation might require
      some changes depending upon your implementation needs. Here are a few we have made to ensure: <br/>
    - In <kafka install dir>/config/consumer.properties file we want to change the property of auto.offset.reset to earliest.
      This is intended to enable any new system entering the group to read ALL the messages from the start.
      <i> auto.offset.reset=earliest</i> <br/>.
2. Some understanding of building, deploying Java artifacts and the commands associated. You will need some knowledge and
   an install of Maven to leverage, more details about Maven can be found [here](https://maven.apache.org/install.html). As part of this
   your systems would need some active internet connectivity, this is to ensure that if any Maven commands are run and any
   libraries need to be pulled down they can.
3. When using Kafka it is always good to have some tooling to have insight into the data within topics. There are numerous
   potential tools such as tooling like [Kafka Tool](https://www.kafkatool.com/), [KafDrop](https://github.com/obsidiandynamics/kafdrop),
   [Provectus Kafka UI](https://github.com/provectus/kafka-ui).

## Database Technologies: RDBMS / NoSQL / Cloud Enablement
We wanted to provide a very simple way to have users be able to visualize and report on anything the iDaaS framework(s) 
do. So we have included a very basic extensible relational database tier. Prior to 2022 we supported other database
technologies. However, to simplify what we deliver we leverage <a href="https://www.postgresql.org" target="_blank">
PostgreSQL</a>: PostgreSQL v9.6 or greater<br>. We also realize that some groups just want the raw data and to use other
technologies so we also support within the platform the ability to natively write out the data to a filesystem as well.

# Platform Implementation
While you can choose not to store the data that KIC parses and processes the majority of resources we have worked with
leverage an RDBMS. 
   
## Filesystem
1. Configure the application.properties to set the directory where the JSON documents created will be placed
2. Start up and start processing data

## RDBMS
1. Within the Supported RDBMS we need to create a user and password and then keep the information for later in 
the configuration process.
2. Run the DDL for the database from DataTier/Postgres (latest release for your RDBMS)
3. Configure the application.properties for your specific RDBMS settings (database server name, port, username, 
   password, and database name )
4. Start up and start processing data

 
# Configuring the Platform
The platform has a number of settings and configurational capabilities provided through a simple to read and enhance
application.properties file. We implemented this to ensure that implementations could be in control of common platform
needs like topic names, kafka server details, file system locations (if used), RDBMS settings (if used) and more.

You can customize default DB connection details by providing the following properties: <br/>
Postgres Property Example<br/>:
```
Postgres Property Example<br/>
```properties
idass.dbDriverClassName=org.postgresql.Driver
idaas.dbUrl=jdbc:postgresql://localhost:5432/audit_db
idaas.dbUsername=postgres
idass.dbPassword=Postgres123
idaas.dbTableName=audit
idaas.createDbTable=true
```

application.properties in detail:
```
# Admin Interface Settings
management.endpoints.web.exposure.include=hawtio,jolokia
hawtio.authenticationEnabled = false
hawtio.offline = true
management.endpoint.hawtio.enabled=true
management.endpoint.jolokia.enabled=true
# urls
# http://localhost:9970/actuator/jolokia/read/org.apache.camel:context=*,type=routes,name=*
# http://localhost:9970/actuator/hawtio/index.html
# Used for internal HTTP server managing application
# Must be unique and defined otherwise defaults to 8080
# used for any Fuse SpringBoot developed assets
# the options from org.apache.camel.spring.boot.CamelConfigurationProperties can be configured here
camel.springboot.name=iDaaS-KIC
server.port=9970
management.port=9970
server.max-http-header-size=200000
# disable all management enpoints except health
#endpoints.enabled = false
#endpoints.health.enabled = true
#spring.main.web-application-type=none
#camel.springboot.main-run-controller=true
# Kafka Details
idaas.kafkaBrokers=localhost:9092
idaas.kafkaTopicName_AppAudit=opsmgmt_platformtransactions_appaudit
idaas.integrationTopic=kic_dataintgrtntransactions
idaas.appintegrationTopic=kic_appintgrtntransactions
# Audit Settings - Directory Settings
# Data Integration
idaas.storeInFs_DataIntegrationAudit=true
idaas.auditDir_DataIntegrationAuditLocation=audit_dataintegration
# Application Integration
idaas.storeInFs_AppIntegrationAudit=true
idaas.auditDir_AppIntegrationAuditLocation=audit_appintegration
# Relational Database Detail
# Setting the createDbTable=true will try to autocreate a table
idaas.createDbTable=false
# RDBMS
idaas.storeInDb=true
# Relational Database Detail
idaas.storeInDb_DataIntegrationAudit=true
idaas.storeInDb_AppIntegrationAudit=true
# Database Tables
idaas.dbIntegrationTableName=audit_dataintegration
idaas.dbAppIntegrationTableName=audit_appintegration
idaas.dbAppTableName=appintgrtn_insight
# Spring Boot JDBC Database
spring.datasource.url=jdbc:postgresql://localhost:5432/idaas_kic
spring.datasource.username=postgres
spring.datasource.password=Developer123
spring.database.driver-class-name=org.postgresql.Driver
# Postgres - KIC Centric Processing
idaas.dbDriverClassName=org.postgresql.Driver
idaas.dbUrl=jdbc:postgresql://localhost:5432/idaas_kic
idaas.dbPassword=Developer123
idaas.dbUsername=postgres
```

# Start The Engine
This section is intended to have all the needed steps to get this module running.
   
## Building
To ensure the best results please make sure you have the latest code from the specific repositor(ies.)
From the command line, or your preferred IDE, pull the latest code and then you can run a build from the command line
or the IDE of your choice.
We typically recommend command line build options.
```
mvn clean install 
```

## Running
1. If you are running from an IDE or command line for testing then you can run the following command:
```
mvn spring-boot:run
 ```
2. As a java jar based asset (you can find the jar within the target directory). You can run the command as follows:
```
java -jar <jarfilename.jar>
```
If you want to implement custom application.properties when the instance runs you can use a simple command line switch
to ensure a specific application.properties file is used. In the example below we have created a directory named
config in the same directory as the jar file and moved the application.properties file to it. 
```
java -jar <jarfile.jar> --spring.config.location=file:./config/application.properties
```

# Testing
In order for any testing to work you must:

+ Have a Kafka server up and ready to process data to/from.
+ Have iDaaS-KIC platform running (numerous options are provided above)

1. You can use the iDaaS-Simulator-KIC which simply runs and transacts to the iDaaS-KIC tier.
2. There are API endpoints provided. The ports are in application.properties so if you can change them you will just
need to change the following example to align with your changes. For Data Integration, you can use
localhost:9970/KIC-DataIntegrationAuditing. For Application Integration you can use 
localhost:9970/KIC-ApplicationIntegrationAuditing. You can leverage test data for iDaaS-KIC from our Assets
by going to the following specific [link](https://github.com/Project-Herophilus/Project-Herophilus-Assets/tree/main/Testing/TestData/kic-audit)
To test the API endpoints we also have a preconfigured Postman invocation set [here](https://www.postman.com/winter-robot-787282/workspace/intelligent-daas/collection/16526170-fb4cdfdd-9a65-4f04-9e32-b807921c1b5e)  

## Scenario: Processing Audit Transactions
The implementation processes all the data from their respective topics as defined in application.properties.

# Ongoing Enhancements
We maintain all enhancements within the Git Hub portal under the projects tab, please feel free to make recommendations 
our goal is to continually improve.

# Defects/Bugs
All defects or bugs should be submitted through the Git Hub Portal under the issues tab
Happy using and coding....
