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

		if (input == null) {
			return new DataStorageReadResultImp(numbers, false);
		}

		try (BufferedReader br = new BufferedReader(new FileReader(input.getFileName()))) {
			String line;
			while ((line = br.readLine()) != null) {
				try {
					String[] numArray = line.split(",");
					for (String num : numArray) {
						numbers.add(Integer.parseInt(num.trim()));
					}
				} catch (NumberFormatException e) {
					System.err.println("Skipping invalid number: " + line);
				}
			}
			return new DataStorageReadResultImp(numbers, true);
		} catch (IOException e) {
			System.err.println("Error reading file: " + e.getMessage());
			return new DataStorageReadResultImp(numbers, false);
		}
	}

	@Override
	public WriteResult appendSingleResult(UserOutputConfig output, String result, char delimiter) {
		if (output == null || result == null) {
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
