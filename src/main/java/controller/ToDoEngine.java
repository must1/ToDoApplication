package controller;

import model.Task;
import model.User;
import repository.TaskInterface;
import repository.UserInterface;

import java.sql.SQLException;
import java.util.List;

public class ToDoEngine {
    private TaskInterface taskStorage;
    private UserInterface userStorage;
    private User connectedUser;

    public ToDoEngine(UserInterface userStorage, TaskInterface taskStorage) {
        this.taskStorage = taskStorage;
        this.userStorage = userStorage;
    }

    public boolean signIn(String username, String password) throws SQLException {
        connectedUser = new User(username, password);
        if (!userStorage.signIn(connectedUser))
            return false;
        connectedUser.setID(retrieveConnectedUserID(connectedUser));
        return true;
    }

    private int retrieveConnectedUserID(User connectedUser) throws SQLException {
        return userStorage.retrieveUserID(connectedUser);
    }

    public boolean registerUser(String username, String password) throws SQLException {
        User user = new User(username, password);
        return userStorage.createUser(user);
    }

    public void addTask(String taskName) throws SQLException {
        taskStorage.addTask(new Task(taskName), connectedUser);
    }


    public void deleteTask(String taskName) throws SQLException {
        taskStorage.deleteTask(new Task(taskName), connectedUser);
    }

    public List<Task> getTasks() throws SQLException {
        return taskStorage.getTasks(connectedUser);
    }
}
