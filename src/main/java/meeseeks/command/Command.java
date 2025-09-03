package meeseeks.command;

import meeseeks.storage.Storage;
import meeseeks.task.TaskList;
import meeseeks.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;
    public boolean isExit() {
        return false;
    }
}
