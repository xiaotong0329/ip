package meeseeks.command;

import meeseeks.storage.Storage;
import meeseeks.task.*;
import meeseeks.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddCommand extends Command {
    private String description;
    private String type;
    private String by;
    private String from;
    private String to;

    // For todo
    public AddCommand(String description, String type) {
        this.description = description.trim();
        this.type = type;
    }

    // For deadline
    public AddCommand(String description, String type, String by) {
        this.description = description.trim();
        this.type = type;
        this.by = by.trim();
    }

    // For event
    public AddCommand(String description, String type, String from, String to) {
        this.description = description.trim();
        this.type = type;
        this.from = from.trim();
        this.to = to.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        Task t;
        switch (type) {
            case "todo":
                t = new ToDo(description);
                break;
            case "deadline":
                DateTimeFormatter inputFmt = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                LocalDateTime byDate = LocalDateTime.parse(by, inputFmt);
                t = new Deadline(description, byDate);
                break;
            case "event":
                // Simplify for now (string storage). Can extend with LocalDateTime like deadline.
                t = new Event(description, from, to);
                break;
            default:
                throw new Exception("Unknown add type");
        }
        tasks.add(t);
        ui.showMessage("Got it. I've added this task:\n  " + t +
                "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
