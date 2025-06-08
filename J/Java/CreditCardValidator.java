import java.util.Scanner;

public class CreditCardValidator {

    public static String getCardType(String number) {
        if (number.startsWith("4")) return "Visa";
        if (number.startsWith("5")) return "MasterCard";
        if (number.startsWith("37")) return "American Express";
        if (number.startsWith("6")) return "Discover";
        return "Unknown";
    }

    public static boolean validCheck(String number) {
        int sumEven = 0, sumOdd = 0;
        for (int i = number.length() - 2; i >= 0; i -= 2) {
            int doubleVal = Character.getNumericValue(number.charAt(i)) * 2;
            sumEven += (doubleVal > 9) ? doubleVal - 9 : doubleVal;
        }
        for (int i = number.length() - 1; i >= 0; i -= 2) {
            sumOdd += Character.getNumericValue(number.charAt(i));
        }
        return (sumEven + sumOdd) % 10 == 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your credit card number: ");
        String number = scanner.nextLine().trim();

        if (!number.matches("\\d{13,16}")) {
            System.out.println("Invalid input. Card number must be 13–16 digits.");
            return;
        }

        String type = getCardType(number);
        boolean valid = validCheck(number);
        System.out.println("Card Type: " + type);
        System.out.println("Status: " + (valid ? "VALID" : "INVALID"));
    }
}
