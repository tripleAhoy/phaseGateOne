import java.util.Scanner;

public class MenstrualApp {

    public static int nextPeriod(int startDay, int cycleLength) {
        return startDay + cycleLength;
    }

    public static int ovulationDay(int startDay, int cycleLength) {
        return startDay + (cycleLength - 14);
    }


    public static String fertileWindow(int ovulationDay) {
        return (ovulationDay - 7) + " to " + (ovulationDay + 2);
    }


    public static String getSafeDays(int periodStart, int fertileEnd, int nextPeriod) {
        return "Before ovulation: " + periodStart + "-" + (fertileEnd - 4) + "\n" +
               "After ovulation: " + (fertileEnd + 1) + "-" + nextPeriod;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n\Menstrual Period App");
        System.out.print("\nEnter last period start day (1-31): ");
        int periodStart = scanner.nextInt();
        
        System.out.print("Enter average cycle length (21-35 days): ");
        int cycleLength = scanner.nextInt();
      
        int nextPeriod = nextPeriod(periodStart, cycleLength);
        int ovulationDay = ovulationDay(periodStart, cycleLength);
        String fertileWindow = fertileWindow(ovulationDay);
        String safeDays = getSafeDays(periodStart, ovulationDay + 2, nextPeriod);
        

        System.out.println("\n\tYour Cycle Info");
        System.out.println("Next period: Day " + nextPeriod);
        System.out.println("Ovulation day: Day " + ovulationDay);
        System.out.println("Fertile window: Days " + fertileWindow);
        System.out.println("Safe days:\n" + safeDays);
        

    }
}

