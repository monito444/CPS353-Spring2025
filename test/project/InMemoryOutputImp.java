package project;

import java.util.List;
import java.util.ArrayList;


public class InMemoryOutputImp implements UserOutputConfig{
    private final List<String> outputData = new ArrayList<>();
    
    public List<String> getOutput() {
    	return outputData;
    }

	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return "test/project/exampleoutput.txt";
	}
	@Override
	public void writeOutput(List<String> data) {
		// TODO Auto-generated method stub
		outputData.addAll(data);
	}

}