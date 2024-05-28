package com.jd.pop.base.gof.factory.abstrac;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

class ReadXML2 {
    public static String getObject(Integer type) {
        try {
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("base-dev/src/main/resources/config3.xml"));
            NodeList nl = doc.getElementsByTagName("className");
            Node classNode = type ==1 ? nl.item(0).getFirstChild(): nl.item(1).getFirstChild();
            String nodeValue = classNode.getNodeValue();
            return nodeValue;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}