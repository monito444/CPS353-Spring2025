package project;

public class ComputeResultImp implements ComputeResult{
	private final ComputeResultStatus status;
	private final String failureMessage;
	
	public ComputeResultImp(ComputeResultStatus status, String failureMessage) {
		this.status = status;
		this.failureMessage = failureMessage;
	}
	
	@Override
	public ComputeResultStatus getStatus() {
		return status;
	}
	
	@Override
	public String getFailureMethod() {
		return failureMessage;
	}
}
