package project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import org.mockito.Mockito;
import project.ComputeResult;
import project.UserImp;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;







public class TestUserImp{
	
	private ComputeEngine computeEngineMock;
    private DataStorage dataStorageMock;
    private UserImp userImp;

    @BeforeEach
    public void setUp() {
        computeEngineMock = mock(ComputeEngine.class);
        dataStorageMock = mock(DataStorage.class);
        userImp = new UserImp(computeEngineMock, dataStorageMock);
    }

    @Test
    public void testCompute_NullRequest() {
        ComputeResult result=userImp.compute(null);
        assertEquals(ComputeResult.FAILURE,result, "Expected failure");
    }

    @Test
    public void testCompute_NullDataStorageReadResult() {
    	
    	ComputeRequest mockRequest= mock(ComputeRequest.class);
    	UserInputConfig mockInputConfig = mock(UserInputConfig.class);
    	
    	when(mockRequest.getInputConfig()).thenReturn(mockInputConfig);
    	when(dataStorageMock.read(mockInputConfig)).thenReturn(null);
    	
    	ComputeResult result =userImp.compute(mockRequest);
    	assertEquals(ComputeResult.FAILURE, result, "Expected failure when DataStorageReadResult is null");
    }

    @Test
    public void testCompute_NullResultsInDataStorageReadResults() {
    	ComputeRequest mockRequest= mock(ComputeRequest.class);
    	UserInputConfig mockInputConfig = mock(UserInputConfig.class);
    	DataStorageReadResult mockReadResult= mock(DataStorageReadResult.class);
    	
    	when(mockRequest.getInputConfig()).thenReturn(mockInputConfig);
    	when(dataStorageMock.read(mockInputConfig)).thenReturn(mockReadResult);
    	when(mockReadResult.getResults()).thenReturn(null);
    	
    	ComputeResult result =userImp.compute(mockRequest);
    	assertEquals(ComputeResult.FAILURE,result,"Expected Failure when result in Data is Null");
       
    }
}
