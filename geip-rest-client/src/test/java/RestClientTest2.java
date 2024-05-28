import com.glodon.cloudt.rest.client.RestServiceClient;
import com.glodon.cloudt.rest.client.data.HmacRestAuthInfo;
import com.glodon.cloudt.rest.client.data.RestResponseInfo;
import com.glodon.cloudt.rest.client.exception.AuthenticateException;
import com.glodon.cloudt.rest.client.exception.InvalidUriException;
import com.glodon.cloudt.rest.client.exception.NoAuthenticateException;
import com.glodon.cloudt.rest.client.impl.HmacRestServiceClient;

/**
 * Created by chenxy on 2015-11-06.
 */
public class RestClientTest2 {

	public static void main(String[] args) throws Throwable {
		new RestClientTest2().testLic();
	}

	public void testLic() throws InvalidUriException {
		try {
			String url = "https://xmgl-test.glodon.com/sync-api/v3/service/proxy/TrdToCloud?accessId=1da9259a0c0811ea961b7cd30a518102&orgId=1025";
			// 创建客户端实例
			RestServiceClient serviceClient = HmacRestServiceClient.getInstance();
			// 构建认证信息
			HmacRestAuthInfo restAuthInfo = new HmacRestAuthInfo();
			restAuthInfo.setLicPath("c:\\auth (3).lic");
			// 设置认证信息
			serviceClient.authenticate(restAuthInfo);
			RestResponseInfo restResponseInfo = serviceClient.get(url);
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
