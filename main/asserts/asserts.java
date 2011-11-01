package asserts;

public class asserts {

	public static void areEqual(Object firstObject, Object secondObject,
			String ErrorMsg) {
		assert (firstObject == secondObject) : ErrorMsg;
	}
}
