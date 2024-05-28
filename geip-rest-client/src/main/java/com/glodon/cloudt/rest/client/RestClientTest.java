package com.glodon.cloudt.rest.client;

import com.glodon.cloudt.rest.client.data.HmacRestAuthInfo;
import com.glodon.cloudt.rest.client.data.LicenseInfo;
import com.glodon.cloudt.rest.client.exception.AuthenticateException;
import com.glodon.cloudt.rest.client.impl.HmacRestServiceClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wr
 */
public class RestClientTest {
    private static final String URL = "http://geip-api-test.glodon.com/cloudt/v3/service/identification/rest-license/authorize";

    public Map<String, String> testLic() throws AuthenticateException {
        // 创建客户端实例
        RestServiceClient serviceClient = HmacRestServiceClient.getInstance();
        // 构建认证信息
        HmacRestAuthInfo restAuthInfo = new HmacRestAuthInfo();

        restAuthInfo.setLicPath("c:\\auth123.lic");

        // 设置认证信息
        serviceClient.authenticate(restAuthInfo);
        LicenseInfo licenseInfo = serviceClient.getLicenseInfo();

        Map<String, String> map = new HashMap<String, String>();
        map.put("computerId", "computerId");
        map.put("accessId", licenseInfo.getAccessId());
        map.put("signature", licenseInfo.getSecret());
        map.put("method", "GET");
        map.put("timestamps", "123456789");
        map.put("url", URL.toUpperCase());

        StringBuilder policyBuilder = new StringBuilder();
        policyBuilder.append(map.get("url") + "\n").append(map.get("method") + "\n")
                .append(map.get("timestamps") + "\n").append(map.get("accessId"));
        String signature = HmacRestServiceClient.getSignature(policyBuilder.toString(), licenseInfo.getSecret());

        map.put("signature", signature);
        return map;
    }
}
