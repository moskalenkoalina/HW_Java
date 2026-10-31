package lab05_lab06;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class task5_7_HW {

    static final int N = 80;

    static String[] getWords(String s) {
        s = s.trim();
        if (s.equals("")) {
            return new String[0];
        }
        return s.split(" +");
    }

    // 1. найдовше слово
    static String longest(String s) {
        String[] w = getWords(s);
        String max = "";
        for (int i = 0; i < w.length; i++) {
            if (w[i].length() > max.length()) {
                max = w[i];
            }
        }
        return max;
    }

    // 2. кількість слів
    static int count(String s) {
        return getWords(s).length;
    }

    // 3. видалити зайві пропуски і слова з однієї літери
    static String delOneLetter(String s) {
        String[] w = getWords(s);
        String res = "";
        for (int i = 0; i < w.length; i++) {
            if (w[i].length() == 1 && Character.isLetter(w[i].charAt(0))) {
                continue;
            }
            if (!res.equals("")) {
                res += " ";
            }
            res += w[i];
        }
        return res;
    }

    // 4. видалити пропуски на початку, у кінці і між словами (крім одного)
    static String delSpaces(String s) {
        String[] w = getWords(s);
        String res = "";
        for (int i = 0; i < w.length; i++) {
            res += w[i];
            if (i < w.length - 1) {
                res += " ";
            }
        }
        return res;
    }

    // 5. вставити пропуски так, щоб довжина рядка була 80
    static String addSpaces(String s) {
        String[] w = getWords(s);
        if (w.length < 2) {
            return s;
        }

        int len = 0;
        for (int i = 0; i < w.length; i++) {
            len += w[i].length();
        }

        int gaps = w.length - 1;
        int spaces = N - len;
        if (spaces < gaps) {
            return delSpaces(s);
        }

        int each = spaces / gaps;
        int rest = spaces % gaps;

        String res = "";
        for (int i = 0; i < w.length; i++) {
            res += w[i];
            if (i < gaps) {
                int k = each;
                if (i < rest) {
                    k++;
                }
                for (int j = 0; j < k; j++) {
                    res += " ";
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Path in = Paths.get("input.txt");
        Path out = Paths.get("output.txt");

        try {
            List<String> lines = Files.readAllLines(in, StandardCharsets.UTF_8);
            List<String> result = new ArrayList<>();

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                result.add("Рядок " + (i + 1) + ": [" + line + "]");
                result.add("1) Найдовше слово: " + longest(line));
                result.add("2) Кількість слів: " + count(line));
                result.add("3) Без слів з однієї літери: [" + delOneLetter(line) + "]");
                result.add("4) Без зайвих пропусків: [" + delSpaces(line) + "]");
                String r5 = addSpaces(line);
                result.add("5) Довжина " + r5.length() + ": [" + r5 + "]");
                result.add("");
            }

            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
            }

            Files.write(out, result, StandardCharsets.UTF_8);
            System.out.println("Результат записано у файл output.txt");

        } catch (IOException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}