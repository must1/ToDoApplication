package model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.UserActions;

import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginTest {

    @Mock
    UserActions userActions;
    private Login login;
    private Registration registration;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        login = new Login(userActions);
        registration = new Registration(userActions);
    }

    @Test
    public void signIn() throws SQLException {
        User user = new User("admin", "123");
        registration.createUser("admin", "123");
        when(userActions.signIn(user)).thenReturn(true);
        verify(userActions).createUser(user);
        login.signIn("admin","123");
        verify(userActions).signIn(user);
        assertTrue(login.signIn("admin","123"));
    }
}