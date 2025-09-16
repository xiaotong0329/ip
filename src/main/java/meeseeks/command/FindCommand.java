package meeseeks.command;

import meeseeks.task.Task;
import meeseeks.task.TaskList;
import meeseeks.ui.Ui;
import meeseeks.storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase().trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matches = tasks.findTasksByKeyword(keyword);

        if (matches.isEmpty()) {
            ui.showMessage("No tasks found containing: " + keyword);
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < matches.size(); i++) {
                ui.showMessage((i + 1) + "." + matches.get(i));
            }
        }
    }
}
