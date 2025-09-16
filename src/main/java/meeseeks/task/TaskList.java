package meeseeks.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task delete(int index) {
        return tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }
    
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Prints all tasks to the console with their index numbers.
     * This method is deprecated in favor of getTasksAsString() for better separation of concerns.
     */
    @Deprecated
    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }
    
    public String getTasksAsString() {
        if (tasks.isEmpty()) {
            return "Your task list is empty!";
        }
        
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append((i + 1)).append(".").append(tasks.get(i)).append("\n");
        }
        return result.toString().trim();
    }
}
