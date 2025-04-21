package program;

import database.DataBaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Authenticator {
    private final DataBaseManager db;

    public Authenticator(DataBaseManager db) {
        this.db = db;
    }

    public boolean authenticate(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement ps = db.getConnection().prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.err.println("Error al autenticar: " + e.getMessage());
            return false;
        }
    }
}
