package fileio;

import java.util.List;

public interface IFileReader<T> {

	public List<T> readFile(String path);
}
