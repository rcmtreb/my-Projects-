import java.util.Scanner;

public class DecimalToBinaryOctal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a decimal number: ");
        int decimal = scanner.nextInt();

        String binary = convertToBinary(decimal);
        String octal = convertToOctal(decimal);

        System.out.println("Binary: " + binary);
        System.out.println("Octal: " + octal);
    }

    public static String convertToBinary(int decimal) {
        if (decimal == 0) {
            return "0";
        }

        StringBuilder binary = new StringBuilder();
        int temp = decimal;
        while (temp != 0) {
            binary.insert(0, temp % 2);
            temp /= 2;
        }
        return binary.toString();
    }

    public static String convertToOctal(int decimal) {
        if (decimal == 0) {
            return "0";
        }

        StringBuilder octal = new StringBuilder();
        int temp = decimal;
        while (temp != 0) {
            octal.insert(0, temp % 8);
            temp /= 8;
        }
        return octal.toString();
    }
}