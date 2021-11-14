package domO3AE4K1027;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class DomWriteO3AE4K_1 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		try {
			File xmlFile = new File("users1LFM4UL.xml");
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(xmlFile);
			Element documentElement = document.getDocumentElement();
			
			Element textNode = document.createElement("firstname");
			textNode.setTextContent("Kiss");
			
			Element textNode1 = document.createElement("lastname");
			textNode1.setTextContent("Anna");
			
			Element textNode2 = document.createElement("profession");
			textNode2.setTextContent("info");
			
			Element nodeElement = document.createElement("user");
			nodeElement.setAttribute("id", "4");
			
			nodeElement.appendChild(textNode);
			nodeElement.appendChild(textNode1);
			nodeElement.appendChild(textNode2);
			documentElement.appendChild(nodeElement);
			document.replaceChild(documentElement, documentElement);
			
			Transformer tFormer = TransformerFactory.newInstance().newTransformer();
			tFormer.setOutputProperty(OutputKeys.METHOD, "xml");
			
			Source source = new DOMSource(document);
			Result result = new StreamResult(xmlFile);
			tFormer.transform(source, result);


		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}