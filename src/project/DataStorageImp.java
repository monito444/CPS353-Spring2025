package project;

public class DataStorageImp implements DataStorage{
	private ComputeEngine engine;
	
	public DataStorageImp(ComputeEngine engine) {
		this.engine = engine;
	}
	
	@Override
	public DataStorageReadResult read(UserInputConfig input) {
		return null;
	}
	
	@Override
	public WriteResult appendSingleResult(UserOutputConfig output, String result, char delimiter) {
		return null;
	}
}