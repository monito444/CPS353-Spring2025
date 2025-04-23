package project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestComputeEngine {
    
	@Test
	public void testCompute() {
		ComputeEngine engine = new ComputeEngineImp();
		Assertions.assertEquals("44", engine.compute(10));
	}
}
