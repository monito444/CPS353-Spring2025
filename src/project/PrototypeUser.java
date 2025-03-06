package project;
import project.annotations.NetworkAPIPrototype;

public class PrototypeUser{
	@NetworkAPIPrototype
	public void prototype(User apiCall) {
		// ask the user to input a file name
		// if file name does not exist, ask the user again to input a file name
		UserInputConfig inputConfig = new UserInputConfigImp("example.txt") {
			
		};
		
		// ask the user to give optional default delimiters to the output
		
		// ask the user where to give a name for the output file
		// if file name already exists, then overwrite the file.
		UserOutputConfig outputConfig = null;
		
		// create ComputeRequest
		ComputeRequest request = new ComputeRequest(inputConfig, outputConfig, ',');
		
		// get the result
		ComputeResult result = apiCall.compute(request);
		
		// get result status
		if(result.getStatus().isSuccess()) {
			System.out.println("Success!");
		}
	}
}