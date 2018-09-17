package model;

import controller.login.Login;
import controller.task.Tasker;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.TaskActions;
import repository.UserActions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TaskerTest {
    private Tasker tasker;
    private Login login;
    @Mock
    TaskActions taskActionsMock;
    @Mock
    UserActions userActionsMock;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        tasker = new Tasker(taskActionsMock);
        login = new Login(userActionsMock);
    }


    @Test
    public void addTask() throws SQLException {
        Task taskName = new Task("wash");
        User user = new User("admin", "123");

        tasker.addTask("wash", user);

        verify(taskActionsMock).addTask(taskName, user);
    }

    @Test
    public void deleteTask() throws SQLException {
        Task taskName = new Task("wash");
        User user = new User("admin", "123");

        tasker.deleteTask("wash", user);

        verify(taskActionsMock).deleteTask(taskName, user);
    }

    @Test
    public void getTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        User user = new User("admin", "123");
        Task task = new Task("wash");
        tasks.add(task);
        when(taskActionsMock.getTasks((User) any())).thenReturn(tasks);

        List<Task> actual = tasker.getTasks(user);
        List<Task> expected = taskActionsMock.getTasks(user);

        assertEquals(expected, actual);
    }
}