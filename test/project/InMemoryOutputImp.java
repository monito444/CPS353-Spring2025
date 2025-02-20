import java.util.List;
import src.project.UserOutputConfig;


public class InMemoryOutputImp implements UserOutputConfig{
    private List<String> outputData;

    public List<String> getOutput() {
    	return outputData;
    }

}