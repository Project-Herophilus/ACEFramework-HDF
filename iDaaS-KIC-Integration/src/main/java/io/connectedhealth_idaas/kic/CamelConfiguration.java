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

  /*@Bean
  private KafkaComponent kafkaComponent(KafkaEndpoint kafkaEndpoint){
    KafkaComponent kafka = new KafkaComponent();
    return kafka;
  }*/

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
      restConfiguration().component("servlet")
              .host("0.0.0.0").port(String.valueOf(simple("{{server.port}}")));

      // Endpoints for direct connectivity
      /*
       * Transactional Audit
       *
       * Direct component within platform to ensure we can centralize logic
       * There are some values we will need to set within every route
       * We are doing this to ensure we dont need to build a series of beans
       * and we keep the processing as lightweight as possible
       *
       *   Simple language reference
       *   https://camel.apache.org/components/latest/languages/simple-language.html
       *
       */
      from("direct:auditing")
              .routeId("iDaaS-Transactions-KIC")
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
              .convertBodyTo(String.class).to(getKafkaTopicUri("opsmgmt_platformtransactions"));

      /*
       * Transactional Audit
       *
       * Direct component within platform to ensure we can centralize logic
       * There are some values we will need to set within every route
       * We are doing this to ensure we dont need to build a series of beans
       * and we keep the processing as lightweight as possible
       *
       *   Simple language reference
       *   https://camel.apache.org/components/latest/languages/simple-language.html
       *
       */
      from("direct:transactionauditing")
              .routeId("iDaaS-App-KIC")
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
              .setHeader("errorID").exchangeProperty("internalMsgID")
              .setHeader("errorData").exchangeProperty("bodyData")
              .setHeader("transactionCount").exchangeProperty("transactionCount")
              .convertBodyTo(String.class).to(getKafkaTopicUri("opsmgmt_appplatformtransactions"));

      //Servlet - External Audit Endpoint
      from("servlet://KIC-Auditing-EndPoint")
              .routeId("KIC-Auditing-EndPoint")
              .convertBodyTo(String.class)
             .wireTap("direct:auditing");

      // Get from Defines KafkaTopic for Integration Processing
        RouteDefinition route = from(getKafkaTopicUri(config.getKafkaTopicName()))
            .removeHeader("breadcrumbId").convertBodyTo(String.class)
            .process("auditProcessor");
            // Output to configured RDBMS ONLY is isStoreinDb = true
            if (config.isStoreInDb()) {
                route.multicast().parallelProcessing().to("direct:file", "direct:db");
                RouteDefinition from = from("direct:db");
                String columns = String.join(",", AuditMessage.DB_PERSISTABLE_FIELDS);
                List<String> namedParams = new ArrayList<>();
                for (String namedParam : AuditMessage.DB_PERSISTABLE_FIELDS) {
                    namedParams.add(":?" + namedParam);
                from = from.setHeader(namedParam, simple("${body." + namedParam + "}"));
                }
                String params = String.join(",", namedParams);
                from.setBody(simple("INSERT INTO " + config.getDbTableName() + " (" + columns + ") VALUES (" + params + ")"))
                .to("jdbc:dataSource?useHeadersAsParameters=true");
                } else {
                route.to("direct:file");
                }
            //  Output JSON Documents ONLY is isStoreinFS = true
            if (config.isStoreInFs()) {
                from("direct:file").marshal().json(JsonLibrary.Jackson)
                .to("file:" + config.getAuditDir());
            }

        // Get from Defintion for App Audit Properties

  }
}
