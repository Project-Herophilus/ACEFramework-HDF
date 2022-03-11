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

import org.apache.camel.builder.RouteBuilder;
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

import java.util.ArrayList;
import java.util.List;

@Component
public class CamelConfiguration extends RouteBuilder {

  private static final Logger log = LoggerFactory.getLogger(CamelConfiguration.class);

  @Autowired
  private ConfigProperties config;

  @Bean
  private KafkaEndpoint kafkaEndpoint(){
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

      // Endpoints for direct auditing processing
      // Integration Based Auditing
      /*
       *
       *   Simple language reference
       *   https://camel.apache.org/components/latest/languages/simple-language.html
       *
       */
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
              .setHeader("errorID").exchangeProperty("internalMsgID")
              .setHeader("errorData").exchangeProperty("bodyData")
              .setHeader("transactionCount").exchangeProperty("transactionCount")
              .setHeader("requestType").exchangeProperty("requestType")
              .setHeader("transactionDirection").exchangeProperty("transactionDirection")
              .convertBodyTo(String.class).to(getKafkaTopicUri("{{idaas.appintegrationTopic}}"));

      // Servlet - External Audit Endpoint
      from("servlet://KIC-DataIntegrationAuditing")
              .routeId("KIC-IntegrationAuditing-EndPoint")
              .convertBodyTo(String.class)
              .wireTap("direct:auditing");
      //Servlet - External Audit Endpoint
      from("servlet://KIC-ApplicationIntegrationAuditing")
              .routeId("KIC-ApplicationAuditing-EndPoint")
              .convertBodyTo(String.class)
              .wireTap("direct:transactionauditing");

        // Get from Defines KafkaTopic for Integration Processing
        RouteDefinition route = from(getKafkaTopicUri(config.getIntegrationTopic()))
            .routeId("KIC-DataIntegrationAuditing-Persistence")
            .removeHeader("breadcrumbId").convertBodyTo(String.class)
            .process("auditIntegrationProcessor");
            // Output to configured RDBMS ONLY is isStoreinDb = true
            //if (config.isStoreInDb()) {
            if (config.isStoreInDb_DataIntegrationAudit())
            {
                route.multicast().parallelProcessing().to("direct:file", "direct:db");
                RouteDefinition from = from("direct:db");
                String columns = String.join(",", AuditMessage.DB_Integration_PERSISTABLE_FIELDS);
                List<String> namedParams = new ArrayList<>();
                for (String namedParam : AuditMessage.DB_Integration_PERSISTABLE_FIELDS) {
                    namedParams.add(":?" + namedParam);
                from = from.setHeader(namedParam, simple("${body." + namedParam + "}"));
                }
                String params = String.join(",", namedParams);
                from.setBody(simple("INSERT INTO " + config.getdbIntegrationTableName() + " (" + columns + ") VALUES (" + params + ")"))
                .to("jdbc:dataSource?useHeadersAsParameters=true");
            } else {
                route.to("direct:file");
                }
            //  Output JSON Documents ONLY is isStoreinFS = true
            if (config.isStoreInFs_DataIntegrationAudit()) {
                from("direct:file").marshal().json(JsonLibrary.Jackson)
                .to("file:" + config.auditDir_DataIntegrationAuditLocation());
            }

        // Application Integration Auditing Processor
        RouteDefinition route2 = from(getKafkaTopicUri(config.getAppintegrationTopic()))
               .routeId("KIC-AppIntegrationAuditing-Persistence")
               .removeHeader("breadcrumbId").convertBodyTo(String.class)
              .process("auditAppIntegrationProcessor");
        // Output to configured RDBMS ONLY is isStoreinDb = true
        //if (config.isAppAuditStoreInDb()) {
        if (config.storeInDb_AppIntegrationAudit)
        {
          route2.multicast().parallelProcessing().to("direct:file2", "direct:db2");
          RouteDefinition from = from("direct:db2");
          String columns = String.join(",", AuditMessage.DB_AppIntegration_PERSISTABLE_FIELDS);
          List<String> namedParams = new ArrayList<>();
          for (String namedParam : AuditMessage.DB_AppIntegration_PERSISTABLE_FIELDS) {
              namedParams.add(":?" + namedParam);
              from = from.setHeader(namedParam, simple("${body." + namedParam + "}"));
          }
          String params = String.join(",", namedParams);
          from.setBody(simple("INSERT INTO " + config.getdbAppIntegrationTableName() + " (" + columns + ") VALUES (" + params + ")"))
                  .to("jdbc:dataSource?useHeadersAsParameters=true");
        } else {
          route2.to("direct:file2");
        }
      //  Output JSON Documents ONLY is isStoreinFS = true
     if (config.isStoreInFs_AppIntegrationAudit()) {
         from("direct:file2").marshal().json(JsonLibrary.Jackson)
                 .to("file:" + config.auditDir_AppIntegrationAuditLocation());
     }


  }
}
