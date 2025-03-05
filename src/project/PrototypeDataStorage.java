package project;

import project.DataStorageReadResult.Status;
import project.annotations.ProcessAPIPrototype;

public class PrototypeDataStorage{
	@ProcessAPIPrototype
	public void prototype(DataStorage apiCall) {
		// create user input config
		UserInputConfig inputConfig = new UserInputConfigImp("example.txt") {
			
		};
		
		// create user output config
		UserOutputConfig outputConfig = null;
		
		// Read in the input source file provided by ComputeEngine
		// if input file does not exist, send a fail status to ComputeEngine
		// else provide a success status
		DataStorageReadResult dataStorageReadResult = apiCall.read(inputConfig);
		
		// verify if all read result was successful
		if (dataStorageReadResult.getStatus() == Status.SUCCESS) {
			Iterable<Integer> loadedData = dataStorageReadResult.getResults();
			
			// write out the output
			for (int i : loadedData) {
				String result = "" + i;
				
				WriteResult writeResult = apiCall.appendSingleResult(outputConfig, result, ',');
				
				// verify if write was successful
				if (writeResult.getStatus() != WriteResult.WriteResultStatus.SUCCESS) {
					System.out.println("Writing output went wrong");
				}
			}
		}
	}
}
