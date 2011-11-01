import annotations.Test;
import asserts.asserts;

public class SimpleTestSuiteSub2 implements interfaces.TestCase {

	@Test
	public static void Test1() {
		asserts.areEqual(0, 1, "Impossible if, 1 can NEVER be equal to 0.");
	}

	@Test
	public void Test2() {

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
