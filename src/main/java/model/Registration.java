package model;

import repository.UserActions;

import java.sql.SQLException;

public class Registration {
    private UserActions userActions;

    public Registration(UserActions userActions) {
        this.userActions = userActions;
    }

    public boolean createUser(String username, String password) throws SQLException {
        return userActions.createUser(new User(username, password));
    }
}
