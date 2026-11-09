import java.util.ArrayList;
import java.util.List;

public class LockSolution {
    public static class Lucky {
        private static final int LIMIT = 999_999;
        private static final int BLOCK = 1024;
        private static final Object LOCK = new Object();
        private static int next = 0;

        private static boolean isLucky(int n) {
            int left = (n % 10) + (n / 10) % 10 + (n / 100) % 10;
            int right = (n / 1000) % 10 + (n / 10000) % 10 + (n / 100000) % 10;
            return left == right;
        }

        public static class LuckyThread extends Thread {
            public final List<Integer> incrValues = new ArrayList<>(20_000);

            @Override
            public void run() {
                while (true) {
                    int from, to;
                    synchronized (LOCK) {           // короткая секция: только выдача блока
                        if (next >= LIMIT) return;
                        from = next + 1;
                        to = Math.min(next + BLOCK, LIMIT);
                        next = to;
                    }
                    for (int n = from; n <= to; n++) {   // тяжёлая часть — без блокировки
                        if (isLucky(n)) {
                            incrValues.add(n);
                        }
                    }
                }
            }
        }

        public static void printIncrementsList(LuckyThread... threads) {
            List<Integer> listOfIncrements = new ArrayList<>(55_251);
            for (var luckyThread : threads) {
                listOfIncrements.addAll(luckyThread.incrValues);
            }
            listOfIncrements.sort(null);
            for (var incrementedInt : listOfIncrements) {
                System.out.println(incrementedInt);
            }
            System.out.println("Total: " + listOfIncrements.size());
        }
    }
}