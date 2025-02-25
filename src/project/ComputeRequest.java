package project;

public class ComputeRequest {
	private static final char DEFAULT_DELIMITER = ';';

	private final UserInputConfig inputConfig;
	private final UserOutputConfig outputConfig;
	private final char delimiter;
	
	public ComputeRequest(UserInputConfig inputConfig, UserOutputConfig outputConfig) {
		this(inputConfig, outputConfig, DEFAULT_DELIMITER); // default delimiter: ;
	}
	
	public ComputeRequest(UserInputConfig inputConfig, UserOutputConfig outputConfig, char delimiter) {
		this.inputConfig = inputConfig;
		this.outputConfig = outputConfig;
		this.delimiter = delimiter;
	}
	
	public char getDelimiter() {
		return delimiter;
	}

	public UserInputConfig getInputConfig() {
		return inputConfig;
	}

	public UserOutputConfig getOutputConfig() {
		return outputConfig;
	}

    @Override
    public String toString() {
        return "ComputeRequest [inputConfig=" + inputConfig + ", outputConfig=" + outputConfig + ", delimiter="
                + delimiter + "]";
    }
}
