package view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToDoView {

    private Logger logger;

    public ToDoView() {
        logger = LoggerFactory.getLogger(ToDoView.class);
    }

    public void executeMainMenu() {
        logger.info("--MAIN MENU--");
        logger.info("1. Sign in");
        logger.info("2. Sign up");
    }

    public void showError() {
        logger.info("Try again");
    }

    public void showMessage(String message) {
        logger.info(message);
    }

    public void showTaskMenu() {
        logger.info("1. Add task");
        logger.info("2. Remove task");
        logger.info("3. Get all tasks");
        logger.info("4. Quit");
    }

    public void showInputingUserAndPasswordMessage() {
        logger.info("Put your username and password");
    }

    public void showInputingTaskMessage() {
        logger.info("Input task");
    }
}
