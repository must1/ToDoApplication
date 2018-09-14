package controller;

import model.Task;
import model.User;
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
    private TaskActions taskActions;
    private UserActions userActions;
    private User connectedUser;
    private ToDoView toDoView;
    private Scanner input;
    private int option;
    private TaskController taskController;

    public ToDoController(UserActions userStorage, TaskActions taskStorage, ToDoView toDoView) {
        this.taskActions = taskStorage;
        this.userActions = userStorage;
        this.toDoView = toDoView;
        input = new Scanner(System.in);
        taskController = new TaskController(taskActions);
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
                toDoView.showError("Try again");
                input.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private void executeUserOptions(int option) throws SQLException {
        switch (option) {
            case 1:
                toDoView.showMessage("Input task");
                String taskName = input.next();
                taskController.addTask(taskName, connectedUser);
                break;
            case 2:
                toDoView.showMessage("Input task");
                taskName = input.next();
                taskController.deleteTask(taskName, connectedUser);
                break;
            case 3:
                List<Task> tasks = taskController.getTasks(connectedUser);
                for (Task task : tasks)
                    toDoView.showMessage(String.valueOf(task));
                break;
        }
    }

    private void executeUserCase() throws SQLException {
        do {
            toDoView.showMessage("1. Add task");
            toDoView.showMessage("2. Remove task");
            toDoView.showMessage("3. Get all tasks");
            toDoView.showMessage("4. Quit");
            option = input.nextInt();
            executeUserOptions(option);
        }
        while (option != 4);
    }

    private void executeRegistrationCase() throws SQLException {
        String username, password;
        toDoView.showMessage("Put your username and password");
        toDoView.showMessage("User: ");
        username = input.next();
        toDoView.showMessage("Password: ");
        password = input.next();
        if (createUser(username, password))
            toDoView.showMessage("User created successfully, now you can sign in!");
    }

    private void executeLoginCase() throws SQLException {
        String username, password;
        boolean isInvalid = true;
        do {
            toDoView.showMessage("Put your username and password");
            toDoView.showMessage("User: ");
            username = input.next();
            toDoView.showMessage("Password: ");
            password = input.next();
            if (signIn(username, password)) {
                toDoView.showMessage("You've logged in");
                executeUserCase();
                isInvalid = false;
            } else
                toDoView.showMessage("Bad login or password, try again");
        }
        while (isInvalid);
    }

    boolean signIn(String username, String password) throws SQLException {
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

    boolean createUser(String username, String password) throws SQLException {
        return userActions.createUser(new User(username, password));
    }
}