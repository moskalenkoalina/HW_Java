package lab_2;
import java.util.Scanner;

public class task2_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("input number, number of bit and value (0 or 1): ");
        int number = scanner.nextInt();
        int bitIndex = scanner.nextInt();
        int value = scanner.nextInt();

        int mask = 1 << (bitIndex - 1);
        int result;

        if (value == 1) {
            result = number | mask;
        } else {
            result = number & ~mask;
        }

        String hex = "0x" + Integer.toHexString(result).toUpperCase();
        String bin = Integer.toBinaryString(result);

        System.out.println(result + " " + hex + " " + bin);
    }
}
