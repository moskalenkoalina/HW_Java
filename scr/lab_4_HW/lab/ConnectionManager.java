package lab_4_HW.lab;

public class ConnectionManager {
    // Фіксований масив об'єктів
    private static Connection[] pool = {
            new Connection(),
            new Connection(),
            new Connection()
    };

    private static int currentIndex = 0;

    // Статичний метод для отримання об'єкта Connection
    public static Connection getConnection() {
        if (currentIndex < pool.length) {
            return pool[currentIndex++];
        } else {
            System.out.println("Помилка: закінчилися вільні об'єкти Connection! Завершення роботи.");
            System.exit(0);
            return null;
        }
    }

    public static void main(String[] args) {
        Connection c1 = ConnectionManager.getConnection();
        c1.doWork();

        Connection c2 = ConnectionManager.getConnection();
        c2.doWork();

        Connection c3 = ConnectionManager.getConnection();
        c3.doWork();

        // Четвертий виклик перевищує ліміт масиву
        System.out.println("Пробуємо отримати четверте підключення:");
        Connection c4 = ConnectionManager.getConnection();
        c4.doWork();
    }
}