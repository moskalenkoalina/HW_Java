package lab_2;
import java.util.Arrays;

public class task2_11_hw {
    private static boolean hasSameDigits(int orig1, int orig2, int target) {
        String fangs = String.valueOf(orig1) + String.valueOf(orig2);
        String number = String.valueOf(target);

        char[] fangsArray = fangs.toCharArray();
        char[] numberArray = number.toCharArray();

        Arrays.sort(fangsArray);
        Arrays.sort(numberArray);

        return Arrays.equals(fangsArray, numberArray);
    }

    public static void main(String[] args) {
        System.out.println("4-значнi числа-вампiри:");

        for (int a = 10; a < 100; a++) {
            for (int b = a; b < 100; b++) {

                if (a % 10 == 0 && b % 10 == 0) {
                    continue;
                }

                int product = a * b;

                if (product >= 1000 && product <= 9999) {
                    if (hasSameDigits(a, b, product)) {
                        System.out.println(product + " = " + a + " * " + b);
                    }
                }
            }
        }
    }
}
