package com.glodon.cloudt.rest.client;

import com.glodon.cloudt.rest.client.data.LicenseInfo;
import com.glodon.cloudt.rest.client.data.RestResponseInfo;
import com.glodon.cloudt.rest.client.exception.InvalidUriException;
import com.glodon.cloudt.rest.client.exception.NoAuthenticateException;
import com.glodon.cloudt.rest.client.utils.CryptoUtils;
import com.glodon.cloudt.rest.client.utils.StringUtils;
import com.sun.jndi.toolkit.url.Uri;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * Created by chenxy on 2016/3/14.
 */
public abstract class RestServiceClientSupport implements RestServiceClient {
    protected static final Charset DEFAULT_ENCODING = Charset.forName("UTF-8");
    protected static final String DEFAULT_CONTENT_TYPE = "application/json; charset=UTF-8";
    protected static final String HTTP_METHOD_POST = "POST";
    protected static final String HTTP_METHOD_PUT = "PUT";
    protected static final String HTTP_METHOD_GET = "GET";
    protected static final String HTTP_METHOD_DELETE = "DELETE";
    protected static final String PARAM_WEB_API = "cloudt-x-webapi";

    protected abstract void checkAuthentication()
            throws NoAuthenticateException;

    protected abstract void innerInitRequest(DefaultHttpClient httpClient, HttpRequestBase requestBase, String method);

    protected abstract Map<String, String> getHeaders();

    /**
     * 发送post请求
     *
     * @param restUrl     rest api 地址
     * @param data        请求数据
     * @param contentType 内容类型
     * @return 请求结果
     */
    @Override
    public RestResponseInfo post(String restUrl, String data, String contentType)
            throws InvalidUriException, NoAuthenticateException {
        checkAuthentication();
        DefaultHttpClient httpClient = validateUrl(restUrl);
        HttpPost httpPost = new HttpPost(restUrl);
        initRestRequest(httpClient, httpPost, HTTP_METHOD_POST);
        writeData(httpPost, data, contentType);
        return sendRequest(httpClient, httpPost);
    }

    /**
     * 发送post请求
     *
     * @param restUrl rest api 地址
     * @param data    请求数据
     * @return 请求结果
     */
    @Override
    public RestResponseInfo post(String restUrl, String data)
            throws InvalidUriException, NoAuthenticateException {
        return post(restUrl, data, DEFAULT_CONTENT_TYPE);
    }

    /**
     * 发送put请求
     *
     * @param restUrl     rest api 地址
     * @param data        请求数据
     * @param contentType 内容类型
     * @return 请求结果
     */
    @Override
    public RestResponseInfo put(String restUrl, String data, String contentType)
            throws InvalidUriException, NoAuthenticateException {
        checkAuthentication();
        DefaultHttpClient httpClient = validateUrl(restUrl);
        HttpPut httpPut = new HttpPut(restUrl);
        initRestRequest(httpClient, httpPut, HTTP_METHOD_PUT);
        writeData(httpPut, data, contentType);
        return sendRequest(httpClient, httpPut);
    }

    /**
     * 发送put请求
     *
     * @param restUrl rest api 地址
     * @param data    请求数据
     * @return 请求结果
     */
    @Override
    public RestResponseInfo put(String restUrl, String data)
            throws InvalidUriException, NoAuthenticateException {
        return put(restUrl, data, DEFAULT_CONTENT_TYPE);
    }

    /**
     * 发送delete请求
     *
     * @param restUrl rest api 地址
     * @return 请求结果
     */
    @Override
    public RestResponseInfo delete(String restUrl)
            throws InvalidUriException, NoAuthenticateException {
        checkAuthentication();
        DefaultHttpClient httpClient = validateUrl(restUrl);
        HttpDelete httpDelete = new HttpDelete(restUrl);
        initRestRequest(httpClient, httpDelete, HTTP_METHOD_DELETE);
        return sendRequest(httpClient, httpDelete);
    }

    /**
     * 发送get请求
     *
     * @param restUrl rest api 地址
     * @return 请求结果
     */
    @Override
    public RestResponseInfo get(String restUrl)
            throws InvalidUriException, NoAuthenticateException {
        checkAuthentication();
        DefaultHttpClient httpClient = validateUrl(restUrl);
        HttpGet httpGet = new HttpGet(restUrl);
        initRestRequest(httpClient, httpGet, HTTP_METHOD_GET);
        return sendRequest(httpClient, httpGet);
    }

    /**
     * 初始化请求
     *
     * @param httpClient  HTTP 请求客户端
     * @param requestBase 当前请求
     * @param method      请求方式
     */
    private void initRestRequest(DefaultHttpClient httpClient, HttpRequestBase requestBase, String method) {
        requestBase.setHeader("Accept-Encoding", "gzip, deflate");
        requestBase.setHeader("Accept-Language", "zh-CN");
        requestBase.setHeader("Accept", "application/json, application/xml, text/html, text/*, image/*, */*");
        requestBase.addHeader(PARAM_WEB_API, "1.0.0");
        requestBase.addHeader("Connection", "keep-alive");
        innerInitRequest(httpClient, requestBase, method);
        Map<String, String> headers = getHeaders();
        if (null == headers || headers.size() == 0) {
            return;
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            requestBase.addHeader(entry.getKey(), entry.getValue());
        }
    }

    private DefaultHttpClient validateUrl(String url)
            throws InvalidUriException {
        DefaultHttpClient tempClient = null;
        String errorMessage = null;
        try {
            Uri uri = new Uri(url);
            if (uri.getScheme().equalsIgnoreCase("http")) {
                tempClient = new DefaultHttpClient();
            } else if (uri.getScheme().equalsIgnoreCase("https")) {
                tempClient = new SSLClient();
            }
        } catch (MalformedURLException e) {
            errorMessage = "非法的URI," + e.getMessage();
        } catch (NoSuchAlgorithmException e) {
            errorMessage = "无法创建HTTPS请求，" + e.getMessage();
        } catch (KeyManagementException e) {
            errorMessage = "无法创建HTTPS请求，" + e.getMessage();
        }
        if (null == tempClient) {
            throw new InvalidUriException(errorMessage);
        }
        return tempClient;
    }

    /**
     * 发送restful请求
     *
     * @param httpClient http客户端实例
     * @param request    当前请求
     * @return 请求结果
     */
    private RestResponseInfo sendRequest(DefaultHttpClient httpClient, HttpRequestBase request) {
        RestResponseInfo responseInfo = new RestResponseInfo();

        try {
            HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            responseInfo.setStatus(statusCode);
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                responseInfo.setSuccess();

                if (null != entity) {
                    InputStream stream = entity.getContent();
                    Header header = response.getFirstHeader("Content-Encoding");
                    boolean gzip = null != header && "gzip".equalsIgnoreCase(header.getValue());
                    responseInfo.setResponseStream(stream, gzip);
                }
            } else {
                String message = "";
                HttpEntity entity = response.getEntity();
                if (null != entity) {
                    Header header = response.getFirstHeader("Content-Encoding");
                    boolean gzip = null != header && "gzip".equalsIgnoreCase(header.getValue());
                    responseInfo.setResponseStream(entity.getContent(), gzip);
                    message = getContent(responseInfo.getResponseStream());
                }
                if (StringUtils.isEmpty(message)) {
                    switch (statusCode) {
                        case 401:
                            message = "(401) 未经授权。";
                            break;
                        default:
                            message = "请求失败：" + statusCode;
                            break;
                    }
                }
                responseInfo.setErrorXmlContent(message);
            }
            request.abort();
            httpClient.close();
        } catch (IOException e) {
            responseInfo.setError(e.getMessage());
        }
        return responseInfo;
    }

    /**
     * 获取响应信息
     *
     * @param inputStream 输入流
     * @return 流的字符串
     */
    private String getContent(InputStream inputStream) {
        if (null == inputStream) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream, DEFAULT_ENCODING));
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }
        } catch (IOException e) {
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
        return builder.toString();
    }

    /**
     * 向request中写入数据
     *
     * @param request     当前请求
     * @param data        发送数据
     * @param contentType 内容类型
     */
    private void writeData(HttpEntityEnclosingRequestBase request,
                           String data, String contentType) {
        if (null == data) {
            data = "";
        }
        if (StringUtils.isEmpty(contentType)) {
            contentType = "application/json";
        }
        StringEntity entity = new StringEntity(data, DEFAULT_ENCODING);
        entity.setContentEncoding("UTF-8");
        entity.setContentType(contentType);
        request.setEntity(entity);
    }
}
