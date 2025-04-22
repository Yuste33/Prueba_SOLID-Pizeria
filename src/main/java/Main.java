import database.DataBaseManager;
import program.Authenticator;
import program.OrderManager;
import program.PaymentProcessor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DataBaseManager dbManager = new DataBaseManager();
        dbManager.connect();

        PaymentProcessor paymentProcessor = new PaymentProcessor();
        Authenticator authenticator = new Authenticator(dbManager);
        OrderManager orderManager = new OrderManager(dbManager, paymentProcessor);

        String currentUser = null;

        while (true) {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Hacer pedido");
            System.out.println("3. Ver mis pedidos");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Usuario: ");
                    String user = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String pass = scanner.nextLine();

                    if (authenticator.authenticate(user, pass)) {
                        currentUser = user;
                        System.out.println("Sesión iniciada como " + user);
                    } else {
                        System.out.println("Autenticación fallida.");
                    }
                    break;

                case "2":
                    if (currentUser == null) {
                        System.out.println("Primero debes iniciar sesión.");
                        break;
                    }
                    System.out.print("Tipo de pizza: ");
                    String pizza = scanner.nextLine();
                    System.out.print("Precio: ");
                    double precio = Double.parseDouble(scanner.nextLine());

                    orderManager.createOrder(currentUser, pizza, precio);
                    System.out.println("Pedido creado con éxito.");
                    break;
                case "3":
                    if (currentUser == null) {
                        System.out.println("Primero debes iniciar sesión.");
                        break;
                    }

                    orderManager.getOrdersByUser(currentUser);
                    break;

                case "4":
                    System.out.println("¡Hasta luego!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
