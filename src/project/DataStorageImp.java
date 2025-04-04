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

		String fileName = input != null ? input.getFileName() : null;

	    if (fileName == null || fileName.isEmpty()) {
	        System.err.println("Error: Input file name is missing.");
	        return new DataStorageReadResultImp("Invalid input configuration.");
	    }


	    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            for (String numStr : line.split(",")) {
	                try {
	                    double parsedValue = Double.parseDouble(numStr.trim());

	                    // Convert float/double to integer (rounding down)
	                    numbers.add((int) parsedValue);
	                } catch (NumberFormatException e) {
	                	System.err.println("Invalid data format: " + numStr + " is not a valid number.");
	                	return new DataStorageReadResultImp("Invalid data format: " + numStr + " is not a valid number.");
	                }
	            }
	        }
	        return new DataStorageReadResultImp(numbers); // Success case
	    } catch (IOException e) {
	        System.err.println("Error reading file: " + fileName);
	        return new DataStorageReadResultImp("Error reading file: " + fileName);
	    }

	}


	@Override
	public WriteResult appendSingleResult(UserOutputConfig output, String result, char delimiter) {
 

		String fileName = output != null ? output.getFileName() : null;

		if (fileName == null || fileName.isEmpty()) {
			System.err.println("Error: Output file name is missing.");
			return () -> WriteResult.WriteResultStatus.FAILURE;
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
			writer.write(result + delimiter);
			writer.newLine();
			return () -> WriteResult.WriteResultStatus.SUCCESS;
		} catch (IOException e) {
			System.err.println("Error writing to file: " + fileName);
			return () -> WriteResult.WriteResultStatus.FAILURE;
		}
	}
}
