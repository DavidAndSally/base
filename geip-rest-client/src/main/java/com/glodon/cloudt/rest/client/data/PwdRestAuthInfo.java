package com.glodon.cloudt.rest.client.data;

/**
 * Created by chenxy on 2016/3/14.
 */
public class PwdRestAuthInfo extends RestAuthInfo {

    private static final long serialVersionUID = 8318643859971870036L;
    private String account;
    private String password;
    private String restRootAddress;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRestRootAddress() {
        return restRootAddress;
    }

    public void setRestRootAddress(String restRootAddress) {
        this.restRootAddress = restRootAddress;
    }
}
