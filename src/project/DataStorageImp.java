package project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataStorageImp implements DataStorage {

	@Override
	public DataStorageReadResult read(UserInputConfig input) {
		List<Integer> numbers = new ArrayList<>();

		// Ensure the input is not null
		if (input == null || input.getFileName() == null || input.getFileName().isEmpty()) {
			System.err.println("Error: Input file name is missing.");
			return new DataStorageReadResultImp("Invalid input configuration.");
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(input.getFileName()))) {
			String line;
			while ((line = reader.readLine()) != null) {
				for (String numStr : line.split(",")) {  // Assuming comma-separated values
					try {
						numbers.add(Integer.parseInt(numStr.trim()));
					} catch (NumberFormatException e) {
						System.err.println("Skipping invalid number: " + numStr);
					}
				}
			}
			return new DataStorageReadResultImp(numbers); // Success case
		} catch (IOException e) {
			System.err.println("Error reading file: " + input.getFileName());
			return new DataStorageReadResultImp("Error reading file: " + input.getFileName());
		}
	}

	@Override
	public WriteResult appendSingleResult(UserOutputConfig output, String result, char delimiter) {
		// Ensure the output is valid
		if (output == null || output.getFileName() == null || output.getFileName().isEmpty()) {
			System.err.println("Error: Output file name is missing.");
			return () -> WriteResult.WriteResultStatus.FAILURE;
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(output.getFileName(), true))) {
			writer.write(result + delimiter);
			writer.newLine();
			return () -> WriteResult.WriteResultStatus.SUCCESS;
		} catch (IOException e) {
			System.err.println("Error writing to file: " + output.getFileName());
			return () -> WriteResult.WriteResultStatus.FAILURE;
		}
	}
}
