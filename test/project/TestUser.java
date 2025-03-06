package project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;


public class TestUser {

	@Test
	public void testMethod() throws Exception{
		DataStorage mockDataStorage = Mockito.mock(DataStorage.class);
		ComputeEngine mockComputeEngine = Mockito.mock(ComputeEngine.class);
		
		when(mockDataStorage.read(any(UserInputConfig.class)))
		.thenReturn(new DataStorageReadResultImp(new ArrayList<Integer>()));
		
		User testUser = new UserImp(mockComputeEngine, mockDataStorage);
		
		ComputeRequest mockRequest = Mockito.mock(ComputeRequest.class);
		when(mockRequest.getInputConfig()).thenReturn(Mockito.mock(UserInputConfig.class));
		when(mockRequest.getOutputConfig()).thenReturn(Mockito.mock(UserOutputConfig.class));
		
		ComputeResult result = testUser.compute(mockRequest);
		
		//check
		Assertions.assertEquals(result.getStatus(), ComputeResult.ComputeResultStatus.SUCCESS);
	}
}
