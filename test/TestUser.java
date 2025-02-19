import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import src.project.User;
import src.project.PrototypeUser;

class TestUser {

	@Test
	public void testPrototype() throws Exception{
		User mockUser = Mockito.mock(User.class);
		
		PrototypeUser testUser = new PrototypeUser(mockUser);
	}

}
