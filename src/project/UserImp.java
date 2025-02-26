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
		if (request==null){
			throw new IllegalArgumentException("Computation Cannot Take Null");
		}
		
		UserInputConfig inputConfiguration= request.getInputConfig();
		DataStorageReadResult readResult =dataStorageAPI.read(inputConfiguration);		
		Iterable<Integer> integer=readResult.getResults();
		
		if (integer!=null) {
			return ComputeResult.SUCCESS;
		}
		
		for (Integer i: integer) {//sends to compute Engine.
			//assuming all 'i' will be integers obviously.
			computeEngineAPI.compute(i);
		}
		
		UserOutputConfig outputConfig=request.getOutputConfig();
		return null;
		
		
	}
	
	
}