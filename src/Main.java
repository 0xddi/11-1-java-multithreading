
void main() throws InterruptedException {

    // Lock-free решение
    LockFreeSolution.Lucky.LuckyThread t1 = new LockFreeSolution.Lucky.LuckyThread();
    LockFreeSolution.Lucky.LuckyThread t2 = new LockFreeSolution.Lucky.LuckyThread();
    LockFreeSolution.Lucky.LuckyThread t3 = new LockFreeSolution.Lucky.LuckyThread();
    t1.start();
    t2.start();
    t3.start();
    t1.join();
    t2.join();
    t3.join();

    LockFreeSolution.Lucky.printIncrementsList(t1, t2, t3);
    LockFreeSolution.Lucky.printCount();


    // Решение с блокировками
    LockSolution.Lucky.LuckyThread l1 = new LockSolution.Lucky.LuckyThread();
    LockSolution.Lucky.LuckyThread l2 = new LockSolution.Lucky.LuckyThread();
    LockSolution.Lucky.LuckyThread l3 = new LockSolution.Lucky.LuckyThread();
    l1.start();
    l2.start();
    l3.start();
    l1.join();
    l2.join();
    l3.join();


    LockSolution.Lucky.printIncrementsList(l1, l2, l3);





}
