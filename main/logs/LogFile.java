package logs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LogFile extends Log {

	FileWriter fstream;
	BufferedWriter out;

	@Override
	public void log(String text) {
		try {
			out.write(text);
			out.newLine();

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	@Override
	public void output(String filename) {
		try {
			fstream = new FileWriter(filename, false);
			out = new BufferedWriter(fstream);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void close() {
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
