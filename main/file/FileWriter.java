package file;

import java.io.File;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class FileWriter 
{

	public File getFile(string extension)
	{
		String nomFichier = "rundata-" + FileWriter.maintenant("yyyy-MM-dd-hh-mm-ss") + extension;
		File fileFD = new File(nomFichier);
		return fileFD
	}

	public static String maintenant(String dateFormat)
	{
		Calendar calendrier = Calendar.getInstance();
		SimpleDateFormat maintenant  = new SimpleDateFormat(dateFormat);
		return maintenant.format(calendrier.getTime());
	}
}