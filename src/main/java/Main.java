import controller.ToDoController;
import repository.TaskRepository;
import repository.UserRepository;
import view.ToDoView;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        UserRepository userRepository = new UserRepository();
        TaskRepository taskRepository = new TaskRepository();
        ToDoView toDoView = new ToDoView();
        ToDoController toDoController = new ToDoController(userRepository,taskRepository,toDoView);
        toDoController.startApplication();
    }
}
