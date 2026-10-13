package lab_3;
import java.util.Scanner;

public class task3_15_hw {public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Введіть 11-значне число: ");
    long number = sc.nextLong();

    long tmp = number;
    int sumEven = 0;
    int sumOdd = 0;

    for (int pos = 2; pos <= 12; pos++) {
        int digit = (int) (tmp % 10);
        tmp /= 10;

        if (pos % 2 == 0) {
            sumEven += digit;
        } else {
            sumOdd += digit;
        }
    }

    int totalSum = sumOdd + 3 * sumEven;

    int rem = totalSum % 10;
    int d1 = (rem == 0) ? 0 : (10 - rem);

    System.out.printf("Повний UPC: %011d%d\n", number, d1);

    sc.close();
}
}
