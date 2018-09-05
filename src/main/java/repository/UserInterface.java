package repository;

import model.User;

import java.sql.SQLException;

public interface UserInterface {
    boolean createUser(User user) throws SQLException;

    boolean signIn(User user) throws SQLException;

    int retrieveUserID(User user) throws SQLException;
}
