package lab_3;
import java.util.Scanner;

public class task3_7 {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int lineCount = 0;
    int wordCount = 0;
    int charCount = 0;

    System.out.println("Enter text (press Enter on empty line to finish):");

    while (sc.hasNextLine()) {
        String input = sc.nextLine();

        // Якщо рядок порожній — виходимо з циклу
        if (input.isEmpty()) {
            break;
        }

        lineCount++;
        charCount += input.length();

        String trimmed = input.trim();
        if (!trimmed.isEmpty()) {
            wordCount += trimmed.split("\\s+").length;
        }
    }
    System.out.println("Number of lines: " + lineCount);
    System.out.println("Number of words: " + wordCount);
    System.out.println("Number of characters: " + charCount);

    sc.close();
}
}
