package com.jd.pop.base.xml;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;

/**
 * xml验证 xsd(XML Schema Definition)
 * 来源:
 * Java调用Schema校验xml文件
 * https://blog.csdn.net/bujibo/article/details/70174656
 *
 * @author qiw-a
 * @date 2020/1/14
 */
public class XmlValidateUtil {
    private static final Log logger = LogFactory.getLog(XmlValidateUtil.class);
    private static final String SCHEMALANG = "http://www.w3.org/2001/XMLSchema";


    public static void main(String[] args) throws IOException {


        long startTime = System.currentTimeMillis();
        //schema校验xml文件
        String xsdPath = "E:\\dev\\审计\\文件对比样例文件\\xsd\\A安庆-房屋与市政-工程类-投标清单文件.xsd";

        //定义一个file对象，用来初始化FileReader
        File file1 = new File("E:\\dev\\审计\\文件对比样例文件\\xsd\\（投标）安庆投标测试项目.xml");
        //定义一个fileReader对象，用来初始化BufferedReader
        FileReader reader = new FileReader(file1);
        //new一个BufferedReader对象，将文件内容读取到缓存
        BufferedReader bReader = new BufferedReader(reader);
        //定义一个字符串缓存，将字符串存放缓存中
        StringBuilder sb = new StringBuilder();
        String s = "";
        //逐行读取文件内容，不读取换行符和末尾的空格
        while ((s = bReader.readLine()) != null) {
            //将读取的字符串添加换行符后累加存放在缓存中
            sb.append(s + "\n");
        }
        bReader.close();
        String str = sb.toString();
        System.out.println(str);
        XmlValidateUtil.xmlStringValidate(str, xsdPath);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    /**
     * Schema校验xml文件
     *
     * @param xmlStr  xml字符串
     * @param xsdPath xsd文件路径
     * @return xml文件是否符合xsd定义的规则
     */
    public static boolean xmlStringValidate(String xmlStr, String xsdPath) {
        boolean flag = false;
        try {
            SchemaFactory factory = SchemaFactory.newInstance(SCHEMALANG);
            File schemaLocation = new File(xsdPath);
            Schema schema = factory.newSchema(schemaLocation);
            //以上内容测试可用
            Validator validator = schema.newValidator();
            InputStream is = new ByteArrayInputStream(xmlStr.getBytes());
            //需要设置编码,否则会出字节解析错误
            InputStreamReader in = new InputStreamReader(is, "UTF-8");
            Source source = new StreamSource(in);
            try {
                validator.validate(source);
                flag = true;
            } catch (SAXException ex) {
                logger.info("Schema校验xml文件 异常" + ex.getMessage());
            }
        } catch (Exception e) {
            logger.info("Schema校验xml文件 异常" + e.getMessage());
        }
        return flag;
    }
}