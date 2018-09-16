package controller;

import model.Registration;
import view.RegistrationView;

import java.sql.SQLException;
import java.util.Scanner;

public class RegistrationController {

    private RegistrationView view;
    private Scanner input;
    private Registration modelOfRegistration;

    public RegistrationController(Registration modelOfRegistration, RegistrationView view) {
        this.view = view;
        this.modelOfRegistration = modelOfRegistration;
        input = new Scanner(System.in);
    }

    void executeRegistrationCase() throws SQLException {
        String username, password;
        view.showTypingUsernameAndPasswordMessage();
        view.showMessage("User: ");
        username = input.next();
        view.showMessage("Password: ");
        password = input.next();
        if (modelOfRegistration.createUser(username, password))
            view.showMessage("User created successfully, now you can sign in!");
    }
}
