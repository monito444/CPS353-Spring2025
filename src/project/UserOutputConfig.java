package project;
import java.util.List;

public interface UserOutputConfig{
	String getFileName();
	
	void writeOutput(List<String> data);
}
