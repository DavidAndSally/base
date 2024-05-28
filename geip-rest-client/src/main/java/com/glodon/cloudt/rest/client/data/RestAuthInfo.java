package com.glodon.cloudt.rest.client.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证信息基类
 * <p/>
 * Created by chenxy on 2016/3/14.
 */
public class RestAuthInfo implements Serializable {

    private static final long serialVersionUID = -1373752353267301656L;

    private Map<String, String> headers = new HashMap<String, String>();

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }
}
