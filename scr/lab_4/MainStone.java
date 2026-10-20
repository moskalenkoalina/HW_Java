package lab_4;

public class MainStone {

    public static void main(String[] args) {
        // Створюємо масив каменів для нашого намиста
        Stone[] necklace = new Stone[5];
        necklace[0] = new PreciousStone("Діамант", 1.5, 5000, 95);
        necklace[1] = new PreciousStone("Рубін", 2.0, 3000, 85);
        necklace[2] = new SemiPreciousStone("Аметист", 3.5, 200, 70);
        necklace[3] = new SemiPreciousStone("Топаз", 4.0, 150, 90);
        necklace[4] = new PreciousStone("Смарагд", 1.8, 4000, 80);

        System.out.println("=== Усі камені в намисті ===");
        for (int i = 0; i < necklace.length; i++) {
            System.out.println(necklace[i]);
        }

        // 1. Підрахунок загальної ваги і вартості
        double totalWeight = 0;
        double totalPrice = 0;
        for (int i = 0; i < necklace.length; i++) {
            totalWeight += necklace[i].getWeight();
            totalPrice += necklace[i].getTotalPrice();
        }
        System.out.println("\n--- Загальні параметри намиста ---");
        System.out.println("Загальна вага: " + totalWeight + " карат");
        System.out.println("Загальна вартість: " + totalPrice + " $");

        // 2. Сортування каменів у намисті на базі цінності (за ціною за карат — бульбашкою)
        for (int i = 0; i < necklace.length - 1; i++) {
            for (int j = 0; j < necklace.length - i - 1; j++) {
                if (necklace[j].getPricePerCarat() < necklace[j + 1].getPricePerCarat()) {
                    Stone temp = necklace[j];
                    necklace[j] = necklace[j + 1];
                    necklace[j + 1] = temp;
                }
            }
        }

        System.out.println("\n--- Сортування каменів за цінностью (від дорожчих до дешевших) ---");
        for (int i = 0; i < necklace.length; i++) {
            System.out.println(necklace[i]);
        }

        // 3. Знайти камені за заданим діапазоном прозорості (наприклад, від 80% до 90%)
        int minTr = 80;
        int maxTr = 90;
        System.out.println("\n--- Камені з прозорістю від " + minTr + "% до " + maxTr + "% ---");
        boolean found = false;
        for (int i = 0; i < necklace.length; i++) {
            if (necklace[i].getTransparency() >= minTr && necklace[i].getTransparency() <= maxTr) {
                System.out.println(necklace[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Не знайдено каменів у такому діапазоні.");
        }
    }
}