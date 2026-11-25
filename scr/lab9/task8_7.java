package lab9;

import java.util.concurrent.ThreadLocalRandom;

public class task8_7 {

    private static final int OPERATORS_COUNT = 3;
    private static final int CLIENTS_COUNT = 8;
    private static final int T1_ARRIVAL_MIN = 200; // мін. пауза між надходженням дзвінків
    private static final int T1_ARRIVAL_MAX = 700; // макс. пауза між надходженням дзвінків

    private static final int T3_SERVICE_MIN = 1000; // мін. час розмови з оператором
    private static final int T3_SERVICE_MAX = 2500; // макс. час розмови з оператором

    private static final int T5_RETRY_MIN = 500; // мін. час до повторного дзвінка
    private static final int T5_RETRY_MAX = 1200; // макс. час до повторного дзвінка

    private static final Object lock = new Object();

    private static int freeOperators = OPERATORS_COUNT;

    static class Client extends Thread {
        private final int clientId;

        Client(int clientId) {
            this.clientId = clientId;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    boolean gotOperator = false;

                    synchronized (lock) {
                        if (freeOperators > 0) {
                            freeOperators--;
                            gotOperator = true;
                            System.out.println("Клієнт " + clientId
                                    + " додзвонився! (вільних операторів: " + freeOperators + ")");
                        }
                    }

                    if (!gotOperator) {
                        int retryDelay = ThreadLocalRandom.current()
                                .nextInt(T5_RETRY_MIN, T5_RETRY_MAX + 1);
                        System.out.println("Клієнт " + clientId
                                + ": усі оператори зайняті, передзвонить через " + retryDelay + " мс");
                        Thread.sleep(retryDelay);
                        continue;
                    }

                    int serviceTime = ThreadLocalRandom.current()
                            .nextInt(T3_SERVICE_MIN, T3_SERVICE_MAX + 1);
                    Thread.sleep(serviceTime);

                    synchronized (lock) {
                        freeOperators++;
                        System.out.println("  Клієнт " + clientId
                                + " завершив розмову за " + serviceTime + " мс (вільних операторів: "
                                + freeOperators + ")");
                        lock.notifyAll();
                    }

                    break;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Client[] clients = new Client[CLIENTS_COUNT];

        for (int i = 0; i < CLIENTS_COUNT; i++) {
            int arrivalDelay = ThreadLocalRandom.current()
                    .nextInt(T1_ARRIVAL_MIN, T1_ARRIVAL_MAX + 1);
            Thread.sleep(arrivalDelay);

            clients[i] = new Client(i + 1);
            clients[i].start();
        }

        for (Client client : clients) {
            client.join();
        }

        System.out.println("Усі клієнти обслужені. Роботу call-центру завершено.");
    }
}