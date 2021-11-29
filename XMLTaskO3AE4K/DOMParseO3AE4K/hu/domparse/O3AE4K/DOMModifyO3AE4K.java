package hu.domparse.O3AE4K;

import java.io.*;
import java.text.ParseException;

import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.xml.sax.*;

public class DOMModifyO3AE4K {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException,
    XPathExpressionException, DOMException, ParseException {
		
		File xml = new File("XMLO3AE4K.xml");
		
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xml);

        // a DOM document modositas
        DomModifier.modifyDom(document);

        // DOM document atalakitas
        DocumentTraversal traversal = (DocumentTraversal) document;

        //TreeWalker inicializalas
        TreeWalker walker = traversal.createTreeWalker(document.getDocumentElement(),
                NodeFilter.SHOW_ELEMENT | NodeFilter.SHOW_TEXT, null, true);

        DomTraverser.traverseLevel(walker, "");

	}
	
    private static class DomModifier {
        public static void modifyDom(Document document) throws XPathExpressionException, DOMException, ParseException {
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();

            // 1.) Szabo Attila neve megvaltoztatasa Nagy Tamasra
            Node owner = (Node) xpath.evaluate("//Futar[./futarnev='Szab� Attila']",
                    document, XPathConstants.NODE);
            owner.setTextContent("Nagy Tamas");

            // 2.) Minden 5000 forintnal dragabb rendeles csokkenjen 10%-al
            NodeList orders = (NodeList) xpath.evaluate("//Rendeles[./teljesar>5000]", document, XPathConstants.NODESET);
            
            for (int i = 0; i < orders.getLength(); i++) {
                Node order = orders.item(i);
                NodeList orderChilds = order.getChildNodes();
                for(int j = 0; j < orderChilds.getLength(); j++) {
                	Node currentNode = orderChilds.item(j);
                	System.out.println(currentNode.getNodeName());
                	if(currentNode.getNodeName().equals("teljesar")) {
                		
                        double price = Double.parseDouble(currentNode.getTextContent());
                        //System.out.println(price*0.9);
                        currentNode.setTextContent(Double.toString(price * 0.9));
                	}
                }
                
            }
        }
    }

    private static class DomTraverser {
        public static void traverseLevel(TreeWalker walker, String indent) {
            //aktualis csomopont
            Node node = walker.getCurrentNode();

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                printElementNode(node, indent);
            } else {
                printTextNode(node, indent);
            }

            // rekurziv meghivas
            for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
                traverseLevel(walker, indent + "    ");
            }

            walker.setCurrentNode(node);
        }

        private static void printElementNode(Node node, String indent) {
            System.out.print(indent + node.getNodeName());

            printElementAttributes(node.getAttributes());
        }

        private static void printElementAttributes(NamedNodeMap attributes) {
            int length = attributes.getLength();

            if (length > 0) {
                System.out.print(" [ ");

                for (int i = 0; i < length; i++) {
                    Node attribute = attributes.item(i);

                    System.out.printf("%s=%s%s", attribute.getNodeName(), attribute.getNodeValue(),
                            i != length - 1 ? ", " : "");
                }

                System.out.println(" ]");
            } else {
                System.out.println();
            }
        }

        private static void printTextNode(Node node, String indent) {
            String content_trimmed = node.getTextContent().trim();

            if (content_trimmed.length() > 0) {
                System.out.print(indent);
                System.out.printf("{ %s }%n", content_trimmed);
            }
        }
    }

}

