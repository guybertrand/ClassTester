package html;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class HtmlWriter 
{
		
	public void writeHtmlFile(
			HashMap<Date,Integer> nombreExecutionParDate,
			HashMap<String, String> statusDesTests,
			HashMap<String,Integer> nombreDeJourEnEchec,
			HashMap<String,Integer> nombreDeJourPourReparer,
			ArrayList<String> statsArray
			)
	{
		try
		{
			  FileWriter fstream = new FileWriter("TEST-SOMMAIRE.HTML",false);
			  BufferedWriter htmlout = new BufferedWriter(fstream);
			  htmlout.write(writeHeader());
			  htmlout.write(writeTestStatus(statusDesTests));
			  htmlout.write(writeNumTestsPerDay(nombreExecutionParDate));
			  htmlout.write(writeTestStats(statsArray));
			  htmlout.write(writeTestLengthError(nombreDeJourEnEchec));
			  htmlout.write(writeTestTurnAroundTime(nombreDeJourPourReparer));
			  htmlout.write("</html></body>");
			  htmlout.close();
			  System.out.println("HTML file is named: TEST-SOMMAIRE.HTML");
			  }
		catch (Exception e)
		{//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
		}
	}
	
	private String writeTestStats(ArrayList<String> statsArray) 
	{
		String returnString = "<table border='1'><tr><th colspan=8>Statistiques des tests</th></tr>";
		returnString = returnString + 
				"<tr>" +
				"<td>Valeur minimum</td>" + 
				"<td>Valeur maximum</td>" +
				"<td>Valeur moyenne</td>" +
				"<td>Écart Type</td>" +
				"<td>Médianne</td>" +
				"<td>25e pourcentile</td>" +
				"<td>75e pourcentile</td>" +
				"</tr>";
		
		returnString = returnString +
				"<tr>" +
				"<td>"+statsArray.get(0)+"</td>" + 
				"<td>"+statsArray.get(1)+"</td>" +
				"<td>"+statsArray.get(2)+"</td>" +
				"<td>"+statsArray.get(3)+"</td>" +
				"<td>"+statsArray.get(4)+"</td>" +
				"<td>"+statsArray.get(5)+"</td>" +
				"<td>"+statsArray.get(6)+"</td>" +
				"</tr>";
		
		returnString = returnString + "</table>";
		return(returnString);
	}

	private String writeHeader()
	{
		return "<html>" +
				"<head>" +
				"<title>Résultat test classtest</title>" +
				"</head>" +
				"<body><h1>Résultat Test</h1>";
	}
	
	private String writeTestStatus(HashMap<String, String> statusDesTests)
	{
		String returnString = "<table border='1'><tr><th colspan=8>Statut des tests</th></tr>";
		returnString = returnString + 
				"<tr><td>Nom de test</td>" + 
				"<td>Status</td>";
		
		for (String nomTest : statusDesTests.keySet()) 
		{ 
            returnString = returnString + 
            		"<tr><td>" + nomTest + "</td><td>" + 
            		statusDesTests.get(nomTest) + 
            		"</td></tr>";
		}
		returnString = returnString + "</table>";
		return(returnString);
	}
	
	private String writeNumTestsPerDay(HashMap<Date,Integer> nombreExecutionParDate)
	{
		String returnString = "<table border='1'><tr><th colspan=8>Tests par jour</th></tr>";
		returnString = returnString + 
				"<tr><td>Date</td>" + 
				"<td>Nombre de tests</td>";
		
		for (Date dateTest : nombreExecutionParDate.keySet()) 
		{ 
            returnString = returnString + 
            		"<tr><td>" + dateTest + "</td><td>" + 
            		nombreExecutionParDate.get(dateTest).toString() + 
            		"</td></tr>";
		}
		returnString = returnString + "</table>";
		return(returnString);
	}
	
	private String writeTestLengthError(HashMap<String, Integer> nombreDeJourEnEchec)
	{
		String returnString = "<table border='1'><tr><th colspan=8>Tests en erreur - nombre de jour</th></tr>";
		returnString = returnString + 
				"<tr><td>Nom</td>" + 
				"<td>Nombre de jours</td>";
		
		for (String nomTest : nombreDeJourEnEchec.keySet()) 
		{ 
			returnString = returnString + 
           		"<tr><td>" + nomTest + "</td><td>" + 
           		nombreDeJourEnEchec.get(nomTest).toString() + 
           		"</td></tr>";
		}
				
		returnString = returnString + "</table>";
		return(returnString);
	}
		
	private String writeTestTurnAroundTime(HashMap<String, Integer> nombreDeJourPourReparer)
	{
		String returnString = "<table border='1'><tr><th colspan=8>Nombre de jours pour réparer</th></tr>";
		returnString = returnString + 
				"<tr><td>Nom</td>" + 
				"<td>Nombre de jours</td>";
		
		if(nombreDeJourPourReparer.size() > 0)
		{
			for (String nomTest : nombreDeJourPourReparer.keySet()) 
			{	 
            returnString = returnString + 
            		"<tr><td>" + nomTest + "</td><td>" + 
            		nombreDeJourPourReparer.get(nomTest).toString() + 
            		"</td></tr>";
			}
		}
		else
		{
			returnString=returnString +
					"<tr><td colspan=4>Aucun test n'a été corrigé</td></tr>";
		}
		returnString = returnString + "</table>";
		return(returnString);
	}


}