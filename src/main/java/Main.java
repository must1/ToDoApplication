import controller.ToDoEngine;
import repository.TaskRepository;
import repository.UserRepository;
import view.ToDoView;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        UserRepository userRepository = new UserRepository();
        TaskRepository taskRepository = new TaskRepository();
        ToDoEngine toDoEngine = new ToDoEngine(userRepository,taskRepository);
        ToDoView toDoView = new ToDoView(toDoEngine);
        toDoView.executeMainMenu();
    }
}
