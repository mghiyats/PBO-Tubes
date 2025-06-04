import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import dao.DatabaseConnector;

public class SqlTest {

    @Test
    public void testDatabaseConnectionSuccess() {
        try (Connection conn = DatabaseConnector.getConnection()) {
            assertNotNull(conn, "Connection should not be null");
            assertFalse(conn.isClosed(), "Connection should be open");
        } catch (SQLException e) {
            fail("SQL connection failed: " + e.getMessage());
        }
    }
}