package project;

public class InMemoryDataStorageAPI implements DataStorage {

	@Override
	public DataStorageReadResult read(UserInputConfig input) {
		return new DataStorageReadResultImp(((InMemoryInputImp)input).getInputs());
	}

	@Override
	public WriteResult appendSingleResult(UserOutputConfig output, String result, char delimiter) {
		((InMemoryOutputImp)output).getOutput().add(result);
		return () -> WriteResult.WriteResultStatus.SUCCESS;
	}
    
}