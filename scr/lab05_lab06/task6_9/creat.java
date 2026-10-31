package lab05_lab06.task6_9;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class creat {

    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<Student>();

        list.add(new Student("Іван", "Іваненко", 9, 'а'));
        list.add(new Student("Петро", "Іваненко", 9, 'б'));
        list.add(new Student("Олена", "Коваль", 9, 'а'));
        list.add(new Student("Олена", "Коваль", 10, 'а'));
        list.add(new Student("Марія", "Шевченко", 9, 'а'));
        list.add(new Student("Марія", "Шевченко", 9, 'а'));
        list.add(new Student("Андрій", "Бойко", 10, 'б'));
        list.add(new Student("Ірина", "Мельник", 11, 'а'));

        for (int i = 1; i <= 36; i++) {
            list.add(new Student("Імя" + i, "Учень" + i, 11, 'б'));
        }

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("students.bin"));
            out.writeObject(list);
            out.close();
            System.out.println("Файл students.bin створено, учнів: " + list.size());
        } catch (IOException e) {
            System.out.println("Помилка запису: " + e.getMessage());
        }
    }
}
