package project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import project.WriteResult.WriteResultStatus;


public class TestDataStorage {

	@Test
	public void testMethodDataStorage() throws Exception{
		UserInputConfig mockInputConfig = Mockito.mock(UserInputConfig.class);
		
		DataStorage testDataStorage = new DataStorageImp();
		Assertions.assertEquals(testDataStorage.read(mockInputConfig).getStatus(), DataStorageReadResult.Status.SUCCESS);
		
		UserOutputConfig mockOutputConfig = Mockito.mock(UserOutputConfig.class);
		
		Assertions.assertEquals(WriteResultStatus.SUCCESS, testDataStorage.appendSingleResult(mockOutputConfig, "result", ';').getStatus());
	}

}
