package com.glodon.cloudt.rest.client.data;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by chenxy on 2017/5/5.
 */

public class LicenseInfo implements Serializable {

    private static final long serialVersionUID = 3915090447043037461L;
    private String accessId;
    private String secret;
    private String rootAddress;
    private Map<String, String> properties;

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getRootAddress() {
        return rootAddress;
    }

    public void setRootAddress(String rootAddress) {
        this.rootAddress = rootAddress;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}
