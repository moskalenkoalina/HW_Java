package lab9;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class task8_9 {

    private static final int LOTS_COUNT = 4;
    private static final int BIDDERS_COUNT = 5;

    // Затримки
    private static final int LOT_DURATION_MS = 1800;
    private static final int BID_INTERVAL_MIN = 200;
    private static final int BID_INTERVAL_MAX = 500;
    private static final int PAYMENT_DEADLINE_MS = 600;
    private static final int PAYMENT_TIME_MIN = 200;
    private static final int PAYMENT_TIME_MAX = 900; // частина оплат буде пізнішою за дедлайн
    private static final int BAN_LOTS = 2; // на скільки наступних лотів відсторонюється боржник

    private static final Object lock = new Object();

    // Поточний стан торгів
    private static int currentLot = 0;
    private static boolean lotOpen = false;
    private static boolean auctionFinished = false;

    private static int highestBid = 0;
    private static int highestBidderId = -1;

    // Скільки ще лотів учасник відсторонений від торгів
    private static final Map<Integer, Integer> bannedUntilLot = new HashMap<>();

    // Дані про переможця
    private static int pendingWinnerId = -1;
    private static int pendingAmount = 0;
    private static int pendingLotNumber = 0;
    private static boolean paymentResolved = true;

    static class Bidder extends Thread {
        private final int bidderId;
        private int myCurrentBidForLot = 0;
        private int lastBidLot = 0;

        Bidder(int bidderId) {
            this.bidderId = bidderId;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    int lotToBid;
                    int nextBidAmount = 0;

                    synchronized (lock) {
                        while (!auctionFinished && (!lotOpen || isBannedForCurrentLot())) {
                            lock.wait(200);
                        }

                        if (auctionFinished) {
                            break;
                        }

                        lotToBid = currentLot;
                        if (lastBidLot != lotToBid) {
                            lastBidLot = lotToBid;
                            myCurrentBidForLot = 0;
                        }

                        int baseAmount = Math.max(highestBid, myCurrentBidForLot);
                        int step = ThreadLocalRandom.current().nextInt(50, 151);
                        nextBidAmount = baseAmount + step;

                        if (lotOpen && currentLot == lotToBid && !isBannedForCurrentLot()) {
                            myCurrentBidForLot = nextBidAmount;
                            highestBid = nextBidAmount;
                            highestBidderId = bidderId;
                            System.out.println("[Лот " + lotToBid + "] Учасник " + bidderId
                                    + " поставив заявку: " + nextBidAmount);
                        }
                    }

                    int pause = ThreadLocalRandom.current().nextInt(BID_INTERVAL_MIN, BID_INTERVAL_MAX + 1);
                    Thread.sleep(pause);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private boolean isBannedForCurrentLot() {
            return bannedUntilLot.getOrDefault(bidderId, 0) > 0;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int id = 1; id <= BIDDERS_COUNT; id++) {
            bannedUntilLot.put(id, 0);
        }

        Bidder[] bidders = new Bidder[BIDDERS_COUNT];
        for (int i = 0; i < BIDDERS_COUNT; i++) {
            bidders[i] = new Bidder(i + 1);
            bidders[i].start();
        }

        for (int lot = 1; lot <= LOTS_COUNT; lot++) {
            synchronized (lock) {
                // Зменшуємо лічильник відсторонення для тих, хто пропустив попередній лот
                if (lot > 1) {
                    for (Map.Entry<Integer, Integer> entry : bannedUntilLot.entrySet()) {
                        if (entry.getValue() > 0) {
                            entry.setValue(entry.getValue() - 1);
                        }
                    }
                }

                currentLot = lot;
                highestBid = 100; 
                highestBidderId = -1;
                lotOpen = true;
                System.out.println("\n=== Відкрито торги за лот #" + lot + " (стартова ціна: " + highestBid + ") ===");
                lock.notifyAll();
            }

            // Тривалість торгів по поточному лоту
            Thread.sleep(LOT_DURATION_MS);

            int winnerId;
            int winningAmount;

            synchronized (lock) {
                lotOpen = false;
                winnerId = highestBidderId;
                winningAmount = highestBid;
                System.out.println("=== Торги за лот #" + lot + " завершено ===");
            }

            if (winnerId == -1) {
                System.out.println("Лот #" + lot + " не знайшов покупця.");
                continue;
            }

            System.out.println("Переможець лоту #" + lot + ": Учасник " + winnerId
                    + " зі ставкою " + winningAmount + ". Очікуємо оплату до "
                    + PAYMENT_DEADLINE_MS + " мс...");

            int actualPaymentTime = ThreadLocalRandom.current()
                    .nextInt(PAYMENT_TIME_MIN, PAYMENT_TIME_MAX + 1);

            boolean paidInTime = simulatePayment(winnerId, winningAmount, lot, actualPaymentTime);

            synchronized (lock) {
                if (paidInTime) {
                    System.out.println("  [Оплата] Учасник " + winnerId
                            + " вчасно оплатив лот #" + lot + " (" + actualPaymentTime + " мс).");
                } else {
                    bannedUntilLot.put(winnerId, BAN_LOTS);
                    System.out.println("  [Штраф] Учасник " + winnerId
                            + " НЕ оплатив лот #" + lot + " вчасно (" + actualPaymentTime + " мс > "
                            + PAYMENT_DEADLINE_MS + " мс) і відсторонюється на " + BAN_LOTS + " лоти!");
                }
                lock.notifyAll();
            }
        }

        synchronized (lock) {
            lotOpen = false;
            auctionFinished = true;
            lock.notifyAll();
        }

        for (Bidder bidder : bidders) {
            bidder.join();
        }

        System.out.println("\nАукціон повністю завершено.");
    }

    private static boolean simulatePayment(int winnerId, int amount, int lot, int paymentDelayMs)
            throws InterruptedException {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < PAYMENT_DEADLINE_MS) {
            long remaining = PAYMENT_DEADLINE_MS - (System.currentTimeMillis() - start);
            if (paymentDelayMs <= remaining) {
                Thread.sleep(paymentDelayMs);
                return true;
            }
            Thread.sleep(Math.min(remaining, 50));
        }
        if (paymentDelayMs > PAYMENT_DEADLINE_MS) {
            Thread.sleep(paymentDelayMs - PAYMENT_DEADLINE_MS);
        }
        return false;
    }
}