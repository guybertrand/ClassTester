package xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import file.FileWriter;

public class XmlWriter 
{
	DocumentBuilderFactory docFactory = null;
	DocumentBuilder docBuilder = null;
	Document doc = null;
	Element rootElement = null;
	Element xmlEntry = null;
	
	public void StartXMLEntry()
	{
		try
		{
			docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.newDocument();
		}
		catch (ParserConfigurationException pce) 
		{
			pce.printStackTrace();
		} 
		rootElement = doc.createElement("TestSuite");
		doc.appendChild(rootElement);
	}
	
	public void AddXMLTest(String testName, long lengthSetup, 
		long lengthTest, long lengthTearDown, long totalLengthTest, Boolean passOrFail)
	{
		xmlEntry = doc.createElement("testName");
		rootElement.appendChild(xmlEntry);
		Attr attr = doc.createAttribute("class");
		attr.setValue(testName);
		xmlEntry.setAttributeNode(attr);
	 
		Element setupEntry = doc.createElement("setup");
		setupEntry.appendChild(doc.createTextNode(String.valueOf(lengthSetup)));
		xmlEntry.appendChild(setupEntry);
	 
		Element testEntry = doc.createElement("test");
		testEntry.appendChild(doc.createTextNode(String.valueOf(lengthTest)));
		xmlEntry.appendChild(testEntry);
	 
		Element tearDownEntry = doc.createElement("tearDown");
		tearDownEntry.appendChild(doc.createTextNode(String.valueOf(lengthTearDown)));
		xmlEntry.appendChild(tearDownEntry);
		
		Element passFailEntry = doc.createElement("passFail");
		passFailEntry.appendChild(doc.createTextNode(String.valueOf(passOrFail)));
		xmlEntry.appendChild(passFailEntry);

		Element TotalTestEntry = doc.createElement("TotalTestTime");
		TotalTestEntry.appendChild(doc.createTextNode(String.valueOf(totalLengthTest)));
		xmlEntry.appendChild(TotalTestEntry);
	}
	
	public void FinishXMLEntry(Long endTestSuiteTime)
	{

		xmlEntry = doc.createElement("TestSuiteTotalTime");
		rootElement.appendChild(xmlEntry);
		Attr attr = doc.createAttribute("TotalTime");
		attr.setValue(String.valueOf(endTestSuiteTime));
		xmlEntry.setAttributeNode(attr);
		
		TransformerFactory transformerFactory = null;
		Transformer transformer = null;
		DOMSource source = new DOMSource(doc);
		StreamResult result = null;
	
		FileWriter outputFile = new FileWriter();
		try
		{
			// write the content into xml file
			transformerFactory = TransformerFactory.newInstance();
			transformer = transformerFactory.newTransformer();
			result = new StreamResult(outputFile.getFileDesc(".xml"));
			transformer.transform(source, result);
		}
		catch (TransformerException tfe) 
		{
			tfe.printStackTrace();
		}
		System.out.println("File saved! name="+outputFile.getFilename());
	}

}
