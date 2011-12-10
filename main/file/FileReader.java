package file;

import java.io.File;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class FileReader
{
	private String[] listOfFiles = null;
	private String[] filteredListOfFiles = null;
	File dir = null;  // TODO si on fait CLEAN dans gradle, bye-bye fichier!!
		
	public String[] getListOfFiles(String prefix, String extension)
	{
		dir = new File(".");
		listOfFiles = dir.list();
		String[] listOfFiles = dir.list();
		if (listOfFiles == null) 
		{
			// Either dir does not exist or is not a directory
			return null;
		} 
		else 
		{
			for (int i=0; i< listOfFiles.length; i++) 
			{
				int j =0;
				// Get filename of file or directory
				String filename = listOfFiles[i];
				if (filename.startsWith(prefix))
				{
					if (filename.endsWith(extension))
					{
						filteredListOfFiles[j] = filename;
						j++;
					}
				}
			}
		}
		return filteredListOfFiles;
	}
}