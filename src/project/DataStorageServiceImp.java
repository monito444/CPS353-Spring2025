package project;

import protobuf.DataStore.DataStorageOutputConfig;
import protobuf.DataStore.DataStorageInputConfig;
import protobuf.DataStore.DataStorageReadResult;
import protobuf.DataStore.DataStorageWriteResult;
import protobuf.DataStorageServiceGrpc.DataStorageServiceImplBase;

import io.grpc.stub.StreamObserver;

public class DataStorageServiceImp extends DataStorageServiceImplBase{
	private final DataStorage dataStorage;
	
	public DataStorageServiceImp() {
		dataStorage = new DataStorageImp();
	}
	
	public void read(DataStorageInputConfig inputConfig, 
						StreamObserver<DataStorageReadResult> responseObserver) {
		DataStorageReadResult.Builder responseBuilder = DataStorageReadResult.newBuilder();
		
		UserInputConfig userInputConfig = new UserInputConfigImp(inputConfig.getFileInputName());
		Iterable<Integer> output = dataStorage.read(userInputConfig).getResults();
		
		if(output == null) {
			responseBuilder.setStatus("Failure").setErrorMessage("There was an error while reading the file.");
		} else {
			responseBuilder.addAllData(output).setStatus("Success");
		}
		
		responseObserver.onNext(responseBuilder.build());
		responseObserver.onCompleted();
	}
	
	public void appendResult(DataStorageOutputConfig outputConfig, 
								StreamObserver<DataStorageWriteResult> responseObserver) {
		DataStorageWriteResult.Builder responseBuilder = DataStorageWriteResult.newBuilder();
		
		UserOutputConfig userOutputConfig = new UserOutputConfigImp(outputConfig.getFileOutputName());
		WriteResult writeResult = dataStorage.appendSingleResult(userOutputConfig, 
									outputConfig.getResult(), outputConfig.getDelimiter().charAt(0));
		
		if(writeResult.getStatus() == WriteResult.WriteResultStatus.FAILURE) {
			responseBuilder.setStatus("Failure").setErrorMessage("There was an error when writing to file.");
		} else {
			responseBuilder.setStatus("Success");
		}
		
		responseObserver.onNext(responseBuilder.build());
		responseObserver.onCompleted();
	}
}
