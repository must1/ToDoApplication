package controller;

import model.*;
import repository.TaskActions;
import repository.UserActions;
import view.ToDoView;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ToDoController {

    private static final int LOGIN = 1;
    private static final int REGISTER = 2;
    private ToDoView toDoView;
    private Scanner input;
    private int option;
    private Login modelOfLogin;
    private Registration modelOfRegistration;
    private Tasker modelOfTasker;


    public ToDoController(Tasker tasker, Registration registration, Login login, ToDoView toDoView) {
        this.toDoView = toDoView;
        input = new Scanner(System.in);
        this.modelOfLogin = login;
        this.modelOfTasker = tasker;
        this.modelOfRegistration = registration;
    }

    public void startApplication() {
        toDoView.executeMainMenu();
        boolean isInvalid = true;
        while (isInvalid) {
            try {
                option = input.nextInt();
                if (option == 1 || option == 2) {
                    isInvalid = false;
                    switch (option) {
                        case LOGIN:
                            executeLoginCase();
                            break;
                        case REGISTER:
                            executeRegistrationCase();
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

    private void executeUserOptions(int option) throws SQLException {
        switch (option) {
            case 1:
                toDoView.showInputingTaskMessage();
                String taskName = input.next();
                modelOfTasker.addTask(taskName, modelOfLogin.getConnectedUser());
                break;
            case 2:
                toDoView.showInputingTaskMessage();
                taskName = input.next();
                modelOfTasker.deleteTask(taskName, modelOfLogin.getConnectedUser());
                break;
            case 3:
                List<Task> tasks = modelOfTasker.getTasks(modelOfLogin.getConnectedUser());
                for (Task task : tasks)
                    toDoView.showMessage(String.valueOf(task));
                break;
        }
    }

    private void executeUserCase() throws SQLException {
        do {
            toDoView.showTaskMenu();
            option = input.nextInt();
            executeUserOptions(option);
        }
        while (option != 4);
    }

    private void executeRegistrationCase() throws SQLException {
        String username, password;
        toDoView.showInputingUserAndPasswordMessage();
        toDoView.showMessage("User: ");
        username = input.next();
        toDoView.showMessage("Password: ");
        password = input.next();
        if (modelOfRegistration.createUser(username, password))
            toDoView.showMessage("User created successfully, now you can sign in!");
    }

    private void executeLoginCase() throws SQLException {
        String username, password;
        boolean isInvalid = true;
        do {
            toDoView.showInputingUserAndPasswordMessage();
            toDoView.showMessage("User: ");
            username = input.next();
            toDoView.showMessage("Password: ");
            password = input.next();
            if (modelOfLogin.signIn(username, password)) {
                toDoView.showMessage("You've logged in");
                executeUserCase();
                isInvalid = false;
            } else
                toDoView.showError();
        }
        while (isInvalid);
    }
}