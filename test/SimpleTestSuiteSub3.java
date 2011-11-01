import annotations.Test;
import asserts.asserts;

public class SimpleTestSuiteSub3 implements interfaces.TestCase {

	@Test
	public static void Test1() {
		int value = 100;
		asserts.areEqual(value, 0, "Value is not zero");
	}

	@Test
	public void Test2() {

	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}

}
