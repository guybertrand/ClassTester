package file;

import java.io.File;
import java.io.FilenameFilter;

public class GetDataFileNames
{
	public File[] getListNamesDataFile()
	{
	// TODO nom de fichier hardcoder
	// TODO si on fait clean dans projet, les fichiers data sont
    // dans répertoire execution, donc ils seronts éffacer
	File dir = new File (".");
	
	File[] files = dir.listFiles
	(
			new FilenameFilter() 
			{
				public boolean accept(File dir, String name) 
				{
					if (name.startsWith("rundata"))
					{
						if (name.endsWith("xml"))
						{
								return true;
						}
						else
						{
							return false;
						}
					}
					return false;
				}
			}
     );
	return files;
	}
}