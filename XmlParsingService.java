package com.example.xdsapp.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class XmlParsingService {

    public Map<String, String> parseXmlFile(String filePath) {
        Map<String, String> keyValueMap = new HashMap<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filePath));
            document.getDocumentElement().normalize();

            // Try to get elements from "xxx" path
            NodeList nodeList = document.getElementsByTagName("xxx");

            // If "xxx" path is not found, fallback to "x2x" path
            if (nodeList.getLength() == 0) {
                nodeList = document.getElementsByTagName("x2x");
            }

            // Process nodes and store key-value pairs
            for (int i = 0; i < nodeList.getLength(); i++) {
                NodeList entries = nodeList.item(i).getChildNodes();
                for (int j = 0; j < entries.getLength(); j++) {
                    Node entryNode = entries.item(j);
                    if (entryNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element entryElement = (Element) entryNode;
                        String key = entryElement.getElementsByTagName("key").item(0).getTextContent();
                        String value = entryElement.getElementsByTagName("value").item(0).getTextContent();
                        keyValueMap.put(key, value);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return keyValueMap;
    }
}
