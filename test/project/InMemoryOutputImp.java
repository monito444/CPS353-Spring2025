package project;

import java.util.List;
import java.util.ArrayList;
import project.UserOutputConfig;


public class InMemoryOutputImp implements UserOutputConfig{
    private List<String> outputData = new ArrayList<>();

    public List<String> getOutput() {
    	return outputData;
    }

}