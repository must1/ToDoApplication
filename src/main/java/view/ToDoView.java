package view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToDoView {
    private Logger logger;

    public ToDoView() {
        logger = LoggerFactory.getLogger(ToDoView.class);
    }

    public void showError() {
        logger.error("Try again, something went wrong!");
    }

    public void executeMainMenu() {
        logger.info("--MAIN MENU--");
        logger.info("1. Sign in");
        logger.info("2. Sign up");
    }
}
