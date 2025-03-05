package project;
import java.util.List;

public class UserOutputConfigImp implements UserOutputConfig{

	private String fileName;
	private List<String> data;
	
	public UserOutputConfigImp(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public String getFileName() {
		return fileName;
	}
	@Override
	public void writeOutput(List<String> data) {
		// TODO Auto-generated method stub
		this.data.addAll(data);
	}

}
