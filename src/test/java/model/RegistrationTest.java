package model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.UserActions;

import java.sql.SQLException;

import static org.mockito.Mockito.verify;

public class RegistrationTest {

    private Registration registration;
    @Mock
    private UserActions userActionsMock;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        registration = new Registration(userActionsMock);
    }

    @Test
    public void createUser() throws SQLException {
        User user = new User("admin", "123");

        registration.createUser("admin", "123");

        verify(userActionsMock).createUser(user);
    }
}