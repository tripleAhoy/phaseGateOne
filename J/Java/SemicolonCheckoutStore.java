import java.util.ArrayList;
import java.util.Scanner;

public class SemicolonCheckoutStore {

    static class Item {
        String name;
        int quantity;
        double unitPrice;

        Item(String name, int quantity, double unitPrice) {
            this.name = name;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        double totalPrice() {
            return quantity * unitPrice;
        }
    }

class CheckoutCalculator {
    public static double computeSubtotal(List<Item> cart) {
        return cart.stream().mapToDouble(Item::totalPrice).sum();
    }

    public static double computeDiscount(double subtotal) {
        return (subtotal >= 20000) ? 0.10 * subtotal : 0;
    }

    public static double computeVAT(double subtotal) {
        return 0.075 * subtotal;
    }

    public static double computeTotal(double subtotal) {
        double discount = computeDiscount(subtotal);
        double vat = computeVAT(subtotal);
        return subtotal - discount + vat;
    }
}


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Item> cart = new ArrayList<>();

        System.out.println("=== Welcome to Semicolon Store Checkout ===");
        System.out.print("What is the name of the customer? ");
        String customerName = input.nextLine();

        System.out.print("What is the name of the cashier? ");
        String cashierName = input.nextLine();

        while (true) {
            System.out.print("What did the user buy? ");
            String productName = input.nextLine();

            System.out.print("How many pieces? ");
            int quantity = input.nextInt();

            System.out.print("How much per unit? #");
            double price = input.nextDouble();
            input.nextLine();  // Consume newline

            cart.add(new Item(productName, quantity, price));

            System.out.print("Add more items? (yes/no): ");
            String more = input.nextLine();
            if (!more.equalsIgnoreCase("yes")) break;
        }

        double subtotal = 0;
        for (Item item : cart) {
            subtotal += item.totalPrice();
        }

        double discount = (subtotal >= 20000) ? 0.10 * subtotal : 0;
        double vat = 0.075 * subtotal;
        double total = subtotal - discount + vat;

        System.out.println("\n=========== SEMICOLON STORE INVOICE ===========");
        System.out.println("Customer Name: " + customerName);
        System.out.println("Cashier: " + cashierName);
        System.out.println("-----------------------------------------------");
        System.out.printf("%-20s %-10s %-12s %-10s\n", "Product", "Qty", "Unit Price", "Total");
        System.out.println("---------------------------------------------------------------");

        for (Item item : cart) {
            System.out.printf("%-20s %-10d #%-10.2f #%-10.2f\n", item.name, item.quantity, item.unitPrice, item.totalPrice());
        }

        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-45s #%.2f\n", "Subtotal:", subtotal);
        System.out.printf("%-45s #%.2f\n", "Discount (10% if ≥ #20,000):", discount);
        System.out.printf("%-45s #%.2f\n", "VAT (7.5%):", vat);
        System.out.printf("%-45s #%.2f\n", "Total:", total);
        System.out.println("===============================================================");

        input.close();
    }
}
