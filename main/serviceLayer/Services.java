package serviceLayer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import logs.Log;
import logs.LogConsole;
import logs.LogFile;

public class Services {

	public static void ExecuteMethod(String ClassName, Method methodToTest)
			throws IllegalArgumentException, InvocationTargetException {
		try {
			Object anInstance = Class.forName(ClassName).newInstance();

			methodToTest.setAccessible(true);
			methodToTest.invoke(anInstance);

		} catch (ClassNotFoundException ex) {
			// x.printStackTrace();
		} catch (IllegalAccessException ex) {
			// x.printStackTrace();
		} catch (InstantiationException e) {
			// x.printStackTrace();
		}
	}

	public static Log getOutputMethod(String className, String logFileName) {
		Log anOutput = new LogConsole();

		try {
			if (!logFileName.equals("")) {
				anOutput = new LogFile();
				anOutput.output(logFileName + ".txt");
			}
		} catch (Throwable ex) {

			System.out.println("ERROR FILE");
		}

		return anOutput;

	}
}
