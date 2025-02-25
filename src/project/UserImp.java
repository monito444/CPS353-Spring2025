package project;

public class UserImp implements User{
	private final ComputeEngine computeEngineAPI;
	private final DataStorage dataStorageAPI;
	
	public UserImp(ComputeEngine computeEngineAPI, DataStorage dataStorageAPI) {
		this.computeEngineAPI = computeEngineAPI;
		this.dataStorageAPI = dataStorageAPI;
	}
	
	
	@Override
	public ComputeResult compute(ComputeRequest request) {
		return null;
	}
}