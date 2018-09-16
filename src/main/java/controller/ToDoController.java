package controller;

import view.ToDoView;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ToDoController {

    private static final int LOGIN = 1;
    private static final int REGISTER = 2;
    private ToDoView toDoView;
    private Scanner input;
    private LoginController loginController;
    private RegistrationController registrationController;
    private TaskManagementController taskManagementController;

    public ToDoController(TaskManagementController taskManagementController, LoginController loginController, RegistrationController registrationController, ToDoView toDoView) {
        this.toDoView = toDoView;
        this.taskManagementController = taskManagementController;
        this.loginController = loginController;
        this.registrationController = registrationController;
        input = new Scanner(System.in);
    }

    public void startApplication() {
        toDoView.executeMainMenu();
        boolean isInvalid = true;
        while (isInvalid) {
            try {
                int option = input.nextInt();
                if (option == 1 || option == 2) {
                    isInvalid = false;
                    switch (option) {
                        case LOGIN:
                            loginController.executeLoginCase();
                            taskManagementController.executeUserCase();
                            break;
                        case REGISTER:
                            registrationController.executeRegistrationCase();
                            break;
                    }
                }
            } catch (InputMismatchException e) {
                toDoView.showError();
                input.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}