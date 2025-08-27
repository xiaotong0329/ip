import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Meeseeks {
    private static final String DIRECTORY_PATH = "./data/";
    private static final String FILE_PATH = "./data/meeseeks.txt";
    public static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {

        loadData();

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Meeseeks\nLook at me!\n");


        while (sc.hasNextLine()) {
            String command = sc.nextLine();

            if (command.startsWith("todo")) {
                AddTodo(command.substring(5));
            } else if (command.startsWith("deadline")) {
                String[] parts = command.split(" /by ", 2);
                AddDeadline(parts[0].substring(9), parts[1]);
            } else if (command.startsWith("event")) {
                String[] parts = command.split(" /from | /to ");
                AddEvent(parts[0].substring(6), parts[1], parts[2]);
            } else if (command.equals("list")) {
                Listing();
            } else if (command.startsWith("mark")) {
                Marking(command);
            } else if (command.startsWith("unmark")) {
                Marking(command);
            } else if (command.startsWith("delete")) {
                Delete(command);
            } else if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println("I'm not sure about that, how about we talk about something else.");
            }
        }


    }

    public static void AddTodo(String description) {
        if(description == null || description.equals("")) {
            System.out.println("Oh Geez u need to put a description!");
        }
        else {
            Task t = new ToDo(description);
            list.add(t);
            printAddedTask(t);
        }
    }

    public static void AddDeadline(String description, String by) {
        if(description == null || description.equals("")) {
            System.out.println("Oh Geez u need to put a description!");
        }
        else {
            Task t = new Deadline(description, by);
            list.add(t);
            printAddedTask(t);
        }
    }

    public static void AddEvent(String description, String from, String to) {
        if(description == null || description.equals("")) {
            System.out.println("Oh Geez u need to put a description!");
        }
        else {
            Task t = new Event(description, from, to);
            list.add(t);
            printAddedTask(t);
        }
    }

    public static void Echo(String input) {
        if (input.equals("bye")) {

            System.out.println("bye\n" + "Bye. Hope to see you again soon!");
        } else {
            System.out.println(input + "\n" + "  " + input);
        }
    }

    private static void printAddedTask(Task t) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public static void Listing() {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + "." + list.get(i));
            }

    }

    public static void Marking(String input) {
        int index = input.indexOf(" ");
        String s = input.substring(0, index);
        int num = Integer.parseInt(input.substring(index + 1));
        Task t = list.get(num - 1);
        if(s.equals("mark")) {
            t.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + t);

        }
        else if(s.equals("unmark")) {
            t.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + t);
        }
    }

    public static void Delete(String input) {
        int index = Integer.parseInt(input.substring(7));
        Task removed = list.remove(index - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removed);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }


    private static void saveData() {
        try {
            
            File directory = new File(DIRECTORY_PATH);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : list) {
                writer.write(task.toFileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Oh Geez! I couldn't save your tasks: " + e.getMessage());
        }
    }

    private static void loadData() {
        try {
            Path path = Paths.get(FILE_PATH);
            if (!Files.exists(path)) {
                System.out.println("No existing data found. Starting with an empty task list.");
                return;
            }

            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(path));
            for (String line : lines) {
                try {
                    Task task = parseTaskFromFile(line);
                    if (task != null) {
                        list.add(task);
                    }
                } catch (Exception e) {
                    System.out.println("Skipping corrupted line: " + line);
                }
            }

            System.out.println("Loaded " + list.size() + " tasks from file.");
        } catch (IOException e) {
            System.out.println("Oh Geez! I couldn't load your tasks: " + e.getMessage());
        }
    }

    private static Task parseTaskFromFile(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) {
                throw new IllegalArgumentException("Invalid format");
            }

            String type = parts[0].trim();
            boolean isDone = parts[1].trim().equals("1");
            String description = parts[2].trim();

            Task task;

            switch (type) {
                case "T":
                    task = new Task(description);
                    break;
                case "D":
                    if (parts.length < 4) throw new IllegalArgumentException("Deadline missing time");
                    task = new Deadline(description, parts[3].trim());
                    break;
                case "E":
                    if (parts.length < 5) throw new IllegalArgumentException("Event missing times");
                    task = new Event(description, parts[3].trim(), parts[4].trim());
                    break;
                default:
                    throw new IllegalArgumentException("Unknown task type");
            }

            if (isDone) {
                task.markAsDone();
            }

            return task;
        } catch (Exception e) {
            System.out.println("Error parsing line: " + line + " - " + e.getMessage());
            return null;
        }
    }
    
}
