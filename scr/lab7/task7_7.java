package lab7;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class task7_7 {
    public static void main(String[] args) {
        String filename = "lines.txt";

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(filename))) {
            writer.write("Київ\n");
            writer.write("Адеса\n");
            writer.write("Львів\n");
            writer.write("Вінниця\n");
            System.out.println("Тестовий файл успішно створено!");
        } catch (IOException e) {
            System.out.println("Помилка створення файлу: " + e.getMessage());
        }
        List<String> linesList = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Path.of(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                linesList.add(line);
            }
        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
        }

        System.out.println("\nРядки до сортування:    " + linesList);

        Collections.sort(linesList);

        System.out.println("Рядки після сортування: " + linesList);
    }
}
