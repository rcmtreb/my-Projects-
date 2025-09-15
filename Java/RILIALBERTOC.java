import java.util.Scanner;

public class DecimalConverter {
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
        return Integer.toBinaryString(decimal);
    }

    public static String convertToOctal(int decimal) {
        return Integer.toOctalString(decimal);
    }
}