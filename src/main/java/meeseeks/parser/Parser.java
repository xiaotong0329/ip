package meeseeks.parser;

import meeseeks.command.*;
import meeseeks.task.Deadline;
import meeseeks.task.Event;
import meeseeks.task.Task;

import java.time.LocalDateTime;

public class Parser {
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String LIST_COMMAND = "list";
    private static final String DELETE_COMMAND = "delete";
    private static final String BYE_COMMAND = "bye";
    private static final String FIND_COMMAND = "find ";
    private static final String MARK_COMMAND = "mark ";
    private static final String UNMARK_COMMAND = "unmark ";
    
    private static final String DEADLINE_SEPARATOR = " /by ";
    private static final String EVENT_FROM_SEPARATOR = " /from ";
    private static final String EVENT_TO_SEPARATOR = " /to ";
    private static final String FILE_SEPARATOR = " \\| ";
    
    private static final int TODO_PREFIX_LENGTH = 5;
    private static final int DEADLINE_PREFIX_LENGTH = 9;
    private static final int EVENT_PREFIX_LENGTH = 6;
    private static final int DELETE_PREFIX_LENGTH = 7;
    private static final int FIND_PREFIX_LENGTH = 5;
    private static final int MARK_PREFIX_LENGTH = 5;
    private static final int UNMARK_PREFIX_LENGTH = 7;
    
    private static final String TODO_TYPE = "T";
    private static final String DEADLINE_TYPE = "D";
    private static final String EVENT_TYPE = "E";
    private static final String DONE_VALUE = "1";
    private static final String NOT_DONE_VALUE = "0";
    
    public static Command parse(String fullCommand) {
        if (fullCommand.startsWith(TODO_COMMAND)) {
            return new AddCommand(fullCommand.substring(TODO_PREFIX_LENGTH), TODO_COMMAND);
        } else if (fullCommand.startsWith(DEADLINE_COMMAND)) {
            String[] parts = fullCommand.split(DEADLINE_SEPARATOR, 2);
            return new AddCommand(parts[0].substring(DEADLINE_PREFIX_LENGTH), DEADLINE_COMMAND, parts[1]);
        } else if (fullCommand.startsWith(EVENT_COMMAND)) {
            String[] parts = fullCommand.split(EVENT_FROM_SEPARATOR + "|" + EVENT_TO_SEPARATOR);
            return new AddCommand(parts[0].substring(EVENT_PREFIX_LENGTH), EVENT_COMMAND, parts[1], parts[2]);
        } else if (fullCommand.equals(LIST_COMMAND)) {
            return new ListCommand();
        } else if (fullCommand.startsWith(DELETE_COMMAND)) {
            return new DeleteCommand(Integer.parseInt(fullCommand.substring(DELETE_PREFIX_LENGTH)) - 1);
        } else if (fullCommand.equals(BYE_COMMAND)) {
            return new ExitCommand();
        } else if (fullCommand.startsWith(FIND_COMMAND)) {
            return new FindCommand(fullCommand.substring(FIND_PREFIX_LENGTH));
        } else if (fullCommand.startsWith(MARK_COMMAND)) {
            return new MarkCommand(Integer.parseInt(fullCommand.substring(MARK_PREFIX_LENGTH)) - 1);
        } else if (fullCommand.startsWith(UNMARK_COMMAND)) {
            return new UnmarkCommand(Integer.parseInt(fullCommand.substring(UNMARK_PREFIX_LENGTH)) - 1);
        }
        return new UnknownCommand();
    }

    public static Task parseTaskFromFile(String line) {
        try {
            String[] parts = line.split(FILE_SEPARATOR);
            if (parts.length < 3) {
                throw new IllegalArgumentException("Invalid format");
            }

            String type = parts[0].trim();
            boolean isDone = parts[1].trim().equals(DONE_VALUE);
            String description = parts[2].trim();

            Task task;

            switch (type) {
                case TODO_TYPE:
                    task = new Task(description);
                    break;
                case DEADLINE_TYPE:
                    if (parts.length < 4) throw new IllegalArgumentException("Deadline missing time");
                    LocalDateTime by = LocalDateTime.parse(parts[3].trim());
                    task = new Deadline(description, by);
                    break;
                case EVENT_TYPE:
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
