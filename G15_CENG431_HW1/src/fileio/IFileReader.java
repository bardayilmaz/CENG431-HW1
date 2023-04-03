package fileio;

import java.util.Collection;

public interface IFileReader<T> {

	public Collection<T> readFile(String path);
}
