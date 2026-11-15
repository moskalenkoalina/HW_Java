package lab7;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class task7_9 {
    public static void main(String[] args) {
        int n = 10000;
        int step = 3;
        long startTimeAL = System.currentTimeMillis();
        int survivorAL = solveWithList(new ArrayList<>(), n, step);
        long endTimeAL = System.currentTimeMillis();

        System.out.println("ArrayList вижив: " + survivorAL + " | Час: " + (endTimeAL - startTimeAL) + " мс");

        long startTimeLL = System.currentTimeMillis();
        int survivorLL = solveWithList(new LinkedList<>(), n, step);
        long endTimeLL = System.currentTimeMillis();

        System.out.println("LinkedList вижив: " + survivorLL + " | Час: " + (endTimeLL - startTimeLL) + " мс");
    }
    public static int solveWithList(List<Integer> list, int n, int step) {
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        int index = 0;
        while (list.size() > 1) {
            index = (index + step - 1) % list.size();
            list.remove(index);
        }

        return list.get(0);
    }
}
