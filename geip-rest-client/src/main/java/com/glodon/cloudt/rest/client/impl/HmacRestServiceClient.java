package com.glodon.cloudt.rest.client.impl;

import com.alibaba.fastjson.JSONObject;
import com.glodon.cloudt.rest.client.RestServiceClient;
import com.glodon.cloudt.rest.client.RestServiceClientSupport;
import com.glodon.cloudt.rest.client.computer.HardwareFeatureManager;
import com.glodon.cloudt.rest.client.data.HmacRestAuthInfo;
import com.glodon.cloudt.rest.client.data.LicenseInfo;
import com.glodon.cloudt.rest.client.data.RestAuthInfo;
import com.glodon.cloudt.rest.client.data.RestResponseInfo;
import com.glodon.cloudt.rest.client.exception.AuthenticateException;
import com.glodon.cloudt.rest.client.exception.InvalidLicFileException;
import com.glodon.cloudt.rest.client.exception.InvalidUriException;
import com.glodon.cloudt.rest.client.exception.NoAuthenticateException;
import com.glodon.cloudt.rest.client.utils.CryptoUtils;
import com.glodon.cloudt.rest.client.utils.LicenseUtils;
import com.glodon.cloudt.rest.client.utils.StringUtils;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import java.util.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * HMAC 签名方式认证客户端
 * <p/>
 * Created by chenxy on 2016/3/14.
 */
public class HmacRestServiceClient extends RestServiceClientSupport {
	private static final String PARAM_SIGNATURE = "t-cloud-rest-signature";
	private static final String PARAM_COMPUTER_ID = "t-cloud-rest-computer-id";
	private static final String PARAM_EXPIRES = "t-cloud-rest-timestamps";
	private static final String PARAM_ACCESS_ID = "t-cloud-rest-access-id";
	private static final String PARAM_COMPUTER_DESCRIPTION = "t-cloud-rest-computer-description";
	private static final String KEY_MAC_SHA1 = "HmacSHA1";
	private static final String errorMessage = "未找到授权文件";
	private static final HmacRestServiceClient instance;

	private LicenseInfo licenseInfo;
	private String computerId = "";
	private String computerDescription = "";
	private HmacRestAuthInfo hmacRestAuthInfo;

	public HmacRestServiceClient() {
		this.computerId = HardwareFeatureManager.computeComputerId();
		this.computerDescription = HardwareFeatureManager.getComputerDescription();
	}

	@Override
	public LicenseInfo getLicenseInfo() {
		return licenseInfo;
	}

	@Override
	public String getRestRootAddress() {
		return null == licenseInfo ? "" : licenseInfo.getRootAddress();
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
	protected void checkAuthentication() throws NoAuthenticateException {
		if (null == licenseInfo) {
			throw new InvalidLicFileException(errorMessage);
		}
	}

	@Override
	protected void innerInitRequest(DefaultHttpClient httpClient, HttpRequestBase requestBase, String method) {
		// 设置请求method
		long timestamps = (new Date()).getTime();
		requestBase.addHeader(PARAM_COMPUTER_ID, computerId);
		requestBase.addHeader(PARAM_ACCESS_ID, licenseInfo.getAccessId());
		requestBase.addHeader(PARAM_EXPIRES, String.valueOf(timestamps));
		requestBase.addHeader(PARAM_COMPUTER_DESCRIPTION, computerDescription);

		String url = requestBase.getURI().getPath();
		String query = requestBase.getURI().getRawQuery();

		if (!StringUtils.isEmpty(query)) {
			url += "?" + query;
		}
		StringBuilder policyBuilder = new StringBuilder();
		policyBuilder.append(url.toUpperCase(Locale.CHINA) + "\n").append(method.toUpperCase(Locale.CHINA) + "\n")
				.append(timestamps + "\n").append(this.licenseInfo.getAccessId());
		String signature = getSignature(policyBuilder.toString(), this.licenseInfo.getSecret());
		requestBase.addHeader(PARAM_SIGNATURE, signature);
	}

	@Override
	protected Map<String, String> getHeaders() {
		return hmacRestAuthInfo.getHeaders();
	}

	@Override
	public void authenticate(RestAuthInfo authInfo) throws AuthenticateException {
		if (null == authInfo) {
			throw new AuthenticateException("认证参数不能为NULL。");
		}

		if (!(authInfo instanceof HmacRestAuthInfo)) {
			throw new AuthenticateException("认证参数类型非法，应为[HmacRestAuthInfo]类型。");
		}

		hmacRestAuthInfo = (HmacRestAuthInfo) authInfo;

		if (!hmacRestAuthInfo.validate()) {
			throw new AuthenticateException("认证参数非法，请确认属性[licStream]和[licPath]存在一个可用。");
		}

		InputStream stream = hmacRestAuthInfo.getLicStream();

		try {
			if (null != stream) {
				licenseInfo = LicenseUtils.load(stream);
				return;
			}
			licenseInfo = LicenseUtils.load(hmacRestAuthInfo.getLicPath());
		} catch (InvalidLicFileException e) {
			throw new AuthenticateException("认证失败", e);
		}
	}

	@Override
	public String createCloudSsoUrl(String service) throws NoAuthenticateException {
		if (null == licenseInfo) {
			return "";
		}

		long timestamp = System.currentTimeMillis();
		String credence = getCredence();
		String data1 = licenseInfo.getAccessId() + "|" + credence;
		data1 = CryptoUtils.rsaEncode(data1);
		data1 = CryptoUtils.convertBytesToString(data1.getBytes());
		String data = computerId + "|" + timestamp;
		data = CryptoUtils.encrypt(data, licenseInfo.getSecret());
		String url = null;
		try {
			url = String.format("%s/identity/integrate/view?data=%s&data1=%s&service=%s", licenseInfo.getRootAddress(),
					data, data1, URLEncoder.encode(service, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
		}
		return url;
	}

	@Override
	public String createCloudSsoUrlWithAccount(String service, String account, String productCode)
			throws NoAuthenticateException {
		if (null == licenseInfo || StringUtils.isEmpty(account)) {
			return "";
		}
		productCode = (null == productCode ? "" : productCode);
		String credence = "123";
		String data1 = licenseInfo.getAccessId() + "|" + credence;
		data1 = CryptoUtils.rsaEncode(data1);
		data1 = CryptoUtils.convertBytesToString(data1.getBytes());
		String data = computerId + "|" + account;
		data = CryptoUtils.encrypt(data, licenseInfo.getSecret());
		String url = null;
		try {
			if (!StringUtils.isEmpty(service)) {
				service = URLEncoder.encode(service, "UTF-8");
			} else {
				service = "";
			}
			url = String.format("%s/identity/integrate/account-sso?data=%s&data1=%s&service=%s&productCode=%s",
					licenseInfo.getRootAddress(), data, data1, service, productCode);
		} catch (UnsupportedEncodingException e) {
		}
		return url;
	}

	private String getCredence() throws NoAuthenticateException {

		try {
			RestResponseInfo restResponseInfo = get(getRestRootAddress() + "/identity/extend/credence");
			JSONObject value = JSONObject.parseObject((restResponseInfo.getStringContent()));
			if (value.getBoolean("success")) {
				return value.getString("data");
			}
		} catch (InvalidUriException e) {
			return null;
		}
		return null;
	}

	/**
	 * 计算签名
	 *
	 * @param policy
	 *            签名策略
	 * @param accessKey
	 *            授权key
	 * @return 获取签名数据
	 */
	public static String getSignature(String policy, String accessKey) {
		try {
			byte[] data = getUTF8Bytes(policy);
			data = encryptHMAC(data, accessKey);
			String signature = new String(Base64.getEncoder().encode(data), Charset.forName("UTF-8"));
			return signature;
		} catch (NoSuchAlgorithmException e) {
		} catch (InvalidKeyException e) {
		}
		return null;
	}

	/**
	 * 获取UTF8格式的字节数组
	 *
	 * @param content
	 *            待转字符串
	 * @return 字节数组
	 */
	private static byte[] getUTF8Bytes(String content) {
		if (StringUtils.isEmpty(content)) {
			return new byte[0];
		}
		return StringUtils.getBytes(content);
	}

	/**
	 * HMAC 方式加密
	 *
	 * @param data
	 *            待加密数据
	 * @param key
	 *            密钥
	 * @return 密文
	 * @throws NoSuchAlgorithmException
	 *             无相关加密算法异常
	 * @throws InvalidKeyException
	 *             非法KEY异常
	 */
	private static byte[] encryptHMAC(byte[] data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
		SecretKey secretKey = new SecretKeySpec(getUTF8Bytes(key), KEY_MAC_SHA1);
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);
		return mac.doFinal(data);
	}

	/**
	 * 静态初始化
	 */
	static {
		instance = new HmacRestServiceClient();
	}

}
