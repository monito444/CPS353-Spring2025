package project;

public class UserOutputConfigImp implements UserOutputConfig{

	private String fileName;
	
	public UserOutputConfigImp(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public String getFileName() {
		return fileName;
	}

}
