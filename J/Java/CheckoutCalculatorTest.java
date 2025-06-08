import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Arrays;

class CheckoutCalculatorTest {

    @Test
    void testComputeSubtotal() {
        List<Item> cart = Arrays.asList(new Item("Laptop", 1, 100000), new Item("Mouse", 2, 5000));
        assertEquals(110000, CheckoutCalculator.computeSubtotal(cart), 0.01);
    }

    @Test
    void testComputeDiscount() {
        assertEquals(2000, CheckoutCalculator.computeDiscount(20000), 0.01);
        assertEquals(0, CheckoutCalculator.computeDiscount(19999), 0.01);
    }

    @Test
    void testComputeVAT() {
        assertEquals(1500, CheckoutCalculator.computeVAT(20000), 0.01);
    }

    @Test
    void testComputeTotal() {
        assertEquals(19500, CheckoutCalculator.computeTotal(20000), 0.01);
    }
}
