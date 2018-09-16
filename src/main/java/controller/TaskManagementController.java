package controller;

import model.Login;
import model.Task;
import model.Tasker;
import view.TaskerView;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TaskManagementController {

    private Login modelOfLogin;
    private Tasker modelOfTasker;
    private TaskerView view;
    private Scanner input;


    public TaskManagementController(Tasker modelOfTasker, TaskerView view, Login modelOfLogin) {
        this.modelOfTasker = modelOfTasker;
        this.view = view;
        this.modelOfLogin = modelOfLogin;
        input = new Scanner(System.in);
    }


    private void executeTaskOptions(int option) throws SQLException {
        switch (option) {
            case 1:
                view.showInputingTaskMessage();
                input.nextLine();
                String taskName = input.nextLine();
                modelOfTasker.addTask(taskName, modelOfLogin.getConnectedUser());
                break;
            case 2:
                view.showInputingTaskMessage();
                input.nextLine();
                taskName = input.nextLine();
                modelOfTasker.deleteTask(taskName, modelOfLogin.getConnectedUser());
                break;
            case 3:
                List<Task> tasks = modelOfTasker.getTasks(modelOfLogin.getConnectedUser());
                for (Task task : tasks)
                    view.showMessage(String.valueOf(task));
                break;
        }
    }

    void executeUserCase() throws SQLException {
        int option;
        do {
            view.showTaskMenu();
            option = input.nextInt();
            executeTaskOptions(option);
        }
        while (option != 4);
    }
}
