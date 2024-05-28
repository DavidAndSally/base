import com.glodon.cloudt.rest.client.RestServiceClient;
import com.glodon.cloudt.rest.client.data.HmacRestAuthInfo;
import com.glodon.cloudt.rest.client.data.LicenseInfo;
import com.glodon.cloudt.rest.client.data.RestResponseInfo;
import com.glodon.cloudt.rest.client.exception.AuthenticateException;
import com.glodon.cloudt.rest.client.exception.InvalidUriException;
import com.glodon.cloudt.rest.client.exception.NoAuthenticateException;
import com.glodon.cloudt.rest.client.impl.HmacRestServiceClient;
import org.apache.http.entity.ContentType;

/**
 * Created by chenxy on 2015-11-06.
 */
public class RestClientTest3 {

    public static void main(String[] args) throws Throwable {
            new RestClientTest3().testLic1();
    }
) throws InvalidUriException {
        public void testLic(
        try {
            String url = "http://127.0.0.1:9539/sync-api/v3/service/public";
            // 创建客户端实例
            RestServiceClient serviceClient = HmacRestServiceClient.getInstance();
            // 构建认证信息
            HmacRestAuthInfo restAuthInfo = new HmacRestAuthInfo();

            restAuthInfo.setLicPath("c:\\auth (14).lic");

            // 设置认证信息
            serviceClient.authenticate(restAuthInfo);
            LicenseInfo licenseInfo = serviceClient.getLicenseInfo();
            // 发送请求
			RestResponseInfo restResponseInfo = serviceClient.post(url,
                    "{" +
					"	\"trdTenantId\": \"test\"," +
					"	\"source\": \"test\"," +
					"	\"adapterType\": \"BASE_ADAPTER\"," +
					"	\"data\": {" +
					"		\"orgs\": [" +
					"			{" +
					"				\"id\": \"1\"," +
					"				\"name\": \"组织1\"," +
					"				\"shortName\": \"组织1\"," +
					"				\"parentId\": \"0\"," +
					"				\"fullId\": \"1\"," +
					"				\"type\": \"company\"," +
					"				\"code\": \"company\"," +
					"				\"sort\": 1," +
					"				\"operation\": \"UPDATE\"" +
					"			}" +
					"		]," +
					"		\"type\": \"ORG\"," +
					"		\"rootId\": \"1\"" +
					"	}" +
					"}"
                    , ContentType.APPLICATION_JSON.getMimeType());
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


    //获取平台颁发的access_id
    public void testLic1() throws InvalidUriException {
        try {
            String url = "https://xmgl.glodon.com/sync-api/v4/service/proxy/register";
            // 创建客户端实例
            RestServiceClient serviceClient = HmacRestServiceClient.getInstance();
            // 构建认证信息
            HmacRestAuthInfo restAuthInfo = new HmacRestAuthInfo();
            restAuthInfo.setLicPath("c:/auth-10.lic");
//            restAuthInfo.setLicStream();
            // 设置认证信息
            serviceClient.authenticate(restAuthInfo);
            LicenseInfo licenseInfo = serviceClient.getLicenseInfo();
            // 发送请求
            RestResponseInfo restResponseInfo = serviceClient.post(url, "{" +
                    "\"trdTenantId\": 6526030152689791522," +
                    "\"source\": \"external.hg\"," +
                    "\"siteDomain\": \"\""+
                    "}", ContentType.APPLICATION_JSON.getMimeType());
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

    public void testLic2() throws InvalidUriException {
        try {
            String url = "http://geip-fat.glodon.com/sync-api/v4/service/proxy/roles";
            // 创建客户端实例
            RestServiceClient serviceClient = HmacRestServiceClient.getInstance();
            // 构建认证信息
            HmacRestAuthInfo restAuthInfo = new HmacRestAuthInfo();

            restAuthInfo.setLicPath("c:/auth.lic");

            // 设置认证信息
            serviceClient.authenticate(restAuthInfo);
            LicenseInfo licenseInfo = serviceClient.getLicenseInfo();
            // 发送请求
            RestResponseInfo restResponseInfo = serviceClient.get(url);
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
}
