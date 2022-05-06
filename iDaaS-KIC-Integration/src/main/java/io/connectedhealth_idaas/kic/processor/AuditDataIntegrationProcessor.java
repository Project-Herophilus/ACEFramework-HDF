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
package io.connectedhealth_idaas.kic.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.Map;
import io.connectedhealth_idaas.kic.CamelConfiguration;
// Import of Data Structures
import io.connectedhealth_idaas.kic.structures.*;

@Component
public class AuditDataIntegrationProcessor implements Processor {

    private static final Logger log = LoggerFactory.getLogger(CamelConfiguration.class);

    @Override
    public void process(Exchange exchange) {
        DataIntegrationAuditMessage message = new DataIntegrationAuditMessage();
        Map<String, Object> headers = exchange.getIn().getHeaders();

        //log.debug("Headers: "+headers.toString());

        message.setMessageprocesseddate(getHeader(headers, "messageprocesseddate"));
        message.setAuditdetails(getHeader(headers, "auditdetails"));
        message.setBodyData(getHeader(headers, "bodyData"));
        message.setCamelid(getHeader(headers, "camelID"));
        message.setComponent(getHeader(headers, "component"));
        message.setExchangeid(getHeader(headers, "exchangeid"));
        message.setIndustrystd(getHeader(headers, "industrystd"));
        message.setInternalmsgid(getHeader(headers, "internalMsgID"));
        message.setMessageprocessedtime(getHeader(headers, "messageprocessedtime"));
        message.setMessagetrigger(getHeader(headers, "messagetrigger"));
        message.setProcessingtype(getHeader(headers, "processingtype"));
        message.setProcessname(getHeader(headers, "processname"));

        exchange.getOut().setBody(message.toString());
    }

    private String getHeader(Map<String, Object> headers, String name) {
        Object objectValue = headers.get(name);
        if (objectValue instanceof byte[]) {
            return new String((byte[]) objectValue, Charset.defaultCharset());
        } else if (objectValue instanceof String){
            return (String) objectValue;
        } else {
            return null;
        }
    }


}
