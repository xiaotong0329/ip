import java.util.ArrayList;

public class Meeseeks {
    public static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {


        System.out.println("Hello! I'm Meeseeks\n" +
                "What can I do for you?\n");
//        Echo("echos");
        Add("reading books");
        Add("playing games");
        listing("list");


    }

    public static void Echo(String input) {
        if (input.equals("bye")) {

            System.out.println("bye\n" + "Bye. Hope to see you again soon!");
        } else {
            System.out.println(input + "\n" + "  " + input);
        }
    }

    public static void Add(String input) {
        list.add(input);
        System.out.println(input + "\n" + "  " +"added: " + input);
    }

    public static void listing(String input) {
        if(input.equals("list")) {
            System.out.println("list\n");
            for (int i = 0; i < list.size(); i++) {
                int j = i + 1;
                String s = list.get(i);

                System.out.println(j + ": " + s);
            }
        }

    }
}
