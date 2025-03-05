package project;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;


public class InMemoryInputImp implements UserInputConfig{
    private final List<Integer> inputData = new ArrayList<>();

	// enter any number of inputs at a time
    public InMemoryInputImp(int... inputs) {
		for(int i : inputs) {
			inputData.add(i);
		}
	}
	
    // enter a collection of inputs at once
	public InMemoryInputImp(Collection<Integer> inputs) {
		inputData.addAll(inputs);
	}
	
	public List<Integer> getInputs() {
		return inputData;
	}

	@Override
	public String getFileName() {
		return "";
	}
}