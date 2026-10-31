package lab05_lab06.task6_9;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class main {

    // чи є однакові учні
    // mode: 1 - у школі, 2 - у паралельних класах, 3 - в одному класі
    static boolean check(ArrayList<Student> list, int mode, boolean full, int year, char letter) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                Student a = list.get(i);
                Student b = list.get(j);

                if (!a.surname.equals(b.surname)) {
                    continue;
                }
                if (full && !a.name.equals(b.name)) {
                    continue;
                }
                if (mode == 2) {
                    if (a.year != b.year || a.letter == b.letter) {
                        continue;
                    }
                }
                if (mode == 3) {
                    if (a.year != year || a.letter != letter || b.year != year || b.letter != letter) {
                        continue;
                    }
                }
                return true;
            }
        }
        return false;
    }

    // д) класи, де більше 35 учнів
    static void bigClasses(ArrayList<Student> list) {
        ArrayList<String> done = new ArrayList<String>();
        boolean found = false;
        for (int i = 0; i < list.size(); i++) {
            String cl = list.get(i).getClassName();
            if (done.contains(cl)) {
                continue;
            }
            done.add(cl);
            int count = 0;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getClassName().equals(cl)) {
                    count++;
                }
            }
            if (count > 35) {
                System.out.println("Клас " + cl + ": " + count + " учнів");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Таких класів немає");
        }
    }

    // е) учні 9-10 класів, відсортовані по класах
    static ArrayList<Student> grades9and10(ArrayList<Student> list) {
        ArrayList<Student> res = new ArrayList<Student>();
        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);
            if (s.year == 9 || s.year == 10) {
                res.add(s);
            }
        }
        // сортування бульбашкою
        for (int i = 0; i < res.size() - 1; i++) {
            for (int j = 0; j < res.size() - 1 - i; j++) {
                Student a = res.get(j);
                Student b = res.get(j + 1);
                if (a.year > b.year || (a.year == b.year && a.letter > b.letter)) {
                    res.set(j, b);
                    res.set(j + 1, a);
                }
            }
        }
        return res;
    }

    // ж) список учнів класу
    static void printClass(ArrayList<Student> list, int year, char letter) {
        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);
            if (s.year == year && s.letter == letter) {
                String ini = s.name.charAt(0) + ".";
                System.out.println(s.surname + " " + s.name + " | "
                        + s.surname + " " + ini + " | " + ini + s.surname);
            }
        }
    }

    static String yesNo(boolean b) {
        if (b) {
            return "так";
        }
        return "ні";
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        try {
            // читаємо учнів з файлу
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("students.bin"));
            ArrayList<Student> list = (ArrayList<Student>) in.readObject();
            in.close();
            System.out.println("Прочитано учнів: " + list.size());

            int year = 9;
            char letter = 'а';

            System.out.println();
            System.out.println("Однакові прізвища:");
            System.out.println("а) у школі: " + yesNo(check(list, 1, false, 0, ' ')));
            System.out.println("б) у паралельних класах: " + yesNo(check(list, 2, false, 0, ' ')));
            System.out.println("в) у класі " + year + " " + letter + ": "
                    + yesNo(check(list, 3, false, year, letter)));

            System.out.println();
            System.out.println("Однакові ім'я та прізвище:");
            System.out.println("г-а) у школі: " + yesNo(check(list, 1, true, 0, ' ')));
            System.out.println("г-б) у паралельних класах: " + yesNo(check(list, 2, true, 0, ' ')));
            System.out.println("г-в) у класі " + year + " " + letter + ": "
                    + yesNo(check(list, 3, true, year, letter)));

            System.out.println();
            System.out.println("д) класи, де більше 35 учнів:");
            bigClasses(list);

            // е) записуємо 9-10 класи у новий файл
            ArrayList<Student> res = grades9and10(list);
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("students9_10.bin"));
            out.writeObject(res);
            out.close();

            System.out.println();
            System.out.println("е) учні 9-10 класів записано у students9_10.bin:");
            for (int i = 0; i < res.size(); i++) {
                System.out.println(res.get(i));
            }

            System.out.println();
            System.out.println("ж) учні класу " + year + " " + letter + ":");
            printClass(list, year, letter);

        } catch (IOException e) {
            System.out.println("Помилка файлу: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
