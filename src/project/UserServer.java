package project;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.protobuf.services.ProtoReflectionService;

public class UserServer {
	private Server server;
	private DataStorage dataStorage = new DataStorageImp();
	private ComputeEngine computeEngine = new ComputeEngineImp();
	
	public static void main(String[] args) throws Exception{
		UserServer server = new UserServer();
		
		server.start();
		server.blockShutdown();
	}
	
	private void start() throws IOException {
		int port = 8080;
		
		ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", port)
										.usePlaintext()
										.build();
		
		server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
					 .addService(new UserServiceImp(dataStorage, computeEngine))
					 .addService(ProtoReflectionService.newInstance())
					 .build()
					 .start();
		
		System.out.println("Server has started. Port " + port);
		
		//add datastorage later
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					if(server != null) {
						server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
					}
				} catch(InterruptedException e) {
					e.printStackTrace(System.err);
				}
			}
		});
	}
	
	private void blockShutdown() throws InterruptedException {
		if(server != null) {
			server.awaitTermination();
		}
	}
}
