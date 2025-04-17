package project;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import protobuf.ComputeEngine.UserRequest;
import protobuf.ComputeEngine.UserResponse;
import protobuf.ComputeServiceGrpc;
import protobuf.ComputeServiceGrpc.ComputeServiceBlockingStub;
import io.grpc.Channel;
import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;

public class UserClient {
	public static String inputFile, outputFile, delimiter;
	public final ComputeServiceBlockingStub blockingStub;
	
	public UserClient(Channel channel) {
		blockingStub = ComputeServiceGrpc.newBlockingStub(channel);
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println("Enter file name: ");
		Scanner userInput = new Scanner(System.in);
		inputFile = userInput.nextLine();
		
		System.out.println("Enter name for output file: ");
		outputFile = userInput.nextLine();
		
		System.out.println("Enter a delimiter: ");
		delimiter = userInput.nextLine();
		
		userInput.close();
		
		ManagedChannel managedChannel = 
				Grpc.newChannelBuilder("localhost:8080", InsecureChannelCredentials.create())
				.build();
		
		try {
			UserClient client = new UserClient(managedChannel);
		} catch(StatusRuntimeException e) {
			e.printStackTrace();
		} finally {
			managedChannel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
		}
	}
	
	public void makeRequest() {
		UserRequest request = UserRequest.newBuilder()
								.setInputFile(inputFile)
								.setOutputFile(outputFile)
								.setDelimiter(delimiter)
								.build();
		
		UserResponse response;
		
		try {
			response = blockingStub.compute(request);
		} catch(StatusRuntimeException e) {
			e.printStackTrace();
			return;
		}
	}
}
