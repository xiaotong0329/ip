package meeseeks.storage;

import meeseeks.parser.Parser;
import meeseeks.task.TaskList;
import meeseeks.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        Path path = Path.of(filePath);
        if (!Files.exists(path)) {
            return new ArrayList<>();
        }

        List<String> lines = Files.readAllLines(path);
        List<Task> tasks = lines.stream()
                .map(Parser::parseTaskFromFile)
                .filter(task -> task != null)
                .collect(Collectors.toList());
        return new ArrayList<>(tasks);
    }

    public void save(TaskList tasks) throws IOException {
        File f = new File(filePath);
        f.getParentFile().mkdirs();

        String content = tasks.getList().stream()
                .map(task -> task.toFileFormat())
                .collect(Collectors.joining("\n"));
        
        try (FileWriter writer = new FileWriter(f)) {
            writer.write(content);
            if (!content.isEmpty()) {
                writer.write("\n");
            }
        }
    }
}
