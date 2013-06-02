package com.codesolid.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service(value="testBean")
public class TestBean {
    private String applicationName;
    private String databaseUser;

    // This property is set in common.properties and will be the same
    // for all environments, test, production, etc.
    @Value("${application.name}")
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    // In contrast, this property and other database related properties
    // are quite likely different in test and production.
    @Value("${database.username}")
    public void setDatabaseUser(String databaseUser) {
        this.databaseUser = databaseUser;
    }

    public String getDatabaseUser() {
        return databaseUser;
    }

    public String getApplicationName() {
        return applicationName;
    }
}
