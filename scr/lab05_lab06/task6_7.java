package lab05_lab06;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class task6_7 {

    public static void main(String[] args) {
        String name = "numbers.bin";

        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(name));
            out.writeDouble(1.5);
            out.writeDouble(2.5);
            out.writeDouble(3.25);
            out.writeDouble(4.75);
            out.writeDouble(10);
            out.close();
        } catch (IOException e) {
            System.out.println("Помилка запису: " + e.getMessage());
            return;
        }

        double sum = 0;
        try {
            DataInputStream in = new DataInputStream(new FileInputStream(name));
            try {
                while (true) {
                    double x = in.readDouble();
                    System.out.println("Прочитано: " + x);
                    sum = sum + x;
                }
            } catch (EOFException e) {
                // файл закінчився, виходимо з циклу
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Помилка читання: " + e.getMessage());
            return;
        }

        System.out.println("Сума = " + sum);
    }
}
