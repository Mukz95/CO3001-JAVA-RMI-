package co3090.rmi.queries;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import co3090.rmi.common.Query;

public abstract class AbstractQuery implements Query {

	private static final long serialVersionUID = 1L;
	protected String filename;
	protected Stream<String> dataStream;

	public AbstractQuery(String filename) {
		this.filename = filename;
	}
	
	public void setDataStream(Stream<String> stream) {
		try {
			this.dataStream = stream;
			this.dataStream= Files.lines(Paths.get(getFilename()),StandardCharsets.ISO_8859_1);


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getFilename() {
		return filename;
	}
	
	public abstract void printResults(List<?> results);
	
}
