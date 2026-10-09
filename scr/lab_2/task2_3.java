package lab_2;
import java.util.Scanner;

public class task2_3 {

    public static void permute(int n, String current) {
        if (current.length() == n) {
            System.out.print(current + " ");
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!current.contains(String.valueOf(i))) {
                permute(n, current + i);
            }
        }
    }

    public static void arrange(int n, int k, String current) {
        if (current.length() == k) {
            for (int i = 0; i < current.length(); i++) {
                System.out.print(current.charAt(i) + (i < k - 1 ? " " : ""));
            }
            System.out.print(", ");
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!current.contains(String.valueOf(i))) {
                arrange(n, k, current + i);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("input N: ");
        int n = scanner.nextInt();
        System.out.println("permutations:");
        permute(n, "");

        System.out.print("\n\ninput N and K: ");
        int n2 = scanner.nextInt();
        int k2 = scanner.nextInt();
        System.out.println("combinations (" + n2 + "; " + k2 + "):");
        arrange(n2, k2, "");

    }
}
