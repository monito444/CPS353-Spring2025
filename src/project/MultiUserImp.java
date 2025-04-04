package project;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit; 
import java.util.concurrent.TimeoutException; 


public class MultiUserImp implements User {
    private final ComputeEngine computeEngineAPI;
    private final DataStorage dataStorageAPI;
    private final ExecutorService executorService;

    public MultiUserImp(ComputeEngine computeEngineAPI, DataStorage dataStorageAPI) {
        this.computeEngineAPI = computeEngineAPI;
        this.dataStorageAPI = dataStorageAPI;
        this.executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
    }

    @Override
    public ComputeResult compute(ComputeRequest request) {
        if (request == null) {
            System.err.println("Invalid argument: Computation cannot take null");
            return ComputeResult.FAILURE;
        }

        try {
            UserInputConfig inputConfig = request.getInputConfig();
            UserOutputConfig outputConfig = request.getOutputConfig(); 

            DataStorageReadResult readResult = dataStorageAPI.read(inputConfig);
            if (readResult == null || readResult.getResults() == null) {
                return ComputeResult.FAILURE;
            }

            List<Future<String>> futures = new ArrayList<>();
            for (Integer input : readResult.getResults()) {
                futures.add(executorService.submit(() -> computeEngineAPI.compute(input)));
            }

            List<String> outputData = new ArrayList<>();
            for (Future<String> future : futures) {
                outputData.add(future.get());
            }

            return ComputeResult.SUCCESS;

        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Computation error: " + e.getMessage());
            return ComputeResult.FAILURE;
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            return ComputeResult.FAILURE;
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
