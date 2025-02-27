package project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataStorageImp implements DataStore {

	@Override
	public Iterable<Integer> read(InputConfig input) {
		List<Integer> numbers = new ArrayList<>();

		if (input == null || input.getFileName() == null) {
			System.err.println("Invalid input configuration.");
			return numbers; // Return empty list instead of null to avoid NullPointerException
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(input.getFileName()))) {
			String line;
			while ((line = reader.readLine()) != null) {
				for (String numStr : line.split(",")) {
					try {
						numbers.add(Integer.parseInt(numStr.trim())); // Trim spaces to prevent errors
					} catch (NumberFormatException e) {
						System.err.println("Skipping invalid number: " + numStr);
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Error reading file: " + input.getFileName());
		}

		return numbers;
	}

	@Override
	public WriteResult appendSingleResult(OutputConfig output, String result) {
		if (output == null || output.getFileName() == null) {
			System.err.println("Invalid output configuration.");
			return () -> WriteResult.WriteResultStatus.FAILURE;
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(output.getFileName(), true))) {
			writer.write(result);
			writer.newLine();
			return () -> WriteResult.WriteResultStatus.SUCCESS;
		} catch (IOException e) {
			System.err.println("Error writing to file: " + output.getFileName());
			return () -> WriteResult.WriteResultStatus.FAILURE;
		}
	}
}
