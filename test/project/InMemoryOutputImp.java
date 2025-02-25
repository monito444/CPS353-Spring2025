package project;

import java.util.List;
import java.util.ArrayList;


public class InMemoryOutputImp implements UserOutputConfig{
    private final List<String> outputData = new ArrayList<>();

    public List<String> getOutput() {
    	return outputData;
    }

}