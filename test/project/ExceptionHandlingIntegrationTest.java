package project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ExceptionHandlingIntegrationTest {
	// test to check IllegalArgumentException is being caught
	@Test
	public void testNullRequestException() {
		ComputeEngine testEngine = new ComputeEngineImp();
		DataStorage testDataStorage = new DataStorageImp();
		User testUser = new UserImp(testEngine, testDataStorage);
		
		// should throw IllegalArgumentException
		ComputeResult testResult = testUser.compute(null);
		Assertions.assertEquals(ComputeResult.FAILURE, testResult);
	}
	
	// test to check if NumberFormatException is being caught
	@Test
	public void testInvalidFileName() {
		DataStorage testDataStorage = new DataStorageImp();
		UserInputConfig testInput = new UserInputConfigImp("thisfiledoesnotexist");
		
		// should throw NumberFormatException
		DataStorageReadResult testResult = testDataStorage.read(testInput);
		Assertions.assertEquals(DataStorageReadResult.Status.FAILURE, testResult.getStatus());
	}
	
	// test to check if IOException is being caught
	@Test
	public void testInvalidDataFormat() {
		DataStorage testDataStorage = new DataStorageImp();
		UserInputConfig testInput = new UserInputConfigImp("test/project/invalidexample.txt");
		
		//should throw IOException
		DataStorageReadResult testResult = testDataStorage.read(testInput);
		Assertions.assertEquals(DataStorageReadResult.Status.FAILURE, testResult.getStatus());
	}
}
