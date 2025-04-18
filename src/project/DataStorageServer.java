package project;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;

public class DataStorageServer {
	private Server server;
	
	public static void main(String[] args) throws Exception{
		DataStorageServer dataStorageServer = new DataStorageServer();
		dataStorageServer.startServer();
		dataStorageServer.blockUntilShutdown();
	}
	
	public void startServer() throws IOException {
		int port = 8082;
		server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
				 .addService(new DataStorageServiceImp())
				 .build()
				 .start();
		
		System.out.println("Server has started on port " + port);
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				System.err.print(isAlive());
				
				try {
					if(server != null) {
						server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
					}
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
				System.err.println("Server has shutdown.");
			}
		});
	}
	
	public void blockUntilShutdown() throws InterruptedException {
		if(server != null) {
			server.awaitTermination();
		}
	}
}
