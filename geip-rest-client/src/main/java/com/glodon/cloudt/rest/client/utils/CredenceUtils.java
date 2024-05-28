package com.glodon.cloudt.rest.client.utils;

import com.alibaba.fastjson.JSONObject;
import com.glodon.cloudt.rest.client.data.LicenseInfo;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Created by chenxy on 2017/5/4.
 */
public class CredenceUtils {
    public static String create(LicenseInfo licenseInfo)
            throws IOException {
        String url = licenseInfo.getRootAddress() + "/identity/extend/credence";
        JSONObject result = sendGetRequest(url);
        if (null == result) {
            return null;
        }
        boolean success = result.getBoolean("success");
        if (!success) {
            return null;
        }
        return result.getString("data");
    }

    public static boolean validate(LicenseInfo licenseInfo, String credence)
            throws IOException {
        String url = licenseInfo.getRootAddress() + "/identity/extend/validate-credence?credence=" + credence;
        JSONObject result = sendGetRequest(url);
        if (null == result) {
            return false;
        }
        return result.getBoolean("success");
    }

    private static JSONObject sendGetRequest(String url) throws IOException {
        HttpGet request = new HttpGet(url);
        request.addHeader("cloudt-x-webapi", "1.0.0");
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(request);
            String responseBody = StreamUtils.readToEndAndClose(response.getEntity().getContent());
            return JSONObject.parseObject(responseBody);
        } finally {
            if (null != httpClient) {
                httpClient.close();
            }
        }
    }
}
