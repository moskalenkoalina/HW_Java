package lab_3;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class task3_8 {public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Введіть розмір масиву: ");
    int n = sc.nextInt();

    if (n <= 0) {
        System.out.println("Масив порожній.");
        return;
    }

    int[] a = new int[n];
    System.out.println("Введіть елементи масиву:");
    for (int i = 0; i < n; i++) {
        a[i] = sc.nextInt();
    }

    if (n == 1) {
        System.out.println("Монотонний відрізок: " + a[0]);
        return;
    }

    List<List<Integer>> segments = new ArrayList<>();

    // Зростаючі відрізки
    int start = 0;
    for (int i = 0; i < n - 1; i++) {
        if (a[i] >= a[i + 1]) {
            if (i > start) {
                List<Integer> seg = new ArrayList<>();
                for (int k = start; k <= i; k++) seg.add(a[k]);
                segments.add(seg);
            }
            start = i + 1;
        }
    }
    if (n - 1 > start) {
        List<Integer> seg = new ArrayList<>();
        for (int k = start; k < n; k++) seg.add(a[k]);
        segments.add(seg);
    }

    // Спадна монотонність
    start = 0;
    for (int i = 0; i < n - 1; i++) {
        if (a[i] <= a[i + 1]) {
            if (i > start) {
                List<Integer> seg = new ArrayList<>();
                for (int k = start; k <= i; k++) seg.add(a[k]);
                segments.add(seg);
            }
            start = i + 1;
        }
    }
    if (n - 1 > start) {
        List<Integer> seg = new ArrayList<>();
        for (int k = start; k < n; k++) seg.add(a[k]);
        segments.add(seg);
    }

    int maxLength = 0;
    for (List<Integer> seg : segments) {
        if (seg.size() > maxLength) {
            maxLength = seg.size();
        }
    }

    System.out.println("Монотонні відрізки максимальної довжини (" + maxLength + "):");
    for (List<Integer> seg : segments) {
        if (seg.size() == maxLength) {
            for (int val : seg) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    sc.close();
}
}
