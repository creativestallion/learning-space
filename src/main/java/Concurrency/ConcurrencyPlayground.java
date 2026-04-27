package Concurrency;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrencyPlayground {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int current = 1; // Start from 1
    private boolean isEvenTurn = false; // 1 is odd, so start with false

    public void startPrinting(int n) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Thread 1: Odd Numbers
        executor.submit(() -> {
            while (current <= n) {
                lock.lock();
                try {
                    while (isEvenTurn && current <= n) condition.await();
                    if (current <= n) {
                        System.out.println("Odd Thread: " + current++);
                        isEvenTurn = true;
                        condition.signal();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        });

        // Thread 2: Even Numbers
        executor.submit(() -> {
            while (current <= n) {
                lock.lock();
                try {
                    while (!isEvenTurn && current <= n) condition.await();
                    if (current <= n) {
                        System.out.println("Even Thread: " + current++);
                        isEvenTurn = false;
                        condition.signal();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        });

        executor.shutdown();
    }

     static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter N: ");
        int n = sc.nextInt();

        new ConcurrencyPlayground().startPrinting(n);
    }
}
