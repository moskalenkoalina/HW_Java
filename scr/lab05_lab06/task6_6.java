package lab05_lab06;

import java.io.*;
import java.util.ArrayList;

public class task6_6 {

    static void change(String f, String g) throws IOException {
        FileInputStream in = new FileInputStream(f);
        FileOutputStream out = new FileOutputStream(g);

        int b = in.read();
        while (b != -1) {
            if (b == '0') {
                b = '1';
            } else if (b == '1') {
                b = '0';
            }
            out.write(b);
            b = in.read();
        }

        in.close();
        out.close();
    }

    static ArrayList<String> readLines(String name) throws IOException {
        ArrayList<String> list = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(name), "UTF-8"));
        String line = br.readLine();
        while (line != null) {
            list.add(line);
            line = br.readLine();
        }
        br.close();
        return list;
    }

    static void save(ArrayList<String> list, String name) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name));
        oos.writeObject(list);
        oos.close();
    }
    @SuppressWarnings("unchecked")
    static ArrayList<String> load(String name) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name));
        ArrayList<String> list = (ArrayList<String>) ois.readObject();
        ois.close();
        return list;
    }

    public static void main(String[] args) {
        String f = "input1.txt";
        String g = "output.txt";
        String bin = "lines.bin";

        try {
            change(f, g);
            System.out.println("Файл F переписано у файл G");

            ArrayList<String> lines = readLines(g);
            System.out.println("Вміст файлу G:");
            for (int i = 0; i < lines.size(); i++) {
                System.out.println(lines.get(i));
            }

            save(lines, bin);
            ArrayList<String> res = load(bin);

            System.out.println("Прочитано з бінарного файлу:");
            for (int i = 0; i < res.size(); i++) {
                System.out.println(res.get(i));
            }

        } catch (IOException e) {
            System.out.println("Помилка файлу: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}