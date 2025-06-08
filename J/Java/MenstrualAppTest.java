import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MenstrualAppTest {

    @Test
    public void testNextPeriod() {
        assertEquals(35, MenstrualApp.nextPeriod(1, 34));
        assertEquals(50, MenstrualApp.nextPeriod(15, 35));
        assertEquals(28, MenstrualApp.nextPeriod(14, 14));
    }

    @Test
    public void testOvulationDay() {
        assertEquals(21, MenstrualApp.ovulationDay(1, 35));
        assertEquals(21, MenstrualApp.ovulationDay(7, 28));
        assertEquals(18, MenstrualApp.ovulationDay(4, 28));
    }

    @Test
    public void testFertileWindow() {
        assertEquals("14 to 23", MenstrualApp.fertileWindow(20));
        assertEquals("7 to 11", MenstrualApp.fertileWindow(9));
        assertEquals("21 to 30", MenstrualApp.fertileWindow(27));
    }

    @Test
    public void testGetSafeDays() {
        String expected1 = "Before ovulation: 1-18\nAfter ovulation: 21-35";
        assertEquals(expected1, MenstrualApp.getSafeDays(1, 22, 35));

        String expected2 = "Before ovulation: 7-16\nAfter ovulation: 19-28";
        assertEquals(expected2, MenstrualApp.getSafeDays(7, 20, 28));
    }
}
