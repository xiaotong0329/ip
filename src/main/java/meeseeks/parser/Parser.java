package meeseeks.parser;

import meeseeks.command.*;
import meeseeks.task.Deadline;
import meeseeks.task.Event;
import meeseeks.task.Task;

import java.time.LocalDateTime;

public class Parser {
    public static Command parse(String fullCommand) {
        assert fullCommand != null : "Command string cannot be null";
        assert !fullCommand.trim().isEmpty() : "Command string cannot be empty";
        
        if (fullCommand.startsWith("todo")) {
            return new AddCommand(fullCommand.substring(5), "todo");
        } else if (fullCommand.startsWith("deadline")) {
            String[] parts = fullCommand.split(" /by ", 2);
            assert parts.length == 2 : "Deadline command must have both description and time";
            return new AddCommand(parts[0].substring(9), "deadline", parts[1]);
        } else if (fullCommand.startsWith("event")) {
            String[] parts = fullCommand.split(" /from | /to ");
            assert parts.length == 3 : "Event command must have description, from time, and to time";
            return new AddCommand(parts[0].substring(6), "event", parts[1], parts[2]);
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.startsWith("delete")) {
            return new DeleteCommand(Integer.parseInt(fullCommand.substring(7)) - 1);
        } else if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.startsWith("find ")) {
            return new FindCommand(fullCommand.substring(5));
        } else if (fullCommand.startsWith("mark ")) {
            return new MarkCommand(Integer.parseInt(fullCommand.substring(5)) - 1);
        } else if (fullCommand.startsWith("unmark ")) {
            return new UnmarkCommand(Integer.parseInt(fullCommand.substring(7)) - 1);
        }
        return new UnknownCommand();
    }

    public static Task parseTaskFromFile(String line) {
        assert line != null : "Line cannot be null";
        assert !line.trim().isEmpty() : "Line cannot be empty";
        
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
                    if (parts.length < 4) throw new IllegalArgumentException("Meeseeks.task.Deadline missing time");
                    LocalDateTime by = LocalDateTime.parse(parts[3].trim());
                    task = new Deadline(description, by);
                    break;
                case "E":
                    if (parts.length < 5) throw new IllegalArgumentException("Meeseeks.task.Event missing times");
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
