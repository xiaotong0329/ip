package meeseeks.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

    class TaskTest {

        @Test
        void testMarkAndUnmark() {
            Task t = new ToDo("read book");
            assertFalse(t.isDone);

            t.markAsDone();
            assertTrue(t.isDone);

            t.markAsNotDone();
            assertFalse(t.isDone);
        }

        @Test
        void testDeadlineFormatting() {
            LocalDateTime dt = LocalDateTime.of(2019, 12, 2, 18, 0);
            Deadline d = new Deadline("return book", dt);

            String output = d.toString();
            assertTrue(output.contains("Dec 02 2019"));
        }
    }

