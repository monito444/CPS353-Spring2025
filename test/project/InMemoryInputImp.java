import java.util.List;
import java.util.ArrayList;
import src.project.UserInputConfig;


public class InMemoryInputImp implements UserInputConfig{
    private List<Integer> inputData = new ArrayList<>();

   public InMemoryInputImp(List<Integer> inputData) {
	   this.inputData = inputData;
   }

}