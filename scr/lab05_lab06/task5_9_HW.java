package lab05_lab06;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class task5_9_HW {

    static String inversion(String s) {
        String res = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            res += s.charAt(i);
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
                String s = lines.get(i);
                result.add("Рядок " + (i + 1) + ": [" + s + "]");

                s = inversion(s);

                result.add("Інверсія:  [" + s + "]");
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