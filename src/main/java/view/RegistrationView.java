package view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationView {
    private Logger logger;

    public RegistrationView() {
        logger = LoggerFactory.getLogger(RegistrationView.class);
    }

    public void showMessage(String message) {
        logger.info(message);
    }

    public void showTypingUsernameAndPasswordMessage() {
        logger.info("Please, put your username and password!");
    }
}
