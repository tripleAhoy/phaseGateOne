import java.util.List;

class Item {
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
