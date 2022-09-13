package tw.com.eshop.tools;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/*
 * 從xml讀取設定值
 */
public class readconfig {
	
	String configxmlPath;
	
	public  readconfig(String configxmlPath) {
		this.configxmlPath=configxmlPath;
	}

	public static readconfig getreadconfig(String configxmlPath) {
		return new readconfig(configxmlPath);
	}

	/* 
	 * 讀取admin/config.xml裡頭的admin類別下restrictminutes的值
	 * 傳入值為伺服器之真實絕對位置
	 */
	public int readrestrictminutes() {
		int restrictminutes = -1;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Document doc;
		try {
			builder = factory.newDocumentBuilder();
			File xml = new File(configxmlPath);
			doc = builder.parse(xml);
			// doc.getDocumentElement();
			NodeList adminNodes = doc.getElementsByTagName("admin");
			for (int i = 0; i < adminNodes.getLength(); i++) {
				Node adminNode = adminNodes.item(i);
				if (adminNode.getNodeType() == Node.ELEMENT_NODE) {
					Element adminElement = (Element) adminNode;
					restrictminutes = Integer.parseInt(adminElement.getElementsByTagName("restrictminutes").item(0).getTextContent());
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return restrictminutes;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return restrictminutes;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return restrictminutes;
		} catch (SAXException e) {
			e.printStackTrace();
			return restrictminutes;
		} catch (IOException e) {
			e.printStackTrace();
			return restrictminutes;
		}
		return restrictminutes;
	}

}
