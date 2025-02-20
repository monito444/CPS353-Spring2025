import java.util.List;
import java.util.ArrayList;
import src.project.UserOutputConfig;


public class InMemoryOutputImp implements UserOutputConfig{
    private List<String> outputData = new ArrayList<>();

    public List<String> getOutput() {
    	return outputData;
    }

}