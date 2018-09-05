package model;

public class Task {
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public Task() {
    }

    @Override
    public String toString() {
        return "Task: " + taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
