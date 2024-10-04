package application;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class XMLEditor {
    public String XMLReader() throws Exception {
        File xmlFile = new File("C:/Users/edwar/Downloads/XMLWriter/output.xml");
        String str = new String();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        NodeList nodeList = document.getElementsByTagName("usernames");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            str = new String(str + ("Element Content: " + node.getTextContent()));
        }
        return(str);
    }
    
    public void XMLWriter(ArrayList<String> authenticationPairs) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Create a new Document
        Document document = builder.newDocument();

        // Create root element
        Element usernames = document.createElement("usernames");
        document.appendChild(usernames);

        for(int i = 0; i < authenticationPairs.size() - 1; i += 2) {
            Element usr = document.createElement(new String("usr" + i));
            Element pw = document.createElement(new String("pw" + i));
            usr.appendChild(document.createTextNode(authenticationPairs.get(i)));
            pw.appendChild(document.createTextNode(authenticationPairs.get(i + 1)));
            usernames.appendChild(usr);
            usernames.appendChild(pw);
        }

        // Write to XML file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);

        // Specify your local file path
        StreamResult result = new StreamResult("C:/Users/edwar/Downloads/XMLWriter/output.xml");
        transformer.transform(source, result);

        System.out.println("XML file created successfully!");
    }
}
