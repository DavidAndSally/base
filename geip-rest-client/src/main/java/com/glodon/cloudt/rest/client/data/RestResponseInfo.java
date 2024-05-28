package com.glodon.cloudt.rest.client.data;

import com.glodon.cloudt.rest.client.utils.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;

/**
 * rest api 返回结果
 * <p/>
 * Created by chenxy on 2015-11-04.
 */
public class RestResponseInfo {
    /**
     * HTTP 状态码
     */
    private int status;
    /**
     * 错误信息
     */
    private String errorMessage;
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 返回相应信息流
     */
    private InputStream responseStream;
    /**
     * 响应信息（字符串形式）
     */
    private String content = null;
    /**
     * 错误码
     */
    private String errorCode;

    private boolean gzip = false;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public InputStream getResponseStream() {
        return responseStream;
    }

    public void setResponseStream(InputStream responseStream, boolean gzip) {
        if (null == responseStream) {
            return;
        }

        try {

            byte[] buffer = new byte[1024];
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int length = 0;
            while ((length = responseStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            this.gzip = gzip;
            if (gzip) {
                this.responseStream = new GZIPInputStream(new ByteArrayInputStream(outputStream.toByteArray()));
            } else {
                this.responseStream = new ByteArrayInputStream(outputStream.toByteArray());
            }
        } catch (IOException e) {
        }
    }

    public String getStringContent() {
        if (null == this.responseStream) {
            return null;
        }
        if (null != content) {
            return content;
        }
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;
        try {
            if (!gzip) {
                this.responseStream.reset();
            }
            reader = new BufferedReader(new InputStreamReader(this.responseStream, Charset.forName("UTF-8")));
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != reader) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
        content = builder.toString();
        return content;
    }

    /**
     * 设置xml错误信息
     *
     * @param xmlContent xml内容
     */
    public void setErrorXmlContent(String xmlContent) {
        this.success = false;
        if (null == xmlContent || xmlContent.length() == 0) {
            return;
        }
        this.errorMessage = xmlContent;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream inputStream = new ByteArrayInputStream(StringUtils.getBytes(xmlContent));
            Document document = builder.parse(inputStream);
            NodeList nodes = document.getElementsByTagName("errorCode");
            if (null != nodes && nodes.getLength() > 0) {
                this.errorCode = nodes.item(0).getTextContent();
            }
            nodes = document.getElementsByTagName("errorMessage");
            if (null != nodes && nodes.getLength() > 0) {
                this.errorMessage = nodes.item(0).getTextContent();
            }
        } catch (ParserConfigurationException e) {
        } catch (SAXException e) {
        } catch (IOException e) {
        }
    }

    public void setError(String errorMessage) {
        this.errorMessage = errorMessage;
        this.success = false;
    }

    public void setSuccess() {
        this.errorMessage = "";
        this.success = true;
    }
}
