import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import src.project.DataStorage;
import src.project.ComputeEngine;

public class TestUser {

	@Test
	public void testMethod() throws Exception{
		DataStorage mockDataStorage = Mockito.mock(DataStorage.class);
		ComputeEngine mockComputeEngine = Mockito.mock(ComputeEngine.class);
	}

}
