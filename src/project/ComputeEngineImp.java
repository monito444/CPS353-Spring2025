package project;

public class ComputeEngineImp implements ComputeEngine{
    private DataStorage dataStorageAPI;
    private User userAPI;
    
    public ComputeEngineImp(DataStorage dataStorageAPI, User userAPI) {
    	this.dataStorageAPI = dataStorageAPI;
    	this.userAPI = userAPI;
    }
    
    public String compute(int value) {
    	return null;
    }
}
