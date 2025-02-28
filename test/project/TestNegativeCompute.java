package project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestNegativeCompute {
	// Test the high values and negative input
	@Test
	public void testNegativeValue() {
		ComputeEngine engine = new ComputeEngineImp();
		Assertions.assertEquals("188", engine.compute(-13));
		Assertions.assertEquals("4613732", engine.compute(-34));
	}
}
