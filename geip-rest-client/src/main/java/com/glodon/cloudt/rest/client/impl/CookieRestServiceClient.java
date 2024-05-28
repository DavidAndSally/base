package com.glodon.cloudt.rest.client.impl;

import java.util.Map;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

import com.glodon.cloudt.rest.client.RestServiceClient;
import com.glodon.cloudt.rest.client.RestServiceClientSupport;
import com.glodon.cloudt.rest.client.data.CookieRestAuthInfo;
import com.glodon.cloudt.rest.client.data.LicenseInfo;
import com.glodon.cloudt.rest.client.data.RestAuthInfo;
import com.glodon.cloudt.rest.client.exception.AuthenticateException;
import com.glodon.cloudt.rest.client.exception.NoAuthenticateException;

/**
 * COOKIE 方式认证客户端
 * <p/>
 * Created by chenxy on 2016/3/14.
 */
public class CookieRestServiceClient extends RestServiceClientSupport {
	private static final CookieRestServiceClient instance;

	private CookieStore cookieStore;
	private boolean authenticated = false;
	private CookieRestAuthInfo cookieRestAuthInfo;

	private CookieRestServiceClient() {
		cookieStore = new BasicCookieStore();
	}

	/**
	 * 获取实例对象
	 *
	 * @return 实例对象
	 */
	public static RestServiceClient getInstance() {
		return instance;
	}

	@Override
	public String getRestRootAddress() {
		return null == cookieRestAuthInfo ? "" : cookieRestAuthInfo.getRestSiteDomain();
	}

	@Override
	protected void checkAuthentication() throws NoAuthenticateException {
		if (!authenticated) {
			throw new NoAuthenticateException("当前客户端还未认证，无法发送请求。");
		}
	}

	@Override
	protected void innerInitRequest(DefaultHttpClient httpClient, HttpRequestBase requestBase, String method) {
		httpClient.setCookieStore(this.cookieStore);
	}

	@Override
	protected Map<String, String> getHeaders() {
		return cookieRestAuthInfo.getHeaders();
	}

	@Override
	public void authenticate(RestAuthInfo authInfo) throws AuthenticateException {
		this.authenticated = false;
		if (null == authInfo) {
			throw new AuthenticateException("认证参数不能为NULL。");
		}

		if (!(authInfo instanceof CookieRestAuthInfo)) {
			throw new AuthenticateException("认证参数类型非法，应为[CookieRestAuthInfo]类型。");
		}
		cookieRestAuthInfo = (CookieRestAuthInfo) authInfo;

		cookieRestAuthInfo.validate();

		this.authenticated = true;
		Cookie[] cookies = cookieRestAuthInfo.getCookies();
		this.cookieStore.clear();

		for (Cookie cookie : cookies) {
			this.cookieStore.addCookie(cookie);
		}
	}

	@Override
	public String createCloudSsoUrl(String url) throws NoAuthenticateException {
		return null;
	}

	@Override
	public String createCloudSsoUrlWithAccount(String service, String account, String productCode)
			throws NoAuthenticateException {
		return null;
	}

	static {
		instance = new CookieRestServiceClient();
	}

	@Override
	public LicenseInfo getLicenseInfo() {
		return null;
	}
}
