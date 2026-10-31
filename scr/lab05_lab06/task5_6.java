package lab05_lab06;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class task5_6 {

    // 1. Виключити всі цифри і подвоїти знаки '+' та '-'
    static String task1(String s) {
        return s.replaceAll("\\d", "")
                .replaceAll("([+-])", "$1$1");
    }

    // 2. Виключити всі '+', безпосередньо за якими стоїть цифра
    static String task2(String s) {
        return s.replaceAll("\\+(?=\\d)", "");
    }

    // 3. Виключити всі літери 'в', безпосередньо перед якими стоїть 'с'
    static String task3(String s) {
        return s.replaceAll("(?<=с)в", "");
    }

    // 4. Замінити всі пари "ph" на 'f'
    static String task4(String s) {
        return s.replaceAll("ph", "f");
    }

    // 5. З кількох пропусків підряд залишити один
    static String task5(String s) {
        return s.replaceAll(" {2,}", " ");
    }

    public static void main(String[] args) {
        Path inPath = Paths.get("input.txt");
        Path outPath = Paths.get("output.txt");

        try {
            List<String> lines = Files.readAllLines(inPath, StandardCharsets.UTF_8);
            List<String> result = new ArrayList<>();

            int num = 1;
            for (String line : lines) {
                result.add("Рядок " + num + ": " + line);
                result.add("1) " + task1(line));
                result.add("2) " + task2(line));
                result.add("3) " + task3(line));
                result.add("4) " + task4(line));
                result.add("5) " + task5(line));
                result.add("");
                num++;
            }

            for (String r : result) {
                System.out.println(r);
            }

            Files.write(outPath, result, StandardCharsets.UTF_8);
            System.out.println("Результат записано у файл " + outPath.toAbsolutePath());

        } catch (IOException e) {
            System.out.println("Помилка роботи з файлом: " + e.getMessage());
        }
    }
}
