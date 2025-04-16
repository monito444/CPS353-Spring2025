package project;

import protobuf.ComputeEngine.UserRequest;
import protobuf.ComputeEngine.UserResponse;
import protobuf.ComputeServiceGrpc.ComputeServiceImplBase;
import io.grpc.stub.StreamObserver;

public class UserServiceImp extends ComputeServiceImplBase {
	private User user;
	
	
	public UserServiceImp(DataStorage dataStorage, ComputeEngine computeEngine) {
		user = new UserImp(computeEngine, dataStorage);
	}
	
	public void compute(UserRequest request, StreamObserver<UserResponse> responseObserver) {
		UserInputConfig inputConfig = new UserInputConfigImp(request.getInputFile());
		UserOutputConfig outputConfig = new UserOutputConfigImp(request.getOutputFile());
		
		char delimiter = request.getDelimiter().charAt(0);
		
		ComputeRequest computeRequest = new ComputeRequest(inputConfig, outputConfig, delimiter);
		
		ComputeResult computeResult = user.compute(computeRequest);
		
		//add datastorage process later
		
		UserResponse.ComputeResult status;
		
		if(computeResult == ComputeResult.SUCCESS) {
			status = UserResponse.ComputeResult.success;
		} else {
			status = UserResponse.ComputeResult.failure;
		}
		
		UserResponse userResponse = UserResponse.newBuilder().setStatus(status).build();
		
		responseObserver.onNext(userResponse);
		responseObserver.onCompleted();
	}
}
