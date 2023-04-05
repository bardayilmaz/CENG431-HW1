package util;

import java.util.ArrayList;
import java.util.List;

import fileio.CsvReader;
import user.AbstractStudentUser;
import user.StudentUser;

public class StudentUserCsvCreator {
	
	private CsvReader csvReader;
	private String path;
	
	

	public StudentUserCsvCreator(CsvReader csvReader, String path) {
		super();
		this.csvReader = csvReader;
		this.path = path;
	}


	public StudentUserCsvCreator() {
		csvReader = null;
		path = "";
	}
	
	
	public List<AbstractStudentUser> createStudents() {
		List<List<String>> csvResult = csvReader.readFile(this.path);
		List<AbstractStudentUser> result = new ArrayList<>();
		for(List<String> csvRow : csvResult) {
			result.add(new StudentUser(csvRow.get(0), csvRow.get(1)));
		}
		return result;
	}


	public CsvReader getCsvReader() {
		return csvReader;
	}


	public void setCsvReader(CsvReader csvReader) {
		this.csvReader = csvReader;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}
	
	
}
