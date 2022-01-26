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

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "idaas")
public class ConfigProperties {

    private String kafkaBrokers;
    private String integrationTopic;
    private String appintegrationTopic;

    private String auditDir;
    private Boolean storeInDb;
    private Boolean storeInFs;
    private String dbDriverClassName;
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    private String dbIntegrationTableName;
    private String dbAppIntegrationTableName;
    private String dbAppTableName;
    private Boolean createDbTable;

    //Method for external invocation of Processors as needed
    String getKafkaTopicUri(String topic) {
        ConfigProperties config;
        return "kafka:" + topic + "?brokers=" +
                getKafkaBrokers();
    }


    // Kafka
    public String getKafkaBrokers() {
        return kafkaBrokers;
    }
    public void setKafkaBrokers(String kafkaBrokers) {
        this.kafkaBrokers = kafkaBrokers;
    }
    // Kafka Topics
    // Kafka - Integration
    public String getIntegrationTopic() {return integrationTopic;}
    public void setIntegrationTopic(String integrationTopic) { this.integrationTopic = integrationTopic;}
    public String getAppintegrationTopic() {return appintegrationTopic;}
    public void setAppintegrationTopic(String appintegrationTopic) { this.appintegrationTopic = appintegrationTopic;}

    //Audit Dir
    public String getAuditDir() {
        return auditDir;
    }
    public void setAuditDir(String auditDir) {
        this.auditDir = auditDir;
    }

    // Database Configuration
    public boolean isStoreInDb() {
        return storeInDb != null && storeInDb;
    }
    public void setStoreInDb(Boolean storeInDb) { this.storeInDb = storeInDb; }
    public Boolean getStoreInDb() {
        return storeInDb;
    }

    public boolean isCreateDbTable() {
        return createDbTable != null && createDbTable;
    }
    public Boolean getCreateDbTable() {
        return createDbTable;
    }
    public void setCreateDbTable(Boolean createDbTable) {
        this.createDbTable = createDbTable;
    }

    public String getDbUrl() {
        return dbUrl;
    }
    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUsername() {
        return dbUsername;
    }
    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }
    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbDriverClassName() {
        return dbDriverClassName;
    }
    public void setDbDriverClassName(String dbDriverClassName) {
        this.dbDriverClassName = dbDriverClassName;
    }

    // DB Specific Tables
    // Integration Auditing
    public String getdbIntegrationTableName() {
        return dbIntegrationTableName;
    }
    public void setdbIntegrationTableName(String dbIntegrationTableName) { this.dbIntegrationTableName = dbIntegrationTableName;
    }
    // Application Integration Auditing
    public String getdbAppIntegrationTableName() {
        return dbAppIntegrationTableName;
    }
    public void setdbAppIntegrationTableName(String dbAppIntegrationTableName) { this.dbAppIntegrationTableName = dbAppIntegrationTableName;
    }
    //public String getdbAppTableName() {return dbAppTableName; }
    // public void setdbAppTableName(String dbAppTableName) { this.dbAppTableName = dbAppTableName; }

    // FS - FileSystem
    public boolean isStoreInFs() { return storeInFs; }
    public void setStoreInFs(boolean storeInFs) {
        this.storeInFs = storeInFs;
    }
    public Boolean getStoreInFs() {
        return storeInFs;
    }

}
