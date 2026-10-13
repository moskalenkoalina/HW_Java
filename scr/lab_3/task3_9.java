package lab_3;

import java.util.Random;
import java.util.Scanner;

public class task3_9 {
    //вивід матриці
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%4d ", matrix[i][j]);
            }
            System.out.println();
        }
    }

    // заповнення
    public static void fillRandom(int[][] matrix, int n) {
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rand.nextInt(2 * n + 1) - n;
            }
        }
    }

    //заповнення з консолі
    public static void fillFromConsole(int[][] matrix, Scanner sc, int n) {
        System.out.println("Введіть елементи матриці (значення від " + (-n) + " до " + n + "):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("a[" + i + "][" + j + "] = ");
                int val = sc.nextInt();

                // Перевірка, інтервал [-n, n]
                while (val < -n || val > n) {
                    System.out.println("Значення має бути від " + (-n) + " до " + n + "! Повторіть спробу:");
                    System.out.print("a[" + i + "][" + j + "] = ");
                    val = sc.nextInt();
                }
                matrix[i][j] = val;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введіть розмірність матриці n: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Розмірність має бути натуральним числом!");
            return;
        }

        int[][] randomMatrix = new int[n][n];
        int[][] consoleMatrix = new int[n][n];

        System.out.println("\n--- Заповнення випадковими числами ---");
        fillRandom(randomMatrix, n);
        printMatrix(randomMatrix);

        System.out.println("\n--- Заповнення з консолі ---");
        fillFromConsole(consoleMatrix, sc, n);

        System.out.println("\nВведена матриця:");
        printMatrix(consoleMatrix);

        sc.close();
    }
}
