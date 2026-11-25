package lab9;

import java.util.LinkedList;
import java.util.Queue;

public class task8_6 {

    private static final int T1 = 500;  // як часто з'являється нове повідомлення
    private static final int T2 = 1000; // скільки часу займає обробка
    private static final int TOTAL_MESSAGES = 6;

    // Спільна черга повідомлень
    private static final Queue<String> queue = new LinkedList<>();
    private static boolean finished = false;

    // Потік, який генерує повідомлення
    static class Producer extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 1; i <= TOTAL_MESSAGES; i++) {
                    Thread.sleep(T1);
                    String msg = "Повідомлення #" + i;

                    synchronized (queue) {
                        queue.add(msg);
                        System.out.println("Надійшло: " + msg + " (у черзі: " + queue.size() + ")");
                        queue.notify();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (queue) {
                finished = true;
                queue.notify();
            }
        }
    }

    // Потік, який обробляє повідомлення по черзі
    static class Consumer extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    String msg;

                    synchronized (queue) {
                        while (queue.isEmpty() && !finished) {
                            queue.wait(); // чекаємо, поки з'явиться повідомлення
                        }

                        if (queue.isEmpty() && finished) {
                            break;
                        }

                        msg = queue.poll();
                    }

                    Thread.sleep(T2);
                    System.out.println("  Оброблено: " + msg);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("Усі повідомлення оброблено.");
    }
}