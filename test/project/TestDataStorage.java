package project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import project.WriteResult.WriteResultStatus;


public class TestDataStorage {

	@Test
	public void testMethodDataStorage() throws Exception{
		UserInputConfig mockInputConfig = Mockito.mock(UserInputConfig.class);
		when(mockInputConfig.getFileName()).thenReturn("src/project/example.txt");
		
		DataStorage testDataStorage = new DataStorageImp();
		Assertions.assertEquals(testDataStorage.read(mockInputConfig).getStatus(), DataStorageReadResult.Status.SUCCESS);
		
		UserOutputConfig mockOutputConfig = Mockito.mock(UserOutputConfig.class);
		when(mockOutputConfig.getFileName()).thenReturn("src/project/outputexample.txt");
		
		Assertions.assertEquals(WriteResultStatus.SUCCESS, testDataStorage.appendSingleResult(mockOutputConfig, "result", ';').getStatus());
	}

}
