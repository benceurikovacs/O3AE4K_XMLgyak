package hu.domparse.O3AE4K;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.SourceDataLine;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMQueryO3AE4K {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		File file = new File("XMLO3AE4K.xml");
		// Parse-olás
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		Document doc = dBuilder.parse(file);
		doc.getDocumentElement().normalize();
		// Root element kiirasa
		System.out.print("Gyökér element: ");
		System.out.println(doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("Italkereskedes");

		// Minden Italkereskedes attribútum kiiratasa

		System.out.println("ITALKERESKEDESEK");
		System.out.println(nList.getLength());
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			System.out.println("\nElement nev : " + node.getNodeName());
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) node;
				System.out.println("ID:" + elem.getAttribute("ikod"));
				NodeList nList2 = elem.getChildNodes();
				for (int j = 0; j < nList2.getLength(); j++) {
					Node node2 = nList2.item(j);
					if (node2.getNodeType() == Node.ELEMENT_NODE) {
						Element elem2 = (Element) node2;
						if (!node2.getNodeName().equals("italkernev")) {
							System.out.println(node2.getNodeName() + " : " + node2.getTextContent());
						} else {

							NodeList nList3 = elem2.getChildNodes();
							for (int k = 0; k < nList3.getLength(); k++) {
								Node node3 = nList3.item(k);

								if (node3.getNodeType() == Node.ELEMENT_NODE) {

									System.out.println("	" + node3.getNodeName() + " : " + node3.getTextContent());
								}
							}
						}
					}
				}
			}
		}
		// Kiirja annak az Italkereskedésnek a nevét ami Pesten van
		System.out.println("\nKECSKEMETI ITALKERESKEDES\n");
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) node;
				NodeList nList2 = elem.getChildNodes();
				for (int j = 0; j < nList2.getLength(); j++) {
					Node node2 = nList2.item(j);
					if (node2.getNodeType() == Node.ELEMENT_NODE) {
						Element elem2 = (Element) node2;
						// System.out.println("Node neve:" + node2.getNodeName());
						if (node2.getNodeName().equals("italkernev")) {
							if (node2.getTextContent().equals("Kecskem�ti Nagyker")) {
								System.out.println("ID:" + elem.getAttribute("ikod"));
							}
						}
					}
				}
			}
		}
	}
}