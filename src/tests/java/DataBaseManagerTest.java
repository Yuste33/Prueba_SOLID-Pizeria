import database.DataBaseManager;

import org.junit.jupiter.api.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class DataBaseManagerTest {
    private static DataBaseManager dbManager;

    @BeforeAll
    static void setup() {
        dbManager = new DataBaseManager();
        dbManager.connect();
    }

    @AfterAll
    static void tearDown() {
        dbManager.close();
    }

    @Test
    void testConnectionNotNull() {
        assertNotNull(dbManager.getConnection(), "La conexión no debería ser null.");
    }

    @Test
    void testInsertUser() {
        String testUsername = "test_user";
        String testPassword = "123456";

        dbManager.insertUser(testUsername, testPassword);


        try (PreparedStatement ps = dbManager.getConnection().prepareStatement(
                "SELECT * FROM users WHERE username = ?")) {
            ps.setString(1, testUsername);
            ResultSet rs = ps.executeQuery();
            assertTrue(rs.next(), "El usuario no fue insertado correctamente.");
            assertEquals(testUsername, rs.getString("username"));
        } catch (SQLException e) {
            fail("Error al verificar inserción: " + e.getMessage());
        }
    }

    @Test
    void testExecuteQuery() {
        String query = "CREATE TABLE IF NOT EXISTS test_table (id INT PRIMARY KEY AUTO_INCREMENT)";
        assertDoesNotThrow(() -> dbManager.executeQuery(query), "La creación de tabla debería ejecutarse sin errores.");
    }

    @Test
    void testCloseConnection() {
        dbManager.close();
        try {
            assertTrue(dbManager.getConnection().isClosed(), "La conexión debería estar cerrada.");
        } catch (SQLException e) {
            fail("Excepción al verificar si la conexión está cerrada: " + e.getMessage());
        } finally {
            dbManager.connect();
        }
    }
}
