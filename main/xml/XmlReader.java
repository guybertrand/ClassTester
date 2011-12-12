package xml;

import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class XmlReader 
{
	private ArrayList<ArrayList<String>> testListInfo = new ArrayList<ArrayList<String>>();
	
	public ArrayList<ArrayList<String>> ReadXmlFile(String filename)
	{
		try
		{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(filename);
			doc.getDocumentElement().normalize();
 
			NodeList testsNodeList = doc.getElementsByTagName("testName");
		
			for (int temp = 0; temp < testsNodeList.getLength(); temp++) 
			{
				ArrayList<String> thisTestResults = new ArrayList<String>();
				String testName = null;
				Node testNode = testsNodeList.item(temp);
				testName=testNode.getAttributes().getNamedItem("class").getNodeValue();
				thisTestResults.add(testName);
				
				if (testNode.getNodeType() == Node.ELEMENT_NODE) 
				{
					//TODO refactor ici.  it smells: code duplication!!
		 		     Element eElement = (Element) testNode;
		 		     NodeList nlList = null; 
		 		     
		 		     nlList = eElement.getElementsByTagName("setup").item(0).getChildNodes();
		 		     Node nValue = (Node) nlList.item(0);
		 		     thisTestResults.add(nValue.getNodeValue());
		 		   		 		     
		 		     nlList = eElement.getElementsByTagName("test").item(0).getChildNodes();
		 		     nValue = (Node) nlList.item(0);
		 		     thisTestResults.add(nValue.getNodeValue());
		 		   		 		     
		 		    nlList = eElement.getElementsByTagName("tearDown").item(0).getChildNodes();
		 		     nValue = (Node) nlList.item(0);
		 		     thisTestResults.add(nValue.getNodeValue());
		 		   		 		    
		 		    nlList = eElement.getElementsByTagName("passFail").item(0).getChildNodes();
		 		   nValue = (Node) nlList.item(0);
		 		   thisTestResults.add(nValue.getNodeValue());
		 		    
		 		     nlList = eElement.getElementsByTagName("TotalTestTime").item(0).getChildNodes();
		 		    nValue = (Node) nlList.item(0);
		 		     thisTestResults.add(nValue.getNodeValue());
		 		   
			   }
				testListInfo.add(thisTestResults);
				
			}
		}
		catch (Exception e)
		{//Catch exception if any
			  System.err.println("XMLReader Error: " + e.getMessage());
		}
		
		return testListInfo;
	}
}