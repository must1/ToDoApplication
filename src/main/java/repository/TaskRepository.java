package repository;

import model.Task;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class TaskRepository implements TaskInterface {
    private Connection connection;

    public TaskRepository() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp?autoReconnect=true&serverTimezone=" + TimeZone.getDefault().getID(), "root", "9234355q");
    }

    public boolean addTask(Task task, User user) throws SQLException {
        if (task == null)
            return false;
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT into tasks (task,idUser) values (?,?) ");
        preparedStatement.setString(1, task.getTaskName());
        preparedStatement.setInt(2, user.getID());
        preparedStatement.executeUpdate();

        return true;
    }

    public boolean deleteTask(Task task, User user) throws SQLException {
        if (!doesTaskExists(task))
            return false;
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE from tasks WHERE idUser=? AND task=?");
        preparedStatement.setInt(1, user.getID());
        preparedStatement.setString(2, task.getTaskName());
        preparedStatement.executeUpdate();

        return true;
    }

    public List<Task> getTasks(User connectedUser) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tasks where idUser=?");
        preparedStatement.setInt(1, connectedUser.getID());
        ResultSet result = preparedStatement.executeQuery();
        List<Task> tasks = new ArrayList<Task>();
        while (result.next()) {
            Task task = new Task();
            task.setTaskName(result.getString("task"));

            tasks.add(task);
        }
        return tasks;
    }



    public boolean doesTaskExists(Task task) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT task FROM tasks WHERE task=?");
        preparedStatement.setString(1, task.getTaskName());
        ResultSet result = preparedStatement.executeQuery();
        return result.next();
    }
}

