package view;

import controller.ToDoEngine;
import model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class ToDoView {

    private static final int LOGIN = 1;
    private static final int REGISTER = 2;
    private Logger logger;
    private ToDoEngine toDoEngine;
    private Scanner input;
    private int option;

    public ToDoView(ToDoEngine toDoEngine) {
        this.toDoEngine = toDoEngine;
        logger = LoggerFactory.getLogger(ToDoView.class);
        input = new Scanner(System.in);
    }

    public void executeMainMenu() {
        logger.info("--MAIN MENU--");
        logger.info("1. Sign in");
        logger.info("2. Sign up");
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
                logger.error("Try again");
                input.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void executeLoginCase() throws SQLException {
        String username, password;
        boolean isInvalid = true;
        do {
            logger.info("Put your username and password");
            logger.info("User: ");
            username = input.next();
            logger.info("Password: ");
            password = input.next();
            if (toDoEngine.signIn(username, password)) {
                logger.info("You've logged in");
                executeUserCase();
                isInvalid = false;
            } else
                logger.info("Bad login or password, try again");
        }
        while (isInvalid);
    }

    private void executeRegistrationCase() throws SQLException {
        String username, password;
        logger.info("Put your username and password");
        logger.info("User: ");
        username = input.next();
        logger.info("Password: ");
        password = input.next();
        if (toDoEngine.createUser(username, password))
            logger.info("User created successfully, now you can sign in!");
    }

    private void executeUserCase() throws SQLException {
        do {
            logger.info("1. Add task");
            logger.info("2. Remove task");
            logger.info("3. Get all tasks");
            logger.info("4. Quit");
            option = input.nextInt();
            executeUserOptions(option);
        }
        while (option != 4);
    }

    private void executeUserOptions(int option) throws SQLException {
        switch (option) {
            case 1:
                logger.info("Input task");
                String taskName = input.next();
                toDoEngine.addTask(taskName);
                break;
            case 2:
                logger.info("Input task");
                taskName = input.next();
                toDoEngine.deleteTask(taskName);
                break;
            case 3:
                List<Task> tasks = toDoEngine.getTasks();
                for (Task task : tasks)
                    logger.info(String.valueOf(task));
                break;
        }
    }
}
