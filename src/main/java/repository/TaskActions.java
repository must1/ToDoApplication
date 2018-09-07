package repository;

import model.Task;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface TaskActions {

    boolean addTask(Task task, User user) throws SQLException;

    boolean deleteTask(Task task, User user) throws SQLException;

    List<Task> getTasks(User user) throws SQLException;
}
