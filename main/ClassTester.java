import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import logs.Log;
import serviceLayer.Services;
import tests.Inventory;
import tests.SingleTest;
import annotations.Test;

public class ClassTester {

	static int intPassed = 0;
	static int intFailed = 0;
	static Log myLog;
	static Inventory testInventory = new Inventory();
	static boolean boolXML = false;

	protected static void createTestsConfiguration(String className) {
		createTestsConfiguration(className, "", false);
	}

	protected static void createTestsConfiguration(String className,
			String fileName, boolean XMLEncoding) {

		myLog = Services.getOutputMethod(className, fileName);
		boolXML = XMLEncoding;
		loadTests(className);
	}

	protected static void lauchTests() throws IllegalArgumentException,
			SecurityException, InvocationTargetException,
			NoSuchMethodException, ClassNotFoundException {

		for (SingleTest s : testInventory) {

			try {

				Services.ExecuteMethod(s.getClassName(), Class.forName(
						s.getClassName()).getMethod("setup"));
				Services.ExecuteMethod(s.getClassName(), s.getMethod());

				s.setTestResultMessage("Success");
				intPassed++;

			} catch (Throwable ex) {

				s.setTestResultMessage(ex.getCause().toString());
				intFailed++;

			} finally {

				Services.ExecuteMethod(s.getClassName(), Class.forName(
						s.getClassName()).getMethod("tearDown"));

			}

		}

		displayResults();

	}

	protected static void loadTests(String className) {

		try {

			for (Method methodToTest : Class.forName(className).getMethods()) {

				if (methodToTest.isAnnotationPresent(Test.class)
						&& methodToTest.getName() != "setup"
						&& methodToTest.getName() != "tearDown") {

					SingleTest aTest = new SingleTest(Class.forName(className)
							.toString().substring(6), methodToTest);

					testInventory.add(aTest);
				}

			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected static void addTestsSuite(String className) {
		loadTests(className);
	}

	private static void displayResults() {

		if (boolXML) {
			myLog.log("<ListOfTests>");
		}

		for (SingleTest s : testInventory) {

			if (boolXML) {

				myLog.log("<classname>" + s.getClassName() + "</classname>");
				myLog.log("<method>" + s.getMethodName() + "</method>");
				myLog.log("<result>" + s.getTestResultMessage() + "</result>");

			} else {

				myLog.log(s.getClassName() + "." + s.getMethodName());
				myLog.log("Result:" + s.getTestResultMessage());
			}

		}

		if (boolXML) {

			myLog.log("<GlobalResults>");
			myLog.log("<Passed>" + intPassed + "</Passed>");
			myLog.log("<Failed>" + intFailed + "</Failed>");
			myLog.log("</GlobalResults>");
			myLog.log("</ListOfTests>");

		} else {

			myLog.skipline();
			myLog.log("**** Passed:" + intPassed + " Failed " + intFailed);
		}

		myLog.close();

	}

}
