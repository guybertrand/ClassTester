public class ClassTesterRunner extends ClassTester {

	public static void main(String[] args) throws Exception {

		// Enable assertions for application
		ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);

		ValidateArguments(args);

		createTestsConfiguration(args[0], args[1], Boolean
				.parseBoolean(args[2]));

		lauchTests();
		System.out.println("test Guy");
	}

	private static void ValidateArguments(String[] args) {
		if (args.length != 3) {
			System.err
					.println("Usage: ClassTesterRunner [string]ClassName [string]OutputFile [boolean]Encoding");
			System.exit(1);
		}

		if (!"true".equals(args[2].toString())
				&& !"false".equals(args[2].toString())) {
			System.err.println("Usage: Parameter Encoding must be boolean");
			System.exit(1);
		}
	}
}