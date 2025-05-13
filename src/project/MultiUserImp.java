package project;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
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
    private final ExecutorService cpuService;
    private final BlockingQueue<ComputeRequest> requestQueue;
    private static final int MAX_SPACE = 5;

    public MultiUserImp(ComputeEngine computeEngineAPI, DataStorage dataStorageAPI) {
        this.computeEngineAPI = computeEngineAPI;
        this.dataStorageAPI = dataStorageAPI;
        this.executorService = Executors.newFixedThreadPool(4);
        this.cpuService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        this.requestQueue = new ArrayBlockingQueue<>(MAX_SPACE);
        startUp();
    }

    @Override
    public ComputeResult compute(ComputeRequest request) {
        try {
        	executorService.submit(() -> {
        		try {
        			requestQueue.put(request);
        		} catch (InterruptedException e) {
        			Thread.currentThread().interrupt();
        		}
        	});
        	
        	return ComputeResult.SUCCESS;
        } catch (Exception e) {
        	System.err.println("There was an error when submitting task: " + e.getMessage());
        	return ComputeResult.FAILURE;
        }
    }
    
    private void startUp() {
    	for(int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
    		cpuService.submit(() -> {
    			while(true) {
    				try {
    					ComputeRequest request = requestQueue.poll(1, TimeUnit.SECONDS);
    					if(request != null) {
    						perform(request);
    					} else {
    						System.out.println("No tasks in queue.");
    					}
    				} catch (InterruptedException e) {
    					Thread.currentThread().interrupt();
    					break;
    				}
    			}
    		});
    	}
    }
    
    private void perform(ComputeRequest request) {
    	try {
        	
        	
            UserInputConfig inputConfig = request.getInputConfig();
            UserOutputConfig outputConfig = request.getOutputConfig(); 

            DataStorageReadResult readResult = dataStorageAPI.read(inputConfig);
            if (readResult == null || readResult.getResults() == null) {
                throw new Exception("Something went wrong while reading the input data");
            }

            List<Future<String>> futures = new ArrayList<>();
            for (Integer input : readResult.getResults()) {
                futures.add(executorService.submit(() -> computeEngineAPI.compute(input)));
            }

            List<String> outputData = new ArrayList<>();
            for (Future<String> future : futures) {
                outputData.add(future.get());
            }

            for(String s : outputData) {
            	WriteResult writeResult = dataStorageAPI.appendSingleResult(outputConfig, s, request.getDelimiter());
            	if(writeResult.getStatus() == WriteResult.WriteResultStatus.FAILURE) {
            		throw new Exception("Something went wrong while writing output data");
            	}
            }
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            
        }
    }

    public void shutdown() {
        executorService.shutdown();
        cpuService.shutdown();
    }
}
