package com.glodon.cloudt.rest.client.utils;

import com.glodon.cloudt.rest.client.data.LicenseInfo;
import com.glodon.cloudt.rest.client.exception.InvalidLicFileException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.util.Base64;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenxy on 2017/5/5.
 */
public class LicenseUtils {
    private static final String DES_KEY = "C9513C6F9E5349BD865EC9BF";
    private static final String DES_IV = "860F0A9F";

    public static LicenseInfo load(String licensePath)
            throws InvalidLicFileException {
        try {
            InputStream inputStream = new FileInputStream(licensePath);
            return load(inputStream);
        } catch (FileNotFoundException e) {
            throw new InvalidLicFileException("授权文件不存在");
        }
    }

    public static LicenseInfo load(InputStream inputStream)
            throws InvalidLicFileException {
        LicenseInfo licenseInfo = new LicenseInfo();
        try {
            StringBuffer buffer = new StringBuffer();
            byte[] bytes = new byte[inputStream.available()];
            int count = inputStream.read(bytes);
            if (count <= 0) {
                throw new InvalidLicFileException("非法的授权文件");
            }
            buffer.append(StringUtils.getString(bytes));
            String configContent = decode(buffer.toString());
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(StringUtils.getBytes(configContent));
            Document document = builder.parse(stream);

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();
            Node node = (Node) xpath.evaluate("/lic/accessId", document, XPathConstants.NODE);
            if (null == node) {
                throw new InvalidLicFileException("非法的授权文件");
            }
            licenseInfo.setAccessId(node.getTextContent());
            node = (Node) xpath.evaluate("/lic/accessKey", document, XPathConstants.NODE);
            if (null == node) {
                throw new InvalidLicFileException("非法的授权文件");
            }
            licenseInfo.setSecret(node.getTextContent());
            node = (Node) xpath.evaluate("/lic/rootAddress", document, XPathConstants.NODE);
            if (null == node) {
                throw new InvalidLicFileException("非法的授权文件");
            }
            licenseInfo.setRootAddress(node.getTextContent().trim());
            node = (Node) xpath.evaluate("/lic/extra", document, XPathConstants.NODE);
            if (null == node ||
                    null == node.getChildNodes() ||
                    node.getChildNodes().getLength() == 0) {
                return licenseInfo;
            }
            NodeList children = node.getChildNodes();
            Map<String, String> properties = new HashMap<String, String>();
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                properties.put(child.getNodeName(), child.getTextContent());
            }
            licenseInfo.setProperties(properties);
        } catch (ParserConfigurationException e) {
            throw new InvalidLicFileException("非法的授权文件," + e.getMessage());
        } catch (SAXException e) {
            throw new InvalidLicFileException("非法的授权文件," + e.getMessage());
        } catch (IOException e) {
            throw new InvalidLicFileException("非法的授权文件," + e.getMessage());
        } catch (XPathExpressionException e) {
            throw new InvalidLicFileException("非法的授权文件," + e.getMessage());
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        }
        return licenseInfo;
    }

    /**
     * 解密授权文件
     *
     * @param content 待解密密文
     * @return 明文
     */
    private static String decode(String content) {
        try {
            DESedeKeySpec spec = new DESedeKeySpec(StringUtils.getBytes(DES_KEY));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
            Key desKey = keyFactory.generateSecret(spec);
            Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(StringUtils.getBytes(DES_IV));
            cipher.init(Cipher.DECRYPT_MODE, desKey, ips);
            byte[] data = Base64.getDecoder().decode(content.getBytes(Charset.forName("UTF-8")));
            byte[] bOut = cipher.doFinal(data);
            return StringUtils.getString(bOut);
        } catch (InvalidKeyException e) {
        } catch (NoSuchAlgorithmException e) {
        } catch (InvalidKeySpecException e) {
        } catch (NoSuchPaddingException e) {
        } catch (InvalidAlgorithmParameterException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (BadPaddingException e) {
        }
        return null;
    }

}
