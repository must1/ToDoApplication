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


    public void showError(String error) {
        logger.info(error);
    }

    public void showMessage(String message) {
        logger.info(message);
    }
}
