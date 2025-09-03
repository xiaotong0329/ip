package meeseeks.task;

public class Task {
    protected String name;
    protected Boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getStatus() {
        return isDone? "[X]": "[ ]";
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return getStatus() + " " + name;
    }

    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + name;
    }

}


