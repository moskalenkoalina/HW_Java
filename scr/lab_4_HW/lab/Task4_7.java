package lab_4_HW.lab;

public class Task4_7 {
    public static void main(String[] args) {
        // Створюємо масив об'єктів
        Quadrangle[] shapes = new Quadrangle[6];
        shapes[0] = new Square(4);
        shapes[1] = new Square(3);
        shapes[2] = new Rectangle(3, 6);
        shapes[3] = new Rectangle(2, 5);
        shapes[4] = new Rhombus(5, 4);
        shapes[5] = new CustomQuadrangle(3, 4, 5, 6, 10);

        // Змінні для підрахунку кількості кожного типу
        int squareCount = 0;
        int rectangleCount = 0;
        int rhombusCount = 0;
        int customCount = 0;

        // Рахуємо кількість через звичайний цикл
        for (int i = 0; i < shapes.length; i++) {
            if (shapes[i] instanceof Square) {
                squareCount++;
            } else if (shapes[i] instanceof Rectangle) {
                rectangleCount++;
            } else if (shapes[i] instanceof Rhombus) {
                rhombusCount++;
            } else {
                customCount++;
            }
        }

        System.out.println("=== Кількість фігур різного типу ===");
        System.out.println("Квадратів: " + squareCount);
        System.out.println("Прямокутників: " + rectangleCount);
        System.out.println("Ромбів: " + rhombusCount);
        System.out.println("Довільних: " + customCount);

        // Шукаємо найбільший і найменший прямокутник за площею
        Rectangle maxRect = null;
        Rectangle minRect = null;

        for (int i = 0; i < shapes.length; i++) {
            if (shapes[i] instanceof Rectangle) {
                Rectangle r = (Rectangle) shapes[i];
                if (maxRect == null || r.getArea() > maxRect.getArea()) {
                    maxRect = r;
                }
                if (minRect == null || r.getArea() < minRect.getArea()) {
                    minRect = r;
                }
            }
        }

        System.out.println("\n=== Аналіз прямокутників за площею ===");
        if (maxRect != null) {
            System.out.println("Найбільший прямокутник: " + maxRect);
            System.out.println("Найменший прямокутник: " + minRect);
        } else {
            System.out.println("Прямокутників у масиві немає.");
        }
    }
}
