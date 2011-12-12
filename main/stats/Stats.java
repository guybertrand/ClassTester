package stats;

//import org.apache.commons.math.stat.StatUtils;
import html.HtmlWriter;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.commons.math.stat.StatUtils;
import com.google.common.primitives.Doubles;
import xml.XmlReader;

public class Stats
{
	private HashMap<Date,Integer> nombreExecutionParDate = new HashMap<Date,Integer>();
	private HashMap<String, String> statusDesTests = new HashMap<String,String>();
	private HashMap<String,Integer> nombreDeJourEnEchec = new HashMap<String, Integer>();
	private HashMap<String,Integer> nombreDeJourPourReparer = new HashMap<String, Integer>();
	HashMap<String,Date> testDejaEnEchec = new HashMap<String, Date>();
	Calendar currentDate = Calendar.getInstance();
	Date laDateMaintenant = currentDate.getTime();
	ArrayList<Double> statsArrayDouble = new ArrayList<Double>();
	ArrayList<String> statsArrayString = new ArrayList<String>();
	
	public void generateStats(File[] listeDeFichier)
	{
		for (File fichier : listeDeFichier) 
		{
			parseData(fichier);
	    }
		
		generateStats();
		HtmlWriter htmlOut = new HtmlWriter();
		htmlOut.writeHtmlFile(nombreExecutionParDate, statusDesTests, nombreDeJourEnEchec, nombreDeJourPourReparer,statsArrayString);
	}
	
	private void generateStats() {
		
		double minVal=(double) 0;
		double max=(double) 0;
		double moyenne=(double) 0;
		double ecartType=(double) 0;
		double pourcent25=(double) 0;
		double median=(double) 0;
		double pourcent75=(double) 0;
		
		double[] statsArray = Doubles.toArray(statsArrayDouble);
		
		minVal=StatUtils.min(statsArray);
		max=StatUtils.max(statsArray);
		moyenne=StatUtils.mean(statsArray);
		ecartType=StatUtils.variance(statsArray);
		pourcent25=StatUtils.percentile(statsArray, 25);
		pourcent75=StatUtils.percentile(statsArray, 75);
		median=StatUtils.percentile(statsArray, 50);
		
		
		statsArrayString.add(Double.toString(minVal));
		statsArrayString.add(Double.toString(max));
		statsArrayString.add(Double.toString(moyenne));
		statsArrayString.add(Double.toString(ecartType));
		statsArrayString.add(Double.toString(pourcent25));
		statsArrayString.add(Double.toString(median));
		statsArrayString.add(Double.toString(pourcent75));
	}

	private void parseData(File dataFile)
	{
		ArrayList<ArrayList<String>> thisTestResults = new ArrayList<ArrayList<String>>();

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
			
			testData = itr.next();
			
			String nomDuTest = testData.get(0);
			String testPassFail = testData.get(4);
			String totalTestTime = testData.get(5);
			statsArrayDouble.add(Double.parseDouble(totalTestTime));
			
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
				Integer numDays = (int)(laDateMaintenant.getTime() - laDate.getTime()) / (1000 * 60 * 60 * 24);
				nombreDeJourEnEchec.put(nomDuTest,numDays);
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