package com.jd.pop.base.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * 解析投标文件前探
 *
 * @author qiw-a
 * @date 2020/1/14
 */
public class Dom4jTest2 {

    public static void main(String[] args) throws DocumentException {
        // 1. 创建一个 saxReader 对象
        SAXReader saxReader = new SAXReader();
        // 2. 将数据读取到 document 对象中
        Document document = saxReader.read(new File("E:\\dev\\审计\\曹老师提供文件\\南山区西丽街道办事住宅处小区排水管网改造工程二期(投标文件) - 副本.SWT.xml"));
        // 3. 使用 document 对象调用 getRootElement 方法获取根标签, 返回 Element 接口实现类对象
        Element rootElement = document.getRootElement();
        System.out.println(rootElement.attributeValue("Name"));
        List<Element> elements = rootElement.elements("ProjectAddInfo");

        System.out.println(elements.get(0).element("ProjectAddInfoItem").attributeValue("Name"));
        Element singleProject = rootElement.element("SingleProject");
        Element unitProject = singleProject.element("UnitProject");
        Element summary = unitProject.element("Summary");
        List<Element> summaryItem = summary.elements("SummaryItem");
        String costRate = summaryItem.get(0).attributeValue("CostRate");
        System.out.println("分部分享工程费为:" + costRate);

    }

}
