import java.util.Scanner;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class MenstrualApp {

    public static LocalDate nextPeriod(LocalDate startDate, int cycleLength) {
        return startDate.plusDays(cycleLength);
    }

    public static LocalDate ovulationDay(LocalDate startDate, int cycleLength) {
        return startDate.plusDays(cycleLength - 14);
    }

    public static String fertileWindow(LocalDate ovulationDate) {
        return ovulationDate.minusDays(2) + " to " + ovulationDate.plusDays(2);
    }

    public static String getSafeDays(LocalDate periodStart, LocalDate fertileEnd, LocalDate nextPeriod) {
        return "Before ovulation: " + periodStart + " - " + fertileEnd.minusDays(4) + "\n" +
               "After ovulation: " + fertileEnd.plusDays(1) + " - " + nextPeriod;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

        System.out.println("\nMenstrual Period App");
        System.out.print("\nEnter last period start day (yyyy-MM-dd): ");
        String inputDate = scanner.next();
        LocalDate periodStart = LocalDate.parse(inputDate);

        System.out.print("Enter average cycle length (21-35 days): ");
        int cycleLength = scanner.nextInt();

        LocalDate nextPeriod = nextPeriod(periodStart, cycleLength);
        LocalDate ovulationDate = ovulationDay(periodStart, cycleLength);
        String fertileWindow = fertileWindow(ovulationDate);
        String safeDays = getSafeDays(periodStart, ovulationDate.plusDays(2), nextPeriod);

        System.out.println("\n\tYour Cycle Info");
        System.out.println("Next period: " + nextPeriod.format(formatter));
        System.out.println("Ovulation day: " + ovulationDate.format(formatter));
        System.out.println("Fertile window: " + fertileWindow);
        System.out.println("Safe days:\n" + safeDays);
    }
}