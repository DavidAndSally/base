import com.alibaba.fastjson.JSONObject;
import com.glodon.cloudt.rest.client.IntegrateService;
import com.glodon.cloudt.rest.client.RestServiceClient;
import com.glodon.cloudt.rest.client.data.CookieRestAuthInfo;
import com.glodon.cloudt.rest.client.data.HmacRestAuthInfo;
import com.glodon.cloudt.rest.client.data.LicenseInfo;
import com.glodon.cloudt.rest.client.data.RestResponseInfo;
import com.glodon.cloudt.rest.client.exception.AuthenticateException;
import com.glodon.cloudt.rest.client.exception.InvalidUriException;
import com.glodon.cloudt.rest.client.exception.NoAuthenticateException;
import com.glodon.cloudt.rest.client.impl.CookieRestServiceClient;
import com.glodon.cloudt.rest.client.impl.DefaultIntegrateService;
import com.glodon.cloudt.rest.client.impl.HmacRestServiceClient;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenxy on 2015-11-06.
 */
public class RestClientTest {
    public void before() {
        // LicenseNative jnative = LicenseNative.getInstance();
        // jnative.loadLibrary(LoadLibraryMode.FROM_JAR);
        // jnative.setAddress("192.168.132.239");
        // jnative.setDebugMode(true);
    }

    public void testCreateSsoUrl() throws Throwable {
        IntegrateService service = new DefaultIntegrateService();
        service.loadLicense("c:\\auth (12).lic");
        String url = service.createSsoUrl("/labor", "1234", "labor");
        System.out.println(url);
    }

    public void testCreateSsoUrlWithAccount() throws Throwable {
        RestServiceClient service = HmacRestServiceClient.getInstance();
        // 构建认证信息
        HmacRestAuthInfo restAuthInfo = new HmacRestAuthInfo();

        restAuthInfo.setLicPath("c:\\auth (13).lic");

        // 设置认证信息
        service.authenticate(restAuthInfo);

        String url = service.createCloudSsoUrlWithAccount("/labor", "xyale@foxmail.com", "labor");
        System.out.println(url);
    }

    public static void main(String[] args) throws Throwable {
            new RestClientTest().testLic();
    }

    public void testLic() throws InvalidUriException {
        // String url = "http://192.168.8.95:8080/api/tenant/v1.0/messaging/sms/send";
        try {
            // String url ="https://xmgl.glodon.com/labor-service/lbs/health";
            // String url = "https://xmgl-test.glodon.com/labor-service/workType";
            // String data = "{\"startDate\":\"2017-08-26
            // 12:28:47\",\"endDate\":\"2017-09-25
            // 12:28:47\",\"pageIndex\":0,\"pageSize\":100} ";
            String url = "http://localhost:8080/";
            // 创建客户端实例
            RestServiceClient serviceClient = HmacRestServiceClient.getInstance();
            // 构建认证信息
            HmacRestAuthInfo restAuthInfo = new HmacRestAuthInfo();

            restAuthInfo.setLicPath("c:\\auth(1).lic");

            // 设置认证信息
            serviceClient.authenticate(restAuthInfo);
            LicenseInfo licenseInfo = serviceClient.getLicenseInfo();
            // JSONObject param = new JSONObject();
            // param.put("sourceCode","GZMetroLine12");
            // //分页起始下标，从0开始,第一页为0，第二页为1
            // param.put("pageIndex", 0);
            // //一页请求的数量
            // param.put("pageSize",100);
            // param.put("updateTime","2018-05-12 00:00:00");
            // 发送请求
            RestResponseInfo restResponseInfo = serviceClient.get(url);
//			RestResponseInfo restResponseInfo = serviceClient.post(url, "{\r\n" +
//					"	\"trdTenantId\": \"test\",\r\n" +
//					"	\"source\": \"external.geps\",\r\n" +
//					"	\"adapterType\": \"BASE_ADAPTER\",\r\n" +
//					"	\"data\": {\r\n" +
//					"		\"orgs\": [\r\n" +
//					"			{\r\n" +
//					"				\"id\": \"30011\",\r\n" +
//					"				\"name\": \"项目up30011\",\r\n" +
//					"				\"shortName\": \"项目up30011\",\r\n" +
//					"				\"parentId\": \"1\",\r\n" +
//					"				\"fullId\": \"1/3001\",\r\n" +
//					"				\"type\": \"general-project\",\r\n" +
//					"				\"code\": \"proup30011\",\r\n" +
//					"				\"sort\": 1,\r\n" +
//					"				\"operation\": \"UPDATE\"\r\n" +
//					"			}\r\n" +
//					"		],\r\n" +
//					"		\"type\": \"ORG\",\r\n" +
//					"		\"rootId\": \"1\"\r\n" +
//					"	}\r\n" +
//					"}", ContentType.APPLICATION_JSON.getMimeType());
//			String openUrl = serviceClient.createCloudSsoUrlWithAccount("http://192.168.153.44/labor/bi/used-summary.jsp?orgId=", "test1212", "labor");
            System.out.println(restResponseInfo.getStatus());
            // 处理请求结果
            if (restResponseInfo.isSuccess()) {
                System.out.println(restResponseInfo.getStringContent());
                System.out.println(restResponseInfo.getStringContent());
            } else {
                System.out.println(restResponseInfo.getErrorCode());
                System.out.println(restResponseInfo.getErrorMessage());
            }
        } catch (AuthenticateException e) {
            e.printStackTrace();
            // } catch (InvalidUriException e) {
            // e.printStackTrace();
        } catch (NoAuthenticateException e) {
            e.printStackTrace();
        }
    }

    public void testCookie() {
        try {
            String url = "https://xmgl-test.glodon.com/labor/services/blacklist/query?pageSize=20&blackType=0&ts=1458026836100";

            // 创建客户端实例
            RestServiceClient serviceClient = CookieRestServiceClient.getInstance();

            // 构建认证信息
            CookieRestAuthInfo restAuthInfo = new CookieRestAuthInfo();
            restAuthInfo.setCookies(createAuthCookies());

            // 设置认证信息
            serviceClient.authenticate(restAuthInfo);

            // 发送请求
            RestResponseInfo restResponseInfo = serviceClient.get(url);

            // 处理请求结果
            if (restResponseInfo.isSuccess()) {
                System.out.println(restResponseInfo.getStringContent());
            } else {
                System.out.println(restResponseInfo.getErrorMessage());
            }
        } catch (AuthenticateException e) {
            e.printStackTrace();
        } catch (InvalidUriException e) {
            e.printStackTrace();
        } catch (NoAuthenticateException e) {
            e.printStackTrace();
        }
    }

    private Cookie[] createAuthCookies() {
        Map<String, String> cookieMap = new HashMap<String, String>();
        cookieMap.put("__gtp_clc", "8611753c72ed4fa592d148990d2f5a59");
        cookieMap.put(".JAVA.CLOUD.AUTH", "633326abe161431a8a6b7685f48cb019");
        cookieMap.put("canselectproduct", "true");
        cookieMap.put("tenantManager", "true");
        cookieMap.put("productCode", "labor");
        cookieMap.put("rootURL", "%2Flabor%2F");
        cookieMap.put("lastUser", "1441");
        cookieMap.put("org", "1");
        cookieMap.put("env", "project");
        cookieMap.put("manager", "true");
        cookieMap.put("project", "35");
        cookieMap.put("JSESSIONID", "BE5A6FF0CA2079B6B966528FF77E1D55");
        BasicClientCookie cookie;
        List<Cookie> cookies = new ArrayList<Cookie>();
        for (Map.Entry<String, String> entry : cookieMap.entrySet()) {
            cookie = new BasicClientCookie(entry.getKey(), entry.getValue());
            // path 都统一设置为根目录，这样子目录的请求都可以获取此cookie
            cookie.setPath("/");
            // 必须要设置domain，否则客户端不会将此cookie发送到服务端
            cookie.setDomain("xmgl-test.glodon.com");
            cookies.add(cookie);
        }
        return cookies.toArray(new Cookie[0]);
    }
}
