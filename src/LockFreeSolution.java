import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LockFreeSolution {
    public static class Lucky {
        static final private AtomicInteger x = new AtomicInteger(0);

        static final private AtomicInteger count = new AtomicInteger(0);



        private static boolean isLucky(int n) {
            int left  = (n % 10) + (n / 10) % 10 + (n / 100) % 10;
            int right = (n / 1000) % 10 + (n / 10000) % 10 + (n / 100000) % 10;
            return left == right;
        }

        public static class LuckyThread extends Thread {
            public final List<Integer> incrValues = new ArrayList<>(20_000);


            @Override
            public void run(){
                int currentX;
                while ((currentX = x.incrementAndGet()) <= 999999) {
                    if (isLucky(currentX)) {
                        incrValues.add(currentX);
                        count.incrementAndGet();
                    }
                }
            }
        }


        public static void printIncrementsList(LuckyThread... threads) {
            List<Integer> listOfIncrements = new ArrayList<>(20_000);
            for(var luckyThread : threads) {
                listOfIncrements.addAll(luckyThread.incrValues);
            }

            listOfIncrements.sort(null);
            for (var incrementedInt : listOfIncrements) {
                System.out.println(incrementedInt);
            }
        }

        public static void printCount() {
            System.out.println("Total: " + count);
        }
    }

}
