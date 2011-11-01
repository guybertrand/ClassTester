import annotations.Test;
import asserts.asserts;

public class SimpleTest extends ClassTester implements interfaces.TestCase {

	static int Value1;
	static int Value2;

	@Override
	public void setup() {
		Value1 = 10;
		Value2 = 5;
	}

	@Override
	public void tearDown() {
	}

	@Test
	public static void SimpleTest1() {
		asserts.areEqual(Value1, Value2, "Value1 and Value2 are not equal");
	}

	@Test
	public static void SimpleTest2() {
		Value2 = 10;
		asserts.areEqual(Value1, Value2, "Value1 and Value2 are equal");
	}

	@Test
	public static void SimpleTest3() {
		asserts.areEqual(Value1, Value2, "Value1 and Value2 are not equal");
	}

}