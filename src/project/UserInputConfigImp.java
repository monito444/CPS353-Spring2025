package project;

public class UserInputConfigImp implements UserInputConfig{

	private String fileName;
	
	public UserInputConfigImp(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public String getFileName() {
		return fileName;
	}
	
}
