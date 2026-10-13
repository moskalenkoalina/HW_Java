package lab_3;

import java.util.Scanner;

public class task3_11_hw {public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Введіть розмір n: ");
    int n = sc.nextInt();
    double[][] a = new double[n][n];

    System.out.println("Введіть елементи:");
    double normSum = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            a[i][j] = sc.nextDouble();
            normSum += a[i][j] * a[i][j];
        }
    }

    System.out.printf("Норма: %.2f\n", Math.sqrt(normSum));

    double[][] b = new double[n][n];
    for (int i = 0; i < n; i++) {
        System.arraycopy(a[i], 0, b[i], 0, n);
    }

    double det = 1.0;
    for (int i = 0; i < n; i++) {
        int pivot = i;
        for (int j = i + 1; j < n; j++) {
            if (Math.abs(b[j][i]) > Math.abs(b[pivot][i])) {
                pivot = j;
            }
        }

        if (Math.abs(b[pivot][i]) < 1e-9) {
            det = 0;
            break;
        }

        if (i != pivot) {
            double[] temp = b[i];
            b[i] = b[pivot];
            b[pivot] = temp;
            det = -det;
        }

        det *= b[i][i];

        for (int j = i + 1; j < n; j++) {
            double factor = b[j][i] / b[i][i];
            for (int k = i; k < n; k++) {
                b[j][k] -= factor * b[i][k];
            }
        }
    }
    System.out.printf("Визначник: %.2f\n", det);

    // 3. Ущільнення
    boolean[] zeroRows = new boolean[n];
    boolean[] zeroCols = new boolean[n];

    for (int i = 0; i < n; i++) {
        zeroRows[i] = true;
        zeroCols[i] = true;
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (a[i][j] != 0) {
                zeroRows[i] = false;
                zeroCols[j] = false;
            }
        }
    }

    System.out.println("Ущільнена матриця:");
    for (int i = 0; i < n; i++) {
        if (zeroRows[i]) continue;
        for (int j = 0; j < n; j++) {
            if (!zeroCols[j]) {
                System.out.printf("%6.2f ", a[i][j]);
            }
        }
        System.out.println();
    }

    sc.close();
}
}
