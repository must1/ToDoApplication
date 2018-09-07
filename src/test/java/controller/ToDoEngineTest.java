package controller;

import model.Task;
import model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.TaskActions;
import repository.UserActions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ToDoEngineTest {

    @Mock
    TaskActions taskActionsMock;
    @Mock
    UserActions userActionsMock;
    private ToDoEngine toDoEngine;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        toDoEngine = new ToDoEngine(userActionsMock, taskActionsMock);
    }

    @Test
    public void createUser() throws SQLException {
        User user = new User("admin", "123");
        toDoEngine.createUser("admin", "123");
        verify(userActionsMock).createUser(user);
    }

    @Test
    public void getTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        User user = new User("admin", "123");
        Task task = new Task("wash");
        tasks.add(task);
        when(taskActionsMock.getTasks((User) any())).thenReturn(tasks);

        List<Task> actual = toDoEngine.getTasks();
        List<Task> expected = taskActionsMock.getTasks(user);

        assertEquals(expected, actual);

    }

    @Test
    public void signIn() {
        // TODO: 2018-09-06
    }

    @Test
    public void addTask() throws SQLException {
        toDoEngine.signIn("admin", "123");
        Task taskName = new Task("wash");
        User user = new User("admin", "123");
        verify(userActionsMock).signIn(user);
        toDoEngine.addTask("wash");
        verify(taskActionsMock).addTask(taskName, user);
    }

    @Test
    public void deleteTask() throws SQLException {
        toDoEngine.signIn("admin", "123");
        Task taskName = new Task("wash");
        User user = new User("admin", "123");
        verify(userActionsMock).signIn(user);
        toDoEngine.deleteTask("wash");
        verify(taskActionsMock).deleteTask(taskName, user);
    }
}
