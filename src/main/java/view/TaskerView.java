package view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskerView {
    private Logger logger;

    public TaskerView() {
        logger = LoggerFactory.getLogger(TaskerView.class);
    }

    public void showMessage(String message) {
        logger.info(message);
    }

    public void showInputingTaskMessage() {
        logger.info("Input task");
    }

    public void showTaskMenu() {
        logger.info("1. Add task");
        logger.info("2. Remove task");
        logger.info("3. Get all tasks");
        logger.info("4. Quit");
    }
}
