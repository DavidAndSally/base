package com.glodon.cloudt.rest.client;

import com.glodon.cloudt.rest.client.data.LicenseInfo;
import com.glodon.cloudt.rest.client.data.RestAuthInfo;
import com.glodon.cloudt.rest.client.data.RestResponseInfo;
import com.glodon.cloudt.rest.client.exception.AuthenticateException;
import com.glodon.cloudt.rest.client.exception.InvalidUriException;
import com.glodon.cloudt.rest.client.exception.NoAuthenticateException;

/**
 * REST客户端接口
 * <p/>
 * Created by chenxy on 2016/3/14.
 */
public interface RestServiceClient {
	/**
	 * REST 客户端认证 只需要初始化时认证一次
	 *
	 * @param authInfo
	 *            认证信息
	 * @throws AuthenticateException
	 *             认证异常
	 */
	void authenticate(RestAuthInfo authInfo) throws AuthenticateException;

	/**
	 * 获取云端根地址
	 *
	 * @return 云端根地址
	 */
	String getRestRootAddress();

	/**
	 * 发送POST请求
	 *
	 * @param restUrl
	 *            请求url
	 * @param data
	 *            请求数据
	 * @param contentType
	 *            内容类型
	 * @return 响应结果
	 * @throws InvalidUriException
	 *             非法url异常
	 * @throws NoAuthenticateException
	 *             未认证异常
	 */
	RestResponseInfo post(String restUrl, String data, String contentType)
			throws InvalidUriException, NoAuthenticateException;

	/**
	 * 发送POST请求
	 *
	 * @param restUrl
	 *            请求url
	 * @param data
	 *            请求数据
	 * @return 响应结果
	 * @throws InvalidUriException
	 *             非法url异常
	 * @throws NoAuthenticateException
	 *             未认证异常
	 */
	RestResponseInfo post(String restUrl, String data) throws InvalidUriException, NoAuthenticateException;

	/**
	 * 发送PUT请求
	 *
	 * @param restUrl
	 *            请求url
	 * @param data
	 *            请求数据
	 * @param contentType
	 *            内容类型
	 * @return 响应结果
	 * @throws InvalidUriException
	 *             非法url异常
	 * @throws NoAuthenticateException
	 *             未认证异常
	 */
	RestResponseInfo put(String restUrl, String data, String contentType)
			throws InvalidUriException, NoAuthenticateException;

	/**
	 * 发送PUT请求
	 *
	 * @param restUrl
	 *            请求url
	 * @param data
	 *            请求数据
	 * @return 响应结果
	 * @throws InvalidUriException
	 *             非法url异常
	 * @throws NoAuthenticateException
	 *             未认证异常
	 */
	RestResponseInfo put(String restUrl, String data) throws InvalidUriException, NoAuthenticateException;

	/**
	 * 发送DELETE请求
	 *
	 * @param restUrl
	 *            请求url
	 * @return 响应结果
	 * @throws InvalidUriException
	 *             非法url异常
	 * @throws NoAuthenticateException
	 *             未认证异常
	 */
	RestResponseInfo delete(String restUrl) throws InvalidUriException, NoAuthenticateException;

	/**
	 * 发送GET请求
	 *
	 * @param restUrl
	 *            请求url
	 * @return 响应结果
	 * @throws InvalidUriException
	 *             非法url异常
	 * @throws NoAuthenticateException
	 *             未认证异常
	 */
	RestResponseInfo get(String restUrl) throws InvalidUriException, NoAuthenticateException;

	/**
	 * 创建单点访问云端页面url
	 *
	 * @param url
	 *            待访问URL
	 * @return 单点URL
	 * @throws NoAuthenticateException
	 */
	String createCloudSsoUrl(String url) throws NoAuthenticateException;

	/**
	 * 创建单点访问云端页面url
	 *
	 * @param service
	 *            待访问URL
	 * @param account
	 *            云端帐号
	 * @param productCode
	 *            云端产品编码
	 * @return 单点URL
	 * @throws NoAuthenticateException
	 */
	String createCloudSsoUrlWithAccount(String service, String account, String productCode)
			throws NoAuthenticateException;

	LicenseInfo getLicenseInfo();
}
