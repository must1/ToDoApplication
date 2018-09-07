package controller;

import model.Task;
import model.User;
import repository.TaskActions;
import repository.UserActions;

import java.sql.SQLException;
import java.util.List;

public class ToDoEngine {

    private TaskActions taskActions;
    private UserActions userActions;
    private User connectedUser;

    public ToDoEngine(UserActions userStorage, TaskActions taskStorage) {
        this.taskActions = taskStorage;
        this.userActions = userStorage;
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

    public boolean createUser(String username, String password) throws SQLException {
        return userActions.createUser(new User(username, password));
    }

    public void addTask(String taskName) throws SQLException {
        taskActions.addTask(new Task(taskName), connectedUser);
    }

    public void deleteTask(String taskName) throws SQLException {
        taskActions.deleteTask(new Task(taskName), connectedUser);
    }

    public List<Task> getTasks() throws SQLException {
        return taskActions.getTasks(connectedUser);
    }
}
