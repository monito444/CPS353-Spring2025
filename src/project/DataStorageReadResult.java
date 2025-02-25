package project;

import javax.annotation.Nullable;

public interface DataStorageReadResult {
	public static enum Status {
		SUCCESS,
		FAILURE;
	}
	
	@Nullable
	public Iterable<Integer> getResults();
	
	public Status getStatus();
}
