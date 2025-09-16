package meeseeks.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Task list cannot be null";
        this.tasks = tasks;
    }

    public void add(Task t) {
        assert t != null : "Task cannot be null";
        tasks.add(t);
    }

    public Task delete(int index) {
        assert index >= 0 : "Index must be non-negative";
        assert index < tasks.size() : "Index must be within bounds of task list";
        return tasks.remove(index);
    }

    public Task get(int index) {
        assert index >= 0 : "Index must be non-negative";
        assert index < tasks.size() : "Index must be within bounds of task list";
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
        
        String header = "Here are the tasks in your list:\n";
        String taskList = IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + "." + tasks.get(i))
                .collect(Collectors.joining("\n"));
        return header + taskList;
    }
    
    /**
     * Finds tasks that contain the given keyword in their name (case-insensitive).
     * Uses Java streams for efficient filtering.
     * 
     * @param keyword the keyword to search for
     * @return a list of tasks that match the keyword
     */
    public List<Task> findTasksByKeyword(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return tasks.stream()
                .filter(task -> task.getName().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());
    }
    
    /**
     * Gets the count of completed tasks using streams.
     * 
     * @return the number of completed tasks
     */
    public long getCompletedTaskCount() {
        return tasks.stream()
                .filter(Task::isDone)
                .count();
    }
    
    /**
     * Gets the count of pending tasks using streams.
     * 
     * @return the number of pending tasks
     */
    public long getPendingTaskCount() {
        return tasks.stream()
                .filter(task -> !task.isDone())
                .count();
    }
}
