package lab05_lab06;

import java.util.Scanner;

public class task5_8 {

    static int toNumber(String s) {
        String digits = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                digits = digits + c;
            }
        }
        if (digits.equals("")) {
            return 0;
        }
        return Integer.parseInt(digits);
    }

    static int sum(String a, String b) {
        int x = toNumber(a);
        int y = toNumber(b);
        return x + y;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Введіть перший рядок:");
        String a = in.nextLine();
        System.out.println("Введіть другий рядок:");
        String b = in.nextLine();

        int result = sum(a, b);
        System.out.println("Результат: " + result);
    }
}