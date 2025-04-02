package project;

import java.io.File;


public class TestUserThread {
	
	// TODO 3: change the type of this variable to the name you're using for your
	// @NetworkAPI interface; also update the parameter passed to the constructor
	private final User coordinator;

	public TestUserThread(User coordinator) {
		this.coordinator = coordinator;
	}

	public void run(String outputPath) {
		char delimiter = ';';
		String inputPath = "test/project" + File.separatorChar + "testInputFile.test";
		
		// TODO 4: Call the appropriate method(s) on the coordinator to get it to 
		// run the compute job specified by inputPath, outputPath, and delimiter
		UserInputConfig input = new UserInputConfigImp(inputPath);
		UserOutputConfig output = new UserOutputConfigImp(outputPath);
		ComputeRequest request = new ComputeRequest(input, output, delimiter);
		
		coordinator.compute(request);
	}

}