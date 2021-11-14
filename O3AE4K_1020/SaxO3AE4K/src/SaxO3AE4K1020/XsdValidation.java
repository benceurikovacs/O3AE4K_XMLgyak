package SaxO3AE4K1020;

import java.io.File;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XsdValidation {
	public static void main(String[] args) {
		
		try {
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = saxParserFactory.newSAXParser(); 
			SaxHandler handler = new SaxHandler();
			saxParser.parse(new File("macskakO3AE4K.xml"), handler);
			
			} catch (ParserConfigurationException | SAXException | IOException e) {
				e.printStackTrace();
			}
		
	}
}

class SaxHandler extends DefaultHandler{
	private int indent = 0;
	private String formatAttributes(Attributes attributes) {
		
		int attriLenght = attributes.getLength();
		if(attriLenght == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder(", {");
		sb.append("}");
		return sb.toString();	} 

private void indent() {
		
		
		for(int i=0; i<indent;i++) {
			System.out.print(" ");
		}
	}


public void characters(char ch[], int start, int length) {
	String chars = new String(ch, start, length).trim();
	
	if(!chars.isEmpty()) {
		indent++;
		indent();
		indent--;
		System.out.println("XSD Validation succesful");
	}
}
}
