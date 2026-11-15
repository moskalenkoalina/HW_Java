package lab7;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class task7_6 {
    public static void main(String[] args) {
        String filename = "data.bin";
        int recordSize = 8;

        try (RandomAccessFile file = new RandomAccessFile(filename, "rw")) {
            file.writeInt(10); file.writeInt(20); // Запис №0
            file.writeInt(30); file.writeInt(40); // Запис №1
            file.writeInt(50); file.writeInt(60); // Запис №2
            System.out.println("Тестовий файл успішно створено!");
        } catch (Exception e) {
            System.out.println("Помилка створення файлу: " + e.getMessage());
            return;
        }

        List<Long> indexList = new ArrayList<>();

        try {
            RandomAccessFile file = new RandomAccessFile(filename, "r");
            long fileLength = file.length();

            long currentPos = 0;
            while (currentPos < fileLength) {
                indexList.add(currentPos);
                currentPos += recordSize;
            }

            System.out.println("Наші індексні позиції у файлі: " + indexList);

            int indexToRead = 1;

            if (indexToRead < indexList.size()) {
                long offset = indexList.get(indexToRead);

                file.seek(offset);

                int val1 = file.readInt();
                int val2 = file.readInt();

                System.out.println("Дані з запису №" + indexToRead + ": " + val1 + ", " + val2);
            }

            file.close();

        } catch (Exception e) {
            System.out.println("Сталася помилка: " + e.getMessage());
        }
    }
}
