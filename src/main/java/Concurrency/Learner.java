package Concurrency;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Learner {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    private final Object lockObject = new Object();
    private final Queue<Integer> queue = new ArrayDeque<>(10);
    private int lockCounter = 0;

    /*
    implement a custom thread-safe counter using synchronized, AtomicInteger,
    and ReentrantLock. Compare performance.
     */
    volatile Integer volatileInteger = 0;

    private void counterUsingVolatile() {
        volatileInteger++;
        System.out.println(volatileInteger);
    }

    final AtomicInteger num = new AtomicInteger(0);

    public void countUsingAtomicInteger() {
        num.incrementAndGet();
        num.addAndGet(10);
        System.out.println(num);
    }

    private int syncCounter = 0;

    public synchronized void countUsingSynchronized() {
        syncCounter++;
        System.out.println(syncCounter);
    }


    public void countUsingReentrantLock() {
        try {
            lock.lock();
            lockCounter++;
            System.out.println(num);
        } finally {
            lock.unlock();
        }
    }

    public void addInQueueUsingSynchronized() throws InterruptedException {
        synchronized (lockObject) {
            while (queue.size() == 10) {
                lockObject.wait();
            }
            queue.add(10);
            lockObject.notifyAll();
        }
    }

    public void removeFromQueueUsingSynchronized() throws InterruptedException {
        synchronized (lockObject) {
            while (queue.isEmpty()) {
                lockObject.wait(); // unlocks the thread and goes to sleep.
            }
            queue.poll();
            lockObject.notifyAll(); // notifying other threads
        }
    }

    public void addInQueueUsingReentrantLock() throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == 10) {
                notFull.await();
            }
            queue.offer(10);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public void removeFromQueueUsingReentrantLock() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            queue.poll();
            notFull.signal();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

}
