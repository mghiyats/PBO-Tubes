import dao.UserDAO;
import model.User;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOTest {

    static UserDAO userDAO;
    static User testUser;

    @BeforeAll
    public static void setup() {
        userDAO = new UserDAO();
        // Create a unique user for testing
        testUser = new User(
                null,
                "testuser_" + UUID.randomUUID(),
                "Test Name",
                "testuser@example.com",
                "testpassword",
                "member"
        );
    }

    @Test
    @Order(1)
    public void testAddUser() {
        userDAO.addUser(testUser);
        assertNotNull(testUser.getId(), "User ID should be set after addUser");
    }

    @Test
    @Order(2)
    public void testGetUserById() {
        User fetched = userDAO.getUserById(testUser.getId());
        assertNotNull(fetched, "User should be fetched by ID");
        assertEquals(testUser.getUsername(), fetched.getUsername());
    }

    @Test
    @Order(3)
    public void testGetUserByUsername() {
        User fetched = userDAO.getUserByUsername(testUser.getUsername());
        assertNotNull(fetched, "User should be fetched by username");
        assertEquals(testUser.getId(), fetched.getId());
    }

    @Test
    @Order(4)
    public void testUpdateUser() {
        testUser.setName("Updated Name");
        userDAO.updateUser(testUser);
        User updated = userDAO.getUserById(testUser.getId());
        assertEquals("Updated Name", updated.getName());
    }

    @Test
    @Order(5)
    public void testGetAllUsers() {
        List<User> users = userDAO.getAllUsers();
        assertNotNull(users, "User list should not be null");
        assertTrue(users.stream().anyMatch(u -> u.getId().equals(testUser.getId())), "Test user should be in the list");
    }

    @Test
    @Order(6)
    public void testDeleteUser() {
        userDAO.deleteUser(testUser.getId());
        User deleted = userDAO.getUserById(testUser.getId());
        assertNull(deleted, "User should be deleted");
    }
}

