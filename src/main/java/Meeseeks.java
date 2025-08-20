public class Meeseeks {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Meeseeks\n" +
                "What can I do for you?\n");
        Echo("echos");

    }

    public static void Echo(String input) {
        if (input.equals("bye")) {

            System.out.println("bye\n" + "Bye. Hope to see you again soon!");
        } else {
            System.out.println(input + "\n" + "  " + input);
        }
    }
}
