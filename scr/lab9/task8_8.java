package lab9;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class task8_8 {

    // Кількість кас у ресторані та кількість відвідувачів
    private static final int CASHIERS_COUNT = 3;
    private static final int VISITORS_COUNT = 12;

    // Інтервали часу
    private static final int ARRIVAL_MIN = 250;
    private static final int ARRIVAL_MAX = 700;

    private static final int SERVICE_MIN = 900;
    private static final int SERVICE_MAX = 2200;

    private static final int CHECK_INTERVAL = 200; // як часто відвідувач дивиться, чи не з'явилась коротша черга

    private static final Object lock = new Object();

    // Окрема черга для кожної каси
    private static final List<Queue<Visitor>> queues = new ArrayList<>();

    // всі відвідувачі вже з'явилися
    private static boolean allArrived = false;

    static class Visitor extends Thread {
        private final int visitorId;
        private int currentCashier;
        private boolean servingStarted = false;

        Visitor(int visitorId) {
            this.visitorId = visitorId;
        }

        @Override
        public void run() {
            try {
                synchronized (lock) {
                    currentCashier = findShortestQueue();
                    queues.get(currentCashier).add(this);
                    System.out.println("Відвідувач " + visitorId
                            + " став у чергу до каси " + (currentCashier + 1));

                    while (!servingStarted) {
                        int shortest = findShortestQueue();
                        if (queues.get(shortest).size() < queues.get(currentCashier).size()) {
                            queues.get(currentCashier).remove(this);
                            currentCashier = shortest;
                            queues.get(currentCashier).add(this);
                            System.out.println("  Відвідувач " + visitorId
                                    + " перейшов до каси " + (currentCashier + 1));
                            lock.notifyAll();
                        }

                        if (!servingStarted) {
                            lock.wait(CHECK_INTERVAL);
                        }
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Cashier extends Thread {
        private final int cashierId;

        Cashier(int cashierId) {
            this.cashierId = cashierId;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Visitor visitor;

                    synchronized (lock) {
                        while (queues.get(cashierId).isEmpty() && !allArrived) {
                            lock.wait();
                        }

                        if (queues.get(cashierId).isEmpty() && allArrived) {
                            break;
                        }

                        visitor = queues.get(cashierId).poll();
                        visitor.servingStarted = true;
                        System.out.println("Каса " + (cashierId + 1)
                                + " почала обслуговувати відвідувача " + visitor.visitorId);
                        lock.notifyAll();
                    }

                    int serviceTime = ThreadLocalRandom.current()
                            .nextInt(SERVICE_MIN, SERVICE_MAX + 1);
                    Thread.sleep(serviceTime);

                    System.out.println("  Каса " + (cashierId + 1)
                            + " завершила обслуговування відвідувача " + visitor.visitorId);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static int findShortestQueue() {
        int bestIndex = 0;
        for (int i = 1; i < queues.size(); i++) {
            if (queues.get(i).size() < queues.get(bestIndex).size()) {
                bestIndex = i;
            }
        }
        return bestIndex;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < CASHIERS_COUNT; i++) {
            queues.add(new LinkedList<>());
        }

        Cashier[] cashiers = new Cashier[CASHIERS_COUNT];
        for (int i = 0; i < CASHIERS_COUNT; i++) {
            cashiers[i] = new Cashier(i);
            cashiers[i].start();
        }

        Visitor[] visitors = new Visitor[VISITORS_COUNT];
        for (int i = 0; i < VISITORS_COUNT; i++) {
            int arrivalDelay = ThreadLocalRandom.current()
                    .nextInt(ARRIVAL_MIN, ARRIVAL_MAX + 1);
            Thread.sleep(arrivalDelay);

            visitors[i] = new Visitor(i + 1);
            visitors[i].start();
        }

        for (Visitor visitor : visitors) {
            visitor.join();
        }

        synchronized (lock) {
            allArrived = true;
            lock.notifyAll();
        }

        for (Cashier cashier : cashiers) {
            cashier.join();
        }

        System.out.println("Роботу ресторану завершено.");
    }
}