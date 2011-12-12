package stats;

//import org.apache.commons.math.stat.StatUtils;
import html.HtmlWriter;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.math.stat.descriptive.AggregateSummaryStatistics;

import xml.XmlReader;

public class Stats
{
	private HashMap<Date,Integer> nombreExecutionParDate = new HashMap<Date,Integer>();
	private HashMap<String, String> statusDesTests = new HashMap<String,String>();
	private HashMap<String,Integer> nombreDeJourEnEchec = new HashMap<String, Integer>();
	private HashMap<String,Integer> nombreDeJourPourReparer = new HashMap<String, Integer>();
	
	
		
	public void generateStats(File[] listeDeFichier)
	{
		for (File fichier : listeDeFichier) 
		{
		    parseData(fichier);
	    }
		
		HtmlWriter htmlOut = new HtmlWriter();
		System.err.println("1"+nombreDeJourEnEchec.toString());
		htmlOut.writeHtmlFile(nombreExecutionParDate, statusDesTests, nombreDeJourEnEchec, nombreDeJourPourReparer);
	}
	
	private void parseData(File dataFile)
	{
		ArrayList<ArrayList<String>> thisTestResults = new ArrayList<ArrayList<String>>();
		HashMap<String,Date> testDejaEnEchec = new HashMap<String, Date>();
		
		// stats de date
		Date laDate = getDateFromDataFilename(dataFile.getName());
		Integer dateDansMap =nombreExecutionParDate.get(laDate);
		if (dateDansMap == null)
		{
			nombreExecutionParDate.put(laDate, 1);	
		}
		else
		{
			nombreExecutionParDate.put(laDate, dateDansMap + 1);
		}
		
		// parse stats test individuel
		XmlReader readXML = new XmlReader();
		thisTestResults=readXML.ReadXmlFile(dataFile.getName());
		
		Iterator<ArrayList<String>> itr = thisTestResults.iterator();
		
		while (itr.hasNext())
		{
			ArrayList<String> testData = new ArrayList<String>();
			Date laDateMaintenant = new Date();
			
			testData = itr.next();
			
			String nomDuTest = testData.get(0);
			String testPassFail = testData.get(4);
			
			// liste de status des tests
			statusDesTests.put(nomDuTest, testPassFail);
			
			// vérifier changement résultat test
			Date TestDejaEnEchecDate = testDejaEnEchec.get(nomDuTest);
			if(testPassFail.equals("false"))
			{
				if (TestDejaEnEchecDate == null) // test nouvellement = false
				{
					testDejaEnEchec.put(nomDuTest, laDate);
				}
				Integer numMilli = (int)(laDateMaintenant.getTime() - laDate.getTime()); 
						/// (1000 * 60 * 60 * 24);
				System.err.println("stats: numdays="+numMilli+" "+laDateMaintenant.getTime()+" "+laDate.getTime());
				//nombreDeJourEnEchec.put(nomDuTest,numDays);
			}
			else
			{
				if (TestDejaEnEchecDate != null) 
				{
					// changement de false a true
					nombreDeJourPourReparer.put(nomDuTest, (int)(TestDejaEnEchecDate.getTime() - laDate.getTime()) / (1000 * 60 * 60 * 24));
					testDejaEnEchec.remove(nomDuTest);
				}
			}
		}
	}
	
	private Date getDateFromDataFilename(String dataFilename)
	{
		Date dateExecution = null;
		String[] splitPrefix, splitExtension;
		
		// TODO doit etre refactorer pour etre proche de l'ecriture et format nom fichier data
		//"rundata-" + FileWriter.maintenant("yyyy-MM-dd-hh-mm-ss") + extension;
		splitPrefix=(dataFilename.split("rundata-"));
		splitExtension=(splitPrefix[1].split(".xml"));
		DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dateExecution = dfm.parse(splitExtension[0]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateExecution;
	}
}