package lab_02;
import java.util.Scanner;

public class task02_4 {
    public static long factor(int n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }

    public static long factorRec(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorRec(n - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть число: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Число не натуральне!");
        } else {
            int n = scanner.nextInt();

            if (n <= 0) {
                System.out.println("Число не натуральне!");
            } else {
                long result1 = factor(n);
                long result2 = factorRec(n);

                System.out.println("Факторіал через цикл: " + result1);
                System.out.println("Факторіал через рекурсію: " + result2);
            }
        }

    }
}
