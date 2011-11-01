import annotations.Test;
import asserts.asserts;

public class SimpleTestSuiteSub1 implements interfaces.TestCase {

	@Test
	public static void Test1() {
		asserts.areEqual(0, 1, "Zero and one are not equal");
	}

	@Test
	public void Test2() {
		asserts.areEqual(1, 1, "One and one are equal");
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}

}
