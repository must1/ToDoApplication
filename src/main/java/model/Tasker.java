package model;

import repository.TaskActions;

import java.sql.SQLException;
import java.util.List;

public class Tasker {
    private TaskActions taskActions;

    public Tasker(TaskActions taskActions) {
        this.taskActions = taskActions;
    }

    public void addTask(String taskName, User connectedUser) throws SQLException {
        taskActions.addTask(new Task(taskName), connectedUser);
    }

    public void deleteTask(String taskName, User connectedUser) throws SQLException {
        taskActions.deleteTask(new Task(taskName), connectedUser);
    }

    public List<Task> getTasks(User connectedUser) throws SQLException {
        return taskActions.getTasks(connectedUser);
    }
}
