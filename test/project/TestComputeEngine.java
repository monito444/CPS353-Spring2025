package project;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import project.DataStorage;
import project.User;

public class TestComputeEngine {
    DataStorage mockDataStorage = Mockito.mock(DataStorage.class);
    User mockUser = Mockito.mock(User.class);

}
