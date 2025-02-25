package project;

import project.annotations.ProcessAPIPrototype;

public class PrototypeDataStorage{
	@ProcessAPIPrototype
	public void prototype(DataStorage storage) {
		// Read in the input source file provided by ComputeEngine
		// if input file does not exist, send a fail status to ComputeEngine
		// else provide a success status
		
		// Read in the output the compute engine provided
		// Write the output of the result to the output source file
	}
}
