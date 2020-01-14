package com.jd.pop.base.xml;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * dom4j解析xml 案例二
 * https://www.jianshu.com/p/806bb1bdc06f
 *
 * @author qiw-a
 * @date 2020/1/14
 */
public class Dom4J_02 {
    public static void main(String[] args) throws Exception {
        // 1. 创建一个 saxReader 对象
        SAXReader saxReader = new SAXReader();
        // 2. 将数据读取到 document 对象中
        Document document = saxReader.read(new File("E:\\dev" +
                "\\审计\\文件对比样例文件\\servlet.xml"));
        // 3. 使用 document 对象调用 getRootElement 方法获取根标签, 返回 Element 接口实现类对象
        Element rootElement = document.getRootElement();
        // 4. 使用 rootElement 根标签对象调用 elements 方法, 传入 servlet, 获取servlet标签对象
        List<Element> servletElements = rootElement.elements("servlet");
        // 5. 遍历, 并获取该标签下的子标签数据内容, 使用父标签对象调用elementText方法, 传入子标签名称获取数据
        for (Element servlet : servletElements) {
            String name = servlet.elementText("servlet-name");
            String cls = servlet.elementText("servlet-class");
            System.out.println(name + " : " + cls);
        }
        // 使用 rootElement 根标签对象调用 elements 方法, 传入 servlet-mapping, 获取servlet-mapping标签对象
        List<Element> mappingElements = rootElement.elements("servlet-mapping");
        for (Element mapping : mappingElements) {
            String name = mapping.elementText("servlet-name");
            String url = mapping.elementText("url-pattern");
            System.out.println(name + " = " + url);
        }
    }
}