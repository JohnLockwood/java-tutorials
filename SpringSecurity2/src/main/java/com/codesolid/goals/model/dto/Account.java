package com.codesolid.goals.model.dto;

/**
 * Class Account
 * Description:
 */
public class Account {

    private long id;
    private String domainName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
}
