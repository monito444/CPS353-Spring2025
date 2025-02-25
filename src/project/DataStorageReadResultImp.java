package project;

public class DataStorageReadResultImp implements DataStorageReadResult{
	private final Iterable<Integer> iterable;
	private final Status statusCode;
	private final String errorMessage;
	
	// construct if read is successful
	public DataStorageReadResultImp(Iterable<Integer> iterable) {
		this.iterable = iterable;
		this.statusCode = Status.SUCCESS;
		this.errorMessage = "";
	}
	
	// construct if read is unsuccessful
	public DataStorageReadResultImp(String errorMessage) {
		this.iterable = null;
		this.statusCode = Status.FAILURE;
		this.errorMessage = "An unexpected error has occured.";
	}
	
	@Override
	public Iterable<Integer> getResults() {
		return iterable;
	}
	
	@Override
	public Status getStatus() {
		return statusCode;
	}
}
