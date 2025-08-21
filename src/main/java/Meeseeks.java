import java.util.ArrayList;

public class Meeseeks {
    public static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {


        System.out.println("Hello! I'm Meeseeks\n" +
                "What can I do for you?\n");
//        Echo("echos");
        Add("reading books");
        Add("playing games");
        Listing("list");
        Marking("mark 2");
        Listing("list");


    }

    public static void Echo(String input) {
        if (input.equals("bye")) {

            System.out.println("bye\n" + "Bye. Hope to see you again soon!");
        } else {
            System.out.println(input + "\n" + "  " + input);
        }
    }

    public static void Add(String input) {
        String task = "[ ]" + input;
        list.add(task);
        System.out.println(input + "\n" + "  " +"added: " + input);
    }

    public static void Listing(String input) {
        if(input.equals("list")) {
            System.out.println("list\n");
            for (int i = 0; i < list.size(); i++) {
                int j = i + 1;
                String s = list.get(i);

                System.out.println( + j + ": " + s);
            }
        }

    }

    public static void Marking(String input) {
        int index = input.indexOf(" ");
        String s = input.substring(0, index);
        if(s.equals("mark")) {
            int num = Integer.parseInt(input.substring(index + 1));
            String oritask = list.get(num - 1);
            int index2 = oritask.indexOf("]");
            String intertask = oritask.substring(index2 + 1);
            String task = "[X]" + intertask;

            list.set(num - 1, task);
            System.out.println( "Nice! I've marked this task as done: \n" + task);

        }
        else if(s.equals("unmark")) {
            int num = Integer.parseInt(input.substring(index + 1));
            String oritask = list.get(num - 1);
            int index2 = oritask.indexOf("]");
            String intertask = oritask.substring(index2 + 1);
            String task = "[ ]" + intertask;

            System.out.println( "OK, I've marked this task as not done yet: \n" + task);
        }
    }
}
