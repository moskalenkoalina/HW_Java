package lab7;

import java.util.ArrayList;
import java.util.List;

public class task7_9hw {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(2);
        list.add(8);
        list.add(1);
        list.add(9);
        list.add(4);
        list.add(3);

        int X = 4;

        System.out.println("До перестановки:    " + list);
        System.out.println("Задане число X = " + X);

        int i = 0;
        for (int j = 0; j < list.size(); j++) {
            if (list.get(j) <= X) {
                int temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
                i++;
            }
        }

        System.out.println("Після перестановки: " + list);
    }
}
