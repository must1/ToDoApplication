import controller.LoginController;
import controller.RegistrationController;
import controller.TaskManagementController;
import controller.ToDoController;
import model.Login;
import model.Registration;
import model.Tasker;
import repository.TaskActions;
import repository.TaskRepository;
import repository.UserActions;
import repository.UserRepository;
import view.LoginView;
import view.RegistrationView;
import view.TaskerView;
import view.ToDoView;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        TaskRepository taskRepository = new TaskRepository();
        UserRepository userRepository = new UserRepository();

        ToDoView toDoView = new ToDoView();

        Login login = new Login(userRepository);

        ToDoController toDoController = new ToDoController(createTaskManagementController(taskRepository, login), createLoginController(login), createRegistrationController(userRepository), toDoView);
        toDoController.startApplication();
    }

    private static LoginController createLoginController(Login login) {
        return new LoginController(login, new LoginView());
    }

    private static RegistrationController createRegistrationController(UserActions userActions) {
        return new RegistrationController(new Registration(userActions), new RegistrationView());
    }

    private static TaskManagementController createTaskManagementController(TaskActions taskActions, Login login) {
        return new TaskManagementController(new Tasker(taskActions), new TaskerView(), login);
    }
}
