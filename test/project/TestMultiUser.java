package project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMultiUser {
	
	// TODO 1: change the type of this variable to the name you're using for your @NetworkAPI
	// interface
	private User coordinator;
	
	@BeforeEach
	public void initializeComputeEngine() {
		//TODO 2: create an instance of the implementation of your @NetworkAPI; this is the component
		// that the user will make requests to
		// Store it in the 'coordinator' instance variable
		DataStorage storage = new DataStorageImp();
		ComputeEngine engine = new ComputeEngineImp();
		
		coordinator = new MultiUserImp(engine, storage);
	}

	@Test
	public void compareMultiAndSingleThreaded() throws Exception {
		int numThreads = 4;
		List<TestUserThread> testUsers = new ArrayList<>();
		for (int i = 0; i < numThreads; i++) {
			testUsers.add(new TestUserThread(coordinator));
		}
		
		// Run single threaded
		String singleThreadFilePrefix = "testMultiUser.compareMultiAndSingleThreaded.test.singleThreadOut.tmp";
		for (int i = 0; i < numThreads; i++) {
			File singleThreadedOut = 
					new File(singleThreadFilePrefix + i);
			if(singleThreadedOut.exists() == false) {
				Path fileSinglePath = Paths.get(singleThreadFilePrefix + i);
				Files.createFile(fileSinglePath);
			}
			singleThreadedOut.deleteOnExit();
			testUsers.get(i).run(singleThreadedOut.getCanonicalPath());
		}
		
		// Run multi threaded
		ExecutorService threadPool = Executors.newCachedThreadPool();
		List<Future<?>> results = new ArrayList<>();
		String multiThreadFilePrefix = "testMultiUser.compareMultiAndSingleThreaded.test.multiThreadOut.tmp";
		for (int i = 0; i < numThreads; i++) {
			File multiThreadedOut = 
					new File(multiThreadFilePrefix + i);
			if(multiThreadedOut.exists() == false) {
				Path fileMultiPath = Paths.get(multiThreadFilePrefix + i);
				Files.createFile(fileMultiPath);
			}
			multiThreadedOut.deleteOnExit();
			String multiThreadOutputPath = multiThreadedOut.getCanonicalPath();
			TestUserThread testUser = testUsers.get(i);
			results.add(threadPool.submit(() -> testUser.run(multiThreadOutputPath)));
		}
		
		results.forEach(future -> {
			try {
				future.get();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
		
		
		// Check that the output is the same for multi-threaded and single-threaded
		List<String> singleThreaded = loadAllOutput(singleThreadFilePrefix, numThreads);
		List<String> multiThreaded = loadAllOutput(multiThreadFilePrefix, numThreads);
		
		singleThreaded.sort(String::compareTo);
		multiThreaded.sort(String::compareTo);
		Assert.assertEquals(singleThreaded, multiThreaded);
	}

	private List<String> loadAllOutput(String prefix, int numThreads) throws IOException {
		List<String> result = new ArrayList<>();
		for (int i = 0; i < numThreads; i++) {
			File multiThreadedOut = 
					new File(prefix + i);
			result.addAll(Files.readAllLines(multiThreadedOut.toPath()));
		}
		return result;
	}
}
