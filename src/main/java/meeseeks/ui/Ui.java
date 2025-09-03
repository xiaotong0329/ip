package meeseeks.ui;

import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("Hello! I'm Meeseeks.Meeseeks\nLook at me!\n");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("_____________________________");
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println("Oh Geez! " + message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
