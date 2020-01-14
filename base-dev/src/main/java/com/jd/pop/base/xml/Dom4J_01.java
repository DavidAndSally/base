package com.jd.pop.base.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * dom4j解析xml 案例一
 * https://www.jianshu.com/p/806bb1bdc06f
 *
 * @author qiw-a
 * @date 2020/1/14
 */
public class Dom4J_01 {
    public static void main(String[] args) throws DocumentException {
        //1.将xml文件加载到内存中
        SAXReader saxReader = new SAXReader();
        //document 加载执行xml文档获取Document对象
        Document document = saxReader.read(new File("E:\\dev\\审计\\文件对比样例文件\\books.xml"));
        //2.获取根元素的对象 -- books getRootElement 获取根元素
        Element rootElement = document.getRootElement();
        //每个元素的对象 都是Element对象
        List<Element> elements = rootElement.elements();
        //3.使用根元素的对象  对其他元素操作操作
        //3.1获取所有的子元素对象 book
        for (Element book : elements) {
            //在循环内拿到了 每个 book  元素对象
            //3.2获取book的属性值
            String author = book.attributeValue("author");
            //3.3获取book的子元素  name price
            String name = book.element("name").getText();
            String price = book.element("price").getText();
            System.out.println(name + " " + author + " " + price);
        }
    }
}

//结果：
//斗破苍穹 天蚕土豆 86
//缥缈之旅 萧潜 92
//诛仙 萧鼎 98
//鬼吹灯 天下霸唱 124
//神墓 辰东 128