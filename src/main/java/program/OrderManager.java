package program;

import database.DataBaseManager;

import java.sql.PreparedStatement;

public class OrderManager {
    private final DataBaseManager db;
    private final PaymentProcessor payment;

    public OrderManager(DataBaseManager db, PaymentProcessor payment) {
        this.db = db;
        this.payment = payment;
    }

    public void createOrder(String user, String description, double total) {
        String sql = "SELECT * FROM orders";
        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            System.out.println("Pedido registrado.");
            payment.processPayment(user, total);
        } catch (Exception e) {
            System.err.println("Error al registrar pedido: " + e.getMessage());
        }
    }
}
