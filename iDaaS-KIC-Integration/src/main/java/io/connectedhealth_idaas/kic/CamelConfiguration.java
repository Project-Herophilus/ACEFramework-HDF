/**
 *  Copyright 2005-2018 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package io.connectedhealth_idaas.kic;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.apache.camel.component.kafka.KafkaComponent;
import org.apache.camel.component.kafka.KafkaEndpoint;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
//import javax.sql.DataSource;
import org.apache.camel.component.sql.SqlComponent;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
// Custom Parser Library
import io.connectedhealth_idaas.kic.structures.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class CamelConfiguration extends RouteBuilder {

    private static final Logger log = LoggerFactory.getLogger(CamelConfiguration.class);

    @Autowired
    private ConfigProperties config;

    @Bean
    private KafkaEndpoint kafkaEndpoint() {
        KafkaEndpoint kafkaEndpoint = new KafkaEndpoint();
        return kafkaEndpoint;
    }

    @Bean
    ServletRegistrationBean camelServlet() {
        // use a @Bean to register the Camel servlet which we need to do
        // because we want to use the camel-servlet component for the Camel REST service
        ServletRegistrationBean mapping = new ServletRegistrationBean();
        mapping.setName("CamelServlet");
        mapping.setLoadOnStartup(1);
        mapping.setServlet(new CamelHttpTransportServlet());
        mapping.addUrlMappings("/idaas/*");
        return mapping;
    }

    private String getKafkaTopicUri(String topic) {
        return "kafka:" + topic + "?brokers=" +
                config.getKafkaBrokers();
    }

    @Override
    public void configure() throws Exception {
        // https://tomd.xyz/camel-rest/
        // Rest Configuration
        // Define the implementing component - and accept the default host and port
        //restConfiguration().component("servlet").host("0.0.0.0").port(String.valueOf(simple("{{server.port}}")));

        /*
         * Direct connections for processing needs
         *
         */
        // Data Integration
        from("direct:auditing")
            .routeId("iDaaS-DataIntegration-KIC")
            .setHeader("messageprocesseddate").simple("${date:now:yyyy-MM-dd}")
            .setHeader("messageprocessedtime").simple("${date:now:HH:mm:ss:SSS}")
            .setHeader("processingtype").exchangeProperty("processingtype")
            .setHeader("industrystd").exchangeProperty("industrystd")
            .setHeader("component").exchangeProperty("componentname")
            .setHeader("messagetrigger").exchangeProperty("messagetrigger")
            .setHeader("processname").exchangeProperty("processname")
            .setHeader("auditdetails").exchangeProperty("auditdetails")
            .setHeader("camelID").exchangeProperty("camelID")
            .setHeader("exchangeID").exchangeProperty("exchangeID")
            .setHeader("internalMsgID").exchangeProperty("internalMsgID")
            .setHeader("bodyData").exchangeProperty("bodyData")
            .convertBodyTo(String.class).to(getKafkaTopicUri("{{idaas.integrationTopic}}"));
        // Application-API requests response style auditing
        from("direct:transactionauditing")
            .routeId("iDaaS-AppIntegration-KIC")
            .setHeader("messageprocesseddate").simple("${date:now:yyyy-MM-dd}")
            .setHeader("messageprocessedtime").simple("${date:now:HH:mm:ss:SSS}")
            .setHeader("processingtype").exchangeProperty("processingtype")
            .setHeader("industrystd").exchangeProperty("industrystd")
            .setHeader("messagetrigger").exchangeProperty("messagetrigger")
            .setHeader("component").exchangeProperty("componentname")
            .setHeader("processname").exchangeProperty("processname")
            .setHeader("camelID").exchangeProperty("camelID")
            .setHeader("exchangeID").exchangeProperty("exchangeID")
            .setHeader("internalMsgID").exchangeProperty("internalMsgID")
            .setHeader("bodyData").exchangeProperty("bodyData")
            .setHeader("errorID").exchangeProperty("errorID")
            .setHeader("errorData").exchangeProperty("errorData")
            .setHeader("transactionCount").exchangeProperty("transactionCount")
            .setHeader("requestType").exchangeProperty("requestType")
            .setHeader("transactionDirection").exchangeProperty("transactionDirection")
            .convertBodyTo(String.class).to(getKafkaTopicUri("{{idaas.appintegrationTopic}}"));
        // Logging
        from("direct:logging")
                .routeId("logging")
                .log(LoggingLevel.INFO, log, "Logged Message: [${body}]")
        ;

        /*
         *  Servlets - External Audit Endpoint
         */
        // Data Integration
        from("servlet://KIC-IntegrationAuditing-EndPoint")
                .routeId("KIC-IntegrationAuditing-EndPoint")
                .convertBodyTo(String.class)
                .wireTap("direct:auditing");
        // Application Integration
        from("servlet://KIC-ApplicationAuditing-EndPoint")
                .routeId("KIC-ApplicationAuditing-EndPoint")
                .convertBodyTo(String.class)
                .wireTap("direct:transactionauditing");
        /*
         * Servlet - Testing EndPoints
         */
        // Data Integration
        from("servlet://Testing-KIC-DataIntegrationAuditing-EndPoint")
                .routeId("Testing-KIC-IntegrationAuditing-EndPoint")
                .convertBodyTo(String.class)
                // set Auditing Properties
                .setProperty("processingtype").constant("data")
                .setProperty("appname").constant("iDAAS-KIC-Test")
                .setProperty("industrystd").constant("N/A")
                .setProperty("messagetrigger").constant("N/A")
                .setProperty("componentname").simple("${routeId}")
                .setProperty("processname").constant("Input")
                .setProperty("camelID").simple("${camelId}")
                .setProperty("exchangeID").simple("${exchangeId}")
                .setProperty("internalMsgID").simple("${id}")
                .setProperty("bodyData").simple("${body}")
                .setProperty("auditdetails").constant("Data Integration KIC message received")
                .wireTap("direct:auditing");
        // Application Integration
        from("servlet://Testing-KIC-ApplicationAuditing-EndPoint")
                .routeId("Testing-KIC-ApplicationAuditing-EndPoint")
                .setProperty("processingtype").constant("app-data")
                .setProperty("appname").constant("iDAAS-KIC-Test")
                .setProperty("industrystd").constant("N/A")
                .setProperty("messagetrigger").constant("N/A")
                .setProperty("componentname").simple("${routeId}")
                .setProperty("processname").constant("Input-AppIntgrtn-Test")
                .setProperty("camelID").simple("${camelId}")
                .setProperty("exchangeID").simple("${exchangeId}")
                .setProperty("internalMsgID").simple("${id}")
                .setProperty("bodyData").simple("${body}")
                .setProperty("auditdetails").constant("App Integration KIC message received")
                .setProperty("requestType").constant("CLI/SDK")
                .setProperty("errorID").constant("123")
                .setProperty("errorData").constant("Error Detail: Error Occurred")
                .setProperty("transactionCount").constant("5")
                .setProperty("transactionDirection").constant("Request")
                .convertBodyTo(String.class)
                .wireTap("direct:transactionauditing");

        /*
         *   Kafka Topic Processing to Data/File Tier
         */
        // Data Integration Kafka Topic Processing to Data Tier
        from(getKafkaTopicUri(config.getIntegrationTopic()))
             .routeId("KIC-DataIntegration-Topic")
             //.log(LoggingLevel.INFO, log, "Message Pre Remove Headers: [${body}]")
             .removeHeader("breadcrumbId").convertBodyTo(String.class)
             .choice().when(simple("{{idaas.storeInFs_DataIntegrationAudit}}"))
                .to("file:" + config.auditDir_DataIntegrationAuditLocation())
             .choice().when(simple("{{idaas.storeInDb_DataIntegrationAudit}}"))
                 //.log(LoggingLevel.INFO, log, "Data Integration Message: [${body}]")
                 //.log(LoggingLevel.INFO, log, "Data Integration Message: [${body.camelid}]")
             /*   .unmarshal(new JacksonDataFormat(DataIntegrationAuditMessage.class))
                .to("sql:insert into data_intgrtn_insight (messagedate, processingtype, industrystd," +
                        "component, messagetrigger, processname, auditdetails, exchangeid, " +
                        "bodydata, messagetime, camelid) " +
                        "values (:#${body.messageprocesseddate},:#${body.processingtype}" +
                        ",:#${body.industrystd},:#${body.component},:#${body.messagetrigger},:#${body.processname}" +
                        ",:#${body.auditdetails},:#${body.exchangeID},:#${body.bodyData},:#${body.messageprocessedtime}" +
                        ",:#${body.camelID})")*/
            .endChoice();

        // App Integration Kafka Topic Processing to Data Tier
        from(getKafkaTopicUri(config.getAppintegrationTopic()))
             .routeId("KIC-AppIntegration-Topic")
             //.log(LoggingLevel.INFO, log, "Message Pre Remove Headers: [${body}]")
             .removeHeader("breadcrumbId").convertBodyTo(String.class)
             .choice().when(simple("{{idaas.storeInFs_AppIntegrationAudit}}"))
                .to("file:" + config.auditDir_AppIntegrationAuditLocation())
             .choice().when(simple("{{idaas.storeInDb_DataIntegrationAudit}}"))
                //.log(LoggingLevel.INFO, log, "Data Integration Message: [${body}]")
                //.log(LoggingLevel.INFO, log, "Data Integration Message: [${body.camelid}]")
               /* .unmarshal(new JacksonDataFormat(AppIntegrationAuditMessage.class))
                .to("sql:insert into app_intgrtn_insight (messagedate, processingtype, industrystd," +
                        "component, messagetrigger, processname, auditdetails, exchangeid, " +
                        "bodydata, messagetime, camelid,requesttype,errorid,errordata," +
                        "transactioncount,transactiondirection) " +
                        "values (:#${body.messageprocesseddate},:#${body.processingtype}" +
                        ",:#${body.industrystd},:#${body.component},:#${body.messagetrigger},:#${body.processname}" +
                        ",:#${body.auditdetails},:#${body.exchangeID},:#${body.bodyData},:#${body.messageprocessedtime}" +
                        ",:#${body.camelID},:#${body.requestType},:#${body.errorID}" +
                        ",:#${body.errorData},:#${body.transactionCount},:#${body.transactionDirection})")*/
              .endChoice();

    }

}
