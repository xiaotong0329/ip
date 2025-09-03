import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

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

        ArrayList<String> lines = new ArrayList<>(Files.readAllLines(path));
        ArrayList<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            Task task = Parser.parseTaskFromFile(line);
            if (task != null) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    public void save(TaskList tasks) throws IOException {
        File f = new File(filePath);
        f.getParentFile().mkdirs();

        FileWriter writer = new FileWriter(f);
        for (Task task : tasks.getList()) {
            writer.write(task.toFileFormat() + "\n");
        }
        writer.close();
    }
}
