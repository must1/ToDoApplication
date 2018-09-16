package view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginView {
    private Logger logger;

    public LoginView() {
        logger = LoggerFactory.getLogger(LoginView.class);
    }

    public void showMessage(String message) {
        logger.info(message);
    }

    public void showError() {
        logger.error("Try again, something went wrong!");
    }

    public void showTypingUsernameAndPasswordMessage() {
        logger.info("Please, put your username and password!");
    }
}
