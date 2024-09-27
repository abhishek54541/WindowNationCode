package com.qa.automation.tests;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DynamicXMLGenerator {
    
    public static void main(String[] args) {
        int scenarioCount = 3;  // Replace with the actual number of scenarios
        
        try {
            generateXML("testng.xml", scenarioCount);
            System.out.println("XML file generated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void generateXML(String filename, int scenarioCount) 
            throws ParserConfigurationException, IOException {
        // Create DocumentBuilderFactory
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        
        // Create Document
        Document doc = docBuilder.newDocument();
        
        // Create <suite> element
        Element suiteElement = doc.createElement("suite");
        suiteElement.setAttribute("name", "My Test Suite");
        doc.appendChild(suiteElement);
        
        // Create <test> element
        Element testElement = doc.createElement("test");
        testElement.setAttribute("name", "Cucumber Tests");
        suiteElement.appendChild(testElement);
        
        // Create <classes> element
        Element classesElement = doc.createElement("classes");
        testElement.appendChild(classesElement);
        
        // Add <class> elements based on scenario count
        for (int i = 1; i <= scenarioCount; i++) {
            String className = "Runner_test_scenario" + String.format("%03d", i) + "_run001_IT";
            Element classElement = doc.createElement("class");
            classElement.setAttribute("name", className);
            classesElement.appendChild(classElement);
        }
        
        // Write the content into XML file
        FileOutputStream fos = new FileOutputStream(new File(filename));
        writeToStream(doc, fos);
        fos.close();
    }
    
    private static void writeToStream(Node node, FileOutputStream fos) throws IOException {
        javax.xml.transform.TransformerFactory transformerFactory = javax.xml.transform.TransformerFactory.newInstance();
        try {
            javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
            javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(node);
            javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(fos);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
