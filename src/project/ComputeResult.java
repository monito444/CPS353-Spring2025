package project;

public interface ComputeResult {
	static ComputeResult SUCCESS = new ComputeResult() {
		
		@Override
		public ComputeResultStatus getStatus() {
			return ComputeResultStatus.SUCCESS;
		}
		
		@Override
		public String getFailureMethod() {
			return "";
		}
	};
	static ComputeResult FAILURE = new ComputeResult() {
		
		@Override
		public ComputeResultStatus getStatus() {
			return ComputeResultStatus.FAILURE;
		}
		
		@Override
		public String getFailureMethod() {
			return "Something went wrong";
		}
	};
	
	ComputeResultStatus getStatus();
	String getFailureMethod();
	
	public static enum ComputeResultStatus {
		SUCCESS(true),
		INVALID_REQUEST(false),
		FAILURE(false);
		
		private final boolean success;
		
		private ComputeResultStatus(boolean success) {
			this.success = success;
		}
		
		public boolean isSuccess() {
			return success;
		}
	}
}
