import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import model.TaskAssignment;

public class TaskAssignmentTest {

    @Test
    public void testConstructorAndGetters() {
        TaskAssignment ta = new TaskAssignment("user1", "task1", "Do something");
        assertEquals("user1", ta.getUserId());
        assertEquals("task1", ta.getTaskId());
        assertEquals("Do something", ta.getJobDesc());
    }

    @Test
    public void testSetUserId() {
        TaskAssignment ta = new TaskAssignment("user1", "task1", "Do something");
        ta.setUserId("user2");
        assertEquals("user2", ta.getUserId());
    }

    @Test
    public void testSetTaskId() {
        TaskAssignment ta = new TaskAssignment("user1", "task1", "Do something");
        ta.setTaskId("task2");
        assertEquals("task2", ta.getTaskId());
    }

    @Test
    public void testSetJobDesc() {
        TaskAssignment ta = new TaskAssignment("user1", "task1", "Do something");
        ta.setJobDesc("New job");
        assertEquals("New job", ta.getJobDesc());
    }
}