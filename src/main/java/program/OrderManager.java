package program;

import database.DataBaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderManager {
    private final DataBaseManager db;
    private final PaymentProcessor payment;

    public OrderManager(DataBaseManager db, PaymentProcessor payment) {
        this.db = db;
        this.payment = payment;
    }

    public void createOrder(String user, String pizzaType, double total) {
        String sql = "INSERT INTO orders (username, pizza_type, status) VALUES (?, ?, ?)";
        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setString(1, user);
            ps.setString(2, pizzaType);
            ps.setString(3, "pendiente"); // ejemplo de estado inicial
            ps.executeUpdate();

            System.out.println("Pedido registrado para " + user);
            payment.processPayment(user, total);
        } catch (Exception e) {
            System.err.println("Error al registrar pedido: " + e.getMessage());
        }
    }


    public void getOrdersByUser(String username) {
        String sql = "SELECT * FROM orders WHERE username = ?";
        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            System.out.println("\nTus pedidos:");
            boolean tienePedidos = false;

            while (rs.next()) {
                tienePedidos = true;
                int id = rs.getInt("id");
                String pizza = rs.getString("pizza_type");
                String estado = rs.getString("status");

                System.out.println("• Pedido #" + id + ": " + pizza + " [" + estado + "]");
            }

            if (!tienePedidos) {
                System.out.println("No tienes pedidos registrados.");
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener pedidos: " + e.getMessage());
        }
    }


}
