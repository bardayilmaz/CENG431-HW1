package fileio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.File;
import java.io.FileNotFoundException;

public class CsvReader implements IFileReader<List<String>> {

	private String seperator;
	
	public CsvReader(String seperator) {
		this.seperator = seperator;
	}

	@Override
	public List<List<String>> readFile(String path) {
		List<List<String>> result = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new File(path));
			String line;
			while(sc.hasNextLine()) {
				line = sc.nextLine();
				result.add(Arrays.asList(line.split(getSeperator())).stream().map(t -> t.trim()).collect(Collectors.toList()));
			}
		} catch (FileNotFoundException e) {
			System.err.println("file not found");
			e.printStackTrace();
		}
		
		return result;
	}

	public String getSeperator() {
		return seperator;
	}

	public void setSeperator(String seperator) {
		this.seperator = seperator;
	}


	

}
