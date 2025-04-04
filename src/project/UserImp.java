package project;

import java.util.ArrayList;
import java.util.List;

public class UserImp implements User {
    private final ComputeEngine computeEngineAPI;
    private final DataStorage dataStorageAPI;

    public UserImp(ComputeEngine computeEngineAPI, DataStorage dataStorageAPI) {
        this.computeEngineAPI = computeEngineAPI;
        this.dataStorageAPI = dataStorageAPI;
    }

    @Override
    public ComputeResult compute(ComputeRequest request) {
        try {
            if (request == null) {
                throw new IllegalArgumentException("Computation Cannot Take Null");
            }

            UserInputConfig inputConfiguration = request.getInputConfig();
            UserOutputConfig outputConfig = request.getOutputConfig();
            
            DataStorageReadResult readResult = dataStorageAPI.read(inputConfiguration);
            
            if (readResult == null) {
                return ComputeResult.FAILURE;
            }

            Iterable<Integer> integer = readResult.getResults();
            if (integer == null) {
                return ComputeResult.FAILURE;
            }

            List<String> outputData = new ArrayList<>();
            for (Integer i : integer) {
                outputData.add(computeEngineAPI.compute(i));
            }
            for (String s : outputData) {
                WriteResult writeResult = dataStorageAPI.appendSingleResult(outputConfig, s, request.getDelimiter());
                if (writeResult.getStatus() == WriteResult.WriteResultStatus.FAILURE) {
                    return ComputeResult.FAILURE;
                }
            }
            
            return ComputeResult.SUCCESS;
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid argument: " + e.getMessage());
            return ComputeResult.FAILURE;
        } catch (Exception e) { // Catching all unexpected runtime exceptions
            System.err.println("Unexpected Error: " + e.getMessage());
            return ComputeResult.FAILURE;
        }
    }
}
