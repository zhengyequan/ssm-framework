package com.yqbaba.common.entity;

import com.yqbaba.framework.entity.BaseEntity;

public class GlobalConfig extends BaseEntity {

    private static final long serialVersionUID = -349444163875780011L;

    private String domainName;

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

}
