package lab05_lab06;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class task5_9 {

    static String[] getWords(String s) {
        s = s.trim();
        if (s.equals("")) {
            return new String[0];
        }
        return s.split(" +");
    }

    static int countWords(List<String> lines) {
        int count = 0;
        for (int i = 0; i < lines.size(); i++) {
            String[] w = getWords(lines.get(i));
            count += w.length;
        }
        return count;
    }

    static String clearLine(String s) {
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

    public static void main(String[] args) {
        Path in = Paths.get("input.txt");
        Path out = Paths.get("output.txt");

        try {
            List<String> lines = Files.readAllLines(in, StandardCharsets.UTF_8);

            System.out.println("Кількість слів у файлі: " + countWords(lines));

            List<String> result = new ArrayList<>();
            for (int i = 0; i < lines.size(); i++) {
                result.add(clearLine(lines.get(i)));
            }

            System.out.println("Файл після очищення:");
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