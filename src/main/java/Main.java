import controller.ToDoController;
import model.Login;
import model.Registration;
import model.Tasker;
import repository.TaskActions;
import repository.TaskRepository;
import repository.UserRepository;
import view.ToDoView;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        TaskRepository taskRepository = new TaskRepository();
        UserRepository userRepository = new UserRepository();
        ToDoController toDoController = new ToDoController(new Tasker(taskRepository), new Registration(userRepository), new Login(userRepository), new ToDoView());
        toDoController.startApplication();
    }
}
