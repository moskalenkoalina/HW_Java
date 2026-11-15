package lab7;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class task7_8 {
    public static void main(String[] args) {
        String filename = "text.txt";
        if (args.length == 0) {
            args = new String[]{"java", "текст", "програма"};
            System.out.println("Аргументи з командного рядка не передано. Використовуємо демонстраційні: java, текст, програма\n");
        }

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(filename))) {
            writer.write("Java — це чудова мова програмування. Ця програма створена для того, щоб аналізувати текст. JAVA рулить!");
            System.out.println("Тестовий файл успішно створено!");
        } catch (IOException e) {
            System.out.println("Помилка створення файлу: " + e.getMessage());
            return;
        }

        List<String> allWords = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Path.of(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.toLowerCase().replaceAll("[^a-zA-Zа-яА-ЯёЁіІїЇєЄґҐ']+", " ");

                String[] words = line.trim().split("\\s+");
                for (String w : words) {
                    if (!w.isEmpty()) {
                        allWords.add(w);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
            return;
        }

        int totalWords = allWords.size();
        System.out.println("Загальна кількість слів у тексті: " + totalWords);

        for (String targetWord : args) {
            String searchWord = targetWord.toLowerCase();
            int count = 0;

            for (String w : allWords) {
                if (w.equals(searchWord)) {
                    count++;
                }
            }

            double relativeFrequency = totalWords > 0 ? (double) count / totalWords : 0.0;

            System.out.printf("Слово '%s': зустрічається %d раз(и), відносна частота = %.4f (%.2f%%)%n",
                    targetWord, count, relativeFrequency, relativeFrequency * 100);
        }
    }
}
