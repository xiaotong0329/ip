import java.util.ArrayList;

public class Meeseeks {
    public static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {


        System.out.println("Hello! I'm Meeseeks\n" +
                "What can I do for you?\n");
        AddTodo("borrow book");
        AddDeadline("return book", "Sunday");
        AddEvent("project meeting", "Mon 2pm", "4pm");
        Listing("list");

        Marking("mark 2");
        Listing("list");

        Marking("unmark 2");
        Listing("list");


    }

    public static void AddTodo(String description) {
        Task t = new ToDo(description);
        list.add(t);
        printAddedTask(t);
    }

    public static void AddDeadline(String description, String by) {
        Task t = new Deadline(description, by);
        list.add(t);
        printAddedTask(t);
    }

    public static void AddEvent(String description, String from, String to) {
        Task t = new Event(description, from, to);
        list.add(t);
        printAddedTask(t);
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

    public static void Listing(String input) {
        System.out.println("list\n");

        if(input.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + "." + list.get(i));
            }
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
}
