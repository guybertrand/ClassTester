package logs;

public abstract class Log {

	public void log(String text) {
	}

	public void close() {

	}

	public void output(String text) {

	}

	public void skipline() {
		this.log("");
	}
}
