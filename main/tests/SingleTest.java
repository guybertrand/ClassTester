package tests;

import java.lang.reflect.Method;

public class SingleTest {

	private String className;
	private Method aMethod;
	private String testResultMessage;

	public SingleTest(String cName, Method theMethod) {

		className = cName;
		aMethod = theMethod;
	}

	public String getClassName() {
		return className;
	}

	public Method getMethod() {
		return aMethod;
	}

	public String getMethodName() {
		return aMethod.getName();
	}

	public String getTestResultMessage() {
		return testResultMessage;
	}

	public void setTestResultMessage(String testResultMessage) {
		this.testResultMessage = testResultMessage;
	}

}