package repository;

import model.User;

import java.sql.*;
import java.util.TimeZone;

public class UserRepository implements UserInterface {
    private Connection connection;

    public UserRepository() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp?autoReconnect=true&serverTimezone=" + TimeZone.getDefault().getID(), "root", "9234355q");
    }

    public boolean createUser(User user) throws SQLException {
        if (doesUserWithThatUsernameExist(user))
            return false;
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT into user (username,password) values (?,?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.executeUpdate();

        return true;
    }

    public boolean signIn(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(username) FROM user WHERE username=? AND password=? LIMIT 0,1");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        ResultSet result = preparedStatement.executeQuery();
        int count = 0;
        if (result.next())
            count = result.getInt(1);

        if (count < 1)
            return false;
        return true;
    }

    public int retrieveUserID(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM user WHERE username=? AND password=?");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        ResultSet result = preparedStatement.executeQuery();
        if (result.next())
            user.setID(result.getInt("id"));

        return user.getID();
    }

    private boolean doesUserWithThatUsernameExist(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT username FROM user WHERE username=?");
        preparedStatement.setString(1, user.getName().toUpperCase());
        ResultSet result = preparedStatement.executeQuery();

        return result.next();
    }


}
