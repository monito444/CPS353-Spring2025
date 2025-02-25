package project;

import project.annotations.ProcessAPI;

@ProcessAPI
public interface DataStorage{
	DataStorageReadResult read(UserInputConfig input);
	WriteResult appendSingleResult(UserOutputConfig output, String result, char delimiter);
}
