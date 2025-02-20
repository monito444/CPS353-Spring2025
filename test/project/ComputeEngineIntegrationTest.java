import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import src.project.User;
import src.project.ComputeEngine;
import src.project.UserImp;
import src.project.ComputeEngineImp;

import java.util.List;
import java.util.ArrayList;


public class ComputeEngineIntegrationTest {
	@Test
	public void testCompute() {
		// use empty implementations from src and 
		// test only data storage
		ComputeEngine engineComponent = new ComputeEngineImp();
		User userComponent = new UserImp();
		
		// use test-only in-memory data storage
		InMemoryDataStorageAPI dataStorageComponent = new InMemoryDataStorageAPI();
		
		// give initial input test [1, 10, 25] with no delimiter specified
		List<Integer> sampleInput = new ArrayList<>();
		sampleInput.add(1);
		sampleInput.add(10);
		sampleInput.add(25);
		InMemoryInputImp testInput = new InMemoryInputImp(sampleInput);
		
		// use in-memory output imp
		InMemoryOutputImp testOutput = new InMemoryOutputImp();
		
		// add validation of the output
		List<String> expectedOutput = new ArrayList<>();
		expectedOutput.add("0");
		expectedOutput.add("44");
		expectedOutput.add("60,696");
		
		Assertions.assertEquals(expectedOutput, testOutput.getOutput());
	}
}
