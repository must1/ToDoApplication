package controller;

import model.Task;
import model.User;
import repository.TaskActions;

import java.sql.SQLException;
import java.util.List;

public class TaskController {
    private TaskActions taskActions;

    public TaskController(TaskActions taskActions) {
        this.taskActions = taskActions;
    }

    void addTask(String taskName, User connectedUser) throws SQLException {
        taskActions.addTask(new Task(taskName), connectedUser);
    }

    void deleteTask(String taskName, User connectedUser) throws SQLException {
        taskActions.deleteTask(new Task(taskName), connectedUser);
    }

    List<Task> getTasks(User connectedUser) throws SQLException {
        return taskActions.getTasks(connectedUser);
    }
}
