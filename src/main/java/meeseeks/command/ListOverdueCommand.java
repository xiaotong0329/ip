package meeseeks.command;

import meeseeks.storage.Storage;
import meeseeks.task.RecurringTask;
import meeseeks.task.Task;
import meeseeks.task.TaskList;
import meeseeks.ui.Ui;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Command to list all overdue recurring tasks.
 */
public class ListOverdueCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<RecurringTask> overdueTasks = tasks.getList().stream()
                .filter(task -> task instanceof RecurringTask)
                .map(task -> (RecurringTask) task)
                .filter(RecurringTask::isOverdue)
                .collect(Collectors.toList());

        if (overdueTasks.isEmpty()) {
            ui.showMessage("Great! No overdue recurring tasks found.");
        } else {
            StringBuilder message = new StringBuilder("Here are your overdue recurring tasks:\n");
            for (int i = 0; i < overdueTasks.size(); i++) {
                message.append((i + 1)).append(".").append(overdueTasks.get(i)).append("\n");
            }
            ui.showMessage(message.toString().trim());
        }
    }

    @Override
    public String executeAndGetResponse(TaskList tasks, Storage storage) {
        List<RecurringTask> overdueTasks = tasks.getList().stream()
                .filter(task -> task instanceof RecurringTask)
                .map(task -> (RecurringTask) task)
                .filter(RecurringTask::isOverdue)
                .collect(Collectors.toList());

        if (overdueTasks.isEmpty()) {
            return "Great! No overdue recurring tasks found.";
        } else {
            StringBuilder message = new StringBuilder("Here are your overdue recurring tasks:\n");
            for (int i = 0; i < overdueTasks.size(); i++) {
                message.append((i + 1)).append(".").append(overdueTasks.get(i)).append("\n");
            }
            return message.toString().trim();
        }
    }
}
