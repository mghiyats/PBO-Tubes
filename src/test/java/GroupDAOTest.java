import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dao.GroupDAO;
import model.Group;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GroupDAOTest {

    static GroupDAO groupDAO;
    static Group testGroup;

    @BeforeAll
    public static void setup() {
        groupDAO = new GroupDAO();
        // Use a unique group ID for testing
        testGroup = new Group("group_" + UUID.randomUUID(), "user_" + UUID.randomUUID());
    }

    @Test
    @Order(1)
    public void testAddGroup() {
        groupDAO.addGroup(testGroup);
        Group fetched = groupDAO.getGroupById(testGroup.getGroupId());
        assertNotNull(fetched, "Group should be added and fetched by ID");
        assertEquals(testGroup.getUserId(), fetched.getUserId());
    }

    @Test
    @Order(2)
    public void testUpdateGroup() {
        testGroup.setUserId("user_" + UUID.randomUUID());
        groupDAO.updateGroup(testGroup);
        Group updated = groupDAO.getGroupById(testGroup.getGroupId());
        assertEquals(testGroup.getUserId(), updated.getUserId());
    }

    @Test
    @Order(3)
    public void testGetAllGroups() {
        List<Group> groups = groupDAO.getAllGroups();
        assertNotNull(groups, "Group list should not be null");
        assertTrue(groups.stream().anyMatch(g -> g.getGroupId().equals(testGroup.getGroupId())), "Test group should be in the list");
    }

    @Test
    @Order(4)
    public void testDeleteGroup() {
        groupDAO.deleteGroup(testGroup.getGroupId());
        Group deleted = groupDAO.getGroupById(testGroup.getGroupId());
        assertNull(deleted, "Group should be deleted");
    }
}