import java.util.Scanner;

public class DecimalConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a decimal number: ");
        int decimal = scanner.nextInt();
        
        System.out.println("Binary: " + decimalToBinary(decimal));
        System.out.println("Octal: " + decimalToOctal(decimal));
        System.out.println("Hexadecimal: " + decimalToHexadecimal(decimal));
    }
    
    public static String decimalToBinary(int decimal) {
        return Integer.toBinaryString(decimal);
    }
    
    public static String decimalToOctal(int decimal) {
        return Integer.toOctalString(decimal);
    }
    
    public static String decimalToHexadecimal(int decimal) {
        return Integer.toHexString(decimal);
    }
}