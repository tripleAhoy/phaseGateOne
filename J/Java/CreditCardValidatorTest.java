import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CreditCardValidatorTest {

    @Test
    public void testGetCardType() {
        assertEquals("Visa", CreditCardValidator.getCardType("4123456789012"));
        assertEquals("MasterCard", CreditCardValidator.getCardType("5123456789012345"));
        assertEquals("American Express", CreditCardValidator.getCardType("371234567890123"));
        assertEquals("Discover", CreditCardValidator.getCardType("6123456789012345"));
        assertEquals("Unknown", CreditCardValidator.getCardType("7123456789012"));
    }

    @Test
    public void testValidCheckValid() {
        assertTrue(CreditCardValidator.ValidCheck("4388576018410707"));
        assertTrue(CreditCardValidator.ValidCheck"6011111111111117"));
    }

    @Test
    public void testValidCheckInvalid() {
        assertFalse(CreditCardValidator.ValidCheck("4388576018402626"));
        assertFalse(CreditCardValidator.ValidCheck("1234567890123"));
    }
}
