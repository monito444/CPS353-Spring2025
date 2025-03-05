package project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

public class ComputeEngineIntegrationTest {
	@Test
	public void testCompute() {
		// use empty implementations from src and 
		// test only data storage
		ComputeEngine engineComponent = new ComputeEngineImp();
		DataStorage dataStorageComponent = new DataStorageImp();
		User userComponent = new UserImp(engineComponent, dataStorageComponent);
		
		// give initial input test [1, 10, 25] with no delimiter specified
		InMemoryInputImp testInput = new InMemoryInputImp(1, 10, 25);
		
		// use in-memory output imp
		InMemoryOutputImp testOutput = new InMemoryOutputImp();
		
		ComputeRequest request = new ComputeRequest(testInput, testOutput);
		
		// compute result check
		ComputeResult result = userComponent.compute(request);
		Assertions.assertEquals(ComputeResult.SUCCESS, result);
		
		// add validation of the output
		List<String> expectedOutput = new ArrayList<>();
		expectedOutput.add("0");
		expectedOutput.add("44");
		expectedOutput.add("60,696");
		
		// output check
		Assertions.assertEquals(expectedOutput, testOutput.getOutput());
	}
}
