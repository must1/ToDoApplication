package controller;

import model.Task;
import model.User;
import repository.TaskActions;
import repository.UserActions;

import java.sql.SQLException;
import java.util.List;

public class ToDoEngine {

    private TaskActions taskStorage;
    private UserActions userStorage;
    private User connectedUser;

    public ToDoEngine(UserActions userStorage, TaskActions taskStorage) {
        this.taskStorage = taskStorage;
        this.userStorage = userStorage;
    }

    public boolean signIn(String username, String password) throws SQLException {
        connectedUser = new User(username, password);
        if (!userStorage.signIn(connectedUser)) {
            return false;
        }
        connectedUser.setID(retrieveConnectedUserID(connectedUser));
        return true;
    }

    private int retrieveConnectedUserID(User connectedUser) throws SQLException {
        return userStorage.retrieveUserID(connectedUser);
    }

    public boolean createUser(String username, String password) throws SQLException {
        return userStorage.createUser(new User(username, password));
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
