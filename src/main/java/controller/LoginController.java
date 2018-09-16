package controller;

import model.Login;
import view.LoginView;

import java.sql.SQLException;
import java.util.Scanner;

public class LoginController {

    private Login modelOfLogin;
    private LoginView view;
    private Scanner input;

    public LoginController(Login login, LoginView view) {
        this.view = view;
        this.modelOfLogin = login;
        input = new Scanner(System.in);
    }

    void executeLoginCase() throws SQLException {
        String username, password;
        boolean isInvalid = true;
        do {
            view.showTypingUsernameAndPasswordMessage();
            view.showMessage("User: ");
            username = input.next();
            view.showMessage("Password: ");
            password = input.next();
            if (modelOfLogin.signIn(username, password)) {
                view.showMessage("You've logged in");
                isInvalid = false;
            } else
                view.showError();
        }
        while (isInvalid);
    }
}
