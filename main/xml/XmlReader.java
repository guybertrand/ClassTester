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
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class XmlReader 
{
	public void ReadXmlFile(String filename)
	{
/*	<?xml version="1.0" encoding="UTF-8" standalone="no"?>
	<TestSuite>
		<testName class="SimpleTestSuiteSub1-Test1">
			<setup>0</setup>
			<test>16</test>
			<tearDown>0</tearDown>
			<TotalTestTime>16</TotalTestTime>
		</testName>

		//get a nodelist of elements
		NodeList nl = docEle.getElementsByTagName("Employee");
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {

				//get the employee element
				Element el = (Element)nl.item(i);

				//get the Employee object
				Employee e = getEmployee(el);

				//add it to list
				myEmpls.add(e);
			}
		}			
*/
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(filename);
		doc.getDocumentElement().normalize();
 
		NodeList testsNodeList = doc.getElementsByTagName("testName");
		
		for (int temp = 0; temp < testsNodeList.getLength(); temp++) 
		{
			Node testNode = testsNodeList.item(temp);
		    String testName = testNode.getAttribute("class");
		    Element testElement = (Element) testNode;
			setupTime=getTagValue("setup", testElement);
		    testTime=getTagValue("test",testElement);
			tearDownTime=getTagValue("tearDown", testElement);
			totalTestTime=getTagValue("TotalTestTime", testElement);
			System.out.println("testname=" + testName);
			System.out.println("setup=" + setupTime);
			System.out.println("testTime=" + testTime);
			System.out.println("tearDown=" + tearDownTime);
			System.out.println("total test time=" + totalTestTime);
		}
		NodeList totalRunTime=doc.getElementByTagName("TestSuiteTotalTime");
		String totalTestSuiteTime = TestSuiteTotalTime.getAttribute("TotalTime");
		System.out.println("Total time for test suite="+totalTestSuiteTime);
	}
}