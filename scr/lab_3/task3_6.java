package lab_3;

public class task3_6 {
    public static void main(String[] args) {
        String[] masStrings = {
                "Hello",
                "World",
                "Of",
                "Java"
        };

        // 1. Вивід через foreach
        for (String s : masStrings) {
            System.out.print(s + ", ");
        }
        System.out.println();

        // 2. Вивід через for
        for (int i = 0; i < masStrings.length - 1; i++) {
            System.out.print(masStrings[i] + ", ");
        }
        System.out.println(masStrings[masStrings.length - 1]);
        System.out.println();

        // 3. Вивід кожного слова з нового рядка
        for (int i = 0; i < masStrings.length; i++) {
            System.out.println("Index " + i + ": " + masStrings[i]);
        }
    }
}
