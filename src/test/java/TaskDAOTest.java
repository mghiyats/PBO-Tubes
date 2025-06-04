import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dao.TaskDAO;
import model.Task;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskDAOTest {

    static TaskDAO taskDAO;
    static Task testTask;

    @BeforeAll
    public static void setup() {
        taskDAO = new TaskDAO();
        // Create a test task with today's date
        testTask = new Task(
                null,
                "JUnit Test Task",
                "JUnit test description",
                new Date(),
                "pending"
        );
    }

    @Test
    @Order(1)
    public void testAddTask() {
        taskDAO.addTask(testTask);
        assertNotNull(testTask.getId(), "Task ID should be set after addTask");
    }

    @Test
    @Order(2)
    public void testGetTaskById() {
        Task fetched = taskDAO.getTaskById(testTask.getId());
        assertNotNull(fetched, "Task should be fetched by ID");
        assertEquals(testTask.getTitle(), fetched.getTitle());
    }

    @Test
    @Order(3)
    public void testGetTaskByTitle() {
        Task fetched = taskDAO.getTaskByTitle(testTask.getTitle());
        assertNotNull(fetched, "Task should be fetched by title");
        assertEquals(testTask.getId(), fetched.getId());
    }

    @Test
    @Order(4)
    public void testUpdateTask() {
        testTask.setDescription("Updated description");
        testTask.setStatus("completed");
        taskDAO.updateTask(testTask);
        Task updated = taskDAO.getTaskById(testTask.getId());
        assertEquals("Updated description", updated.getDescription());
        assertEquals("completed", updated.getStatus());
    }

    @Test
    @Order(5)
    public void testGetAllTasks() {
        List<Task> tasks = taskDAO.getAllTasks();
        assertNotNull(tasks, "Task list should not be null");
        assertTrue(tasks.stream().anyMatch(t -> t.getId().equals(testTask.getId())), "Test task should be in the list");
    }

    @Test
    @Order(6)
    public void testDeleteTask() {
        taskDAO.deleteTask(testTask.getId());
        Task deleted = taskDAO.getTaskById(testTask.getId());
        assertNull(deleted, "Task should be deleted");
    }
}