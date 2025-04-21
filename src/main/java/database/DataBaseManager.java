package database;

import java.sql.*;

public class DataBaseManager {
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3307/pizzeriadb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public void connect() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conectado a la base de datos.");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }


    public void insertUser(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            System.out.println("Usuario insertado: " + username);
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
        }
    }

    public void executeQuery(String query) {
        if (connection == null) {
            System.err.println("Conexión no establecida. Llama a connect() primero.");
            return;
        }

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(query);
            System.out.println("onsulta ejecutada.");
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
