package controller;

import model.Task;
import model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.TaskActions;
import repository.UserActions;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToDoEngineTest {

    @Mock
    TaskActions taskStorageMock;
    @Mock
    UserActions userStorageMock;
    private ToDoEngine toDoEngine;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        toDoEngine = new ToDoEngine(userStorageMock, taskStorageMock);
    }

    @Test
    public void createUser() throws SQLException {
        User user = new User("admin", "123");
        toDoEngine.createUser("admin", "123");
        verify(userStorageMock).createUser(user);
    }

    @Test
    public void getTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        User user = new User("admin","123");
        Task task = new Task("wash");
        tasks.add(task);
        when(taskStorageMock.getTasks((User) any())).thenReturn(tasks);

        List<Task> actual = toDoEngine.getTasks();
        List<Task> expected = taskStorageMock.getTasks(user);

        assertEquals(expected,actual);

    }

    @Test
    public void signIn() {
    }

    @Test
    public void addTask() throws SQLException {

    }

    @Test
    public void deleteTask() {
    }
}
