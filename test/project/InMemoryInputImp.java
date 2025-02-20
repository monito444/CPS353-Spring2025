import java.util.List;
import src.project.UserInputConfig;


public class InMemoryInputImp implements UserInputConfig{
    private List<Integer> inputData;

   public InMemoryInputImp(List<Integer> inputData) {
	   this.inputData = inputData;
   }

}