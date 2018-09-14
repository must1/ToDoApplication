package model;

import repository.UserActions;

import java.sql.SQLException;

public class Login {

    private User connectedUser;
    private UserActions userActions;

    public Login(UserActions userActions) {
        this.userActions = userActions;
    }

    public boolean signIn(String username, String password) throws SQLException {
        connectedUser = new User(username, password);
        if (!userActions.signIn(connectedUser)) {
            return false;
        }
        connectedUser.setID(retrieveConnectedUserID(connectedUser));
        return true;
    }

    private int retrieveConnectedUserID(User connectedUser) throws SQLException {
        return userActions.retrieveUserID(connectedUser);
    }

    public User getConnectedUser() {
        return this.connectedUser;
    }
}
