import database.DataBaseManager;
import program.Authenticator;
import program.OrderManager;
import program.PaymentProcessor;

public class Main {
    public static void main(String[] args) {
        DataBaseManager dbManager = new DataBaseManager();
        dbManager.connect();

        PaymentProcessor paymentProcessor = new PaymentProcessor();
        Authenticator authenticator = new Authenticator(dbManager);
        OrderManager orderManager = new OrderManager(dbManager, paymentProcessor);

        if (authenticator.authenticate("admin", "1234")) {
            orderManager.createOrder("admin", "Pizza Hawaiana con borde de queso", 18.99);
        } else {
            System.out.println("Autenticación fallida.");
        }
    }
}
