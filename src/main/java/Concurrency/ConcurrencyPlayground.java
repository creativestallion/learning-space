package Concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class ConcurrencyPlayground {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int current = 1; // Start from 1
    private boolean isEvenTurn = false; // 1 is odd, so start with false
    ExecutorService executor = Executors.newFixedThreadPool(2);

    public static List<Integer> squareAll(List<Integer> numbers, ExecutorService executor) {
        List<Future<Integer>> futureList = numbers.stream()
                .filter(Objects::nonNull)
                .map(e -> executor.submit(() -> e*e))
                .toList();

        return futureList.stream()
                .map(e -> {
                    try {
                        return e.get();
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(ex);
                    } catch (ExecutionException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .toList();
    }

    public void startPrinting(int n) {
//        executor.submit(() -> );

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

    public static List<Integer> processAll(List<Integer> numbers, ExecutorService executor) {
        // For each number:
        //   - call riskyLookup(n) asynchronously via supplyAsync
        //   - if it succeeds, use the result
        //   - if it throws, substitute -1 instead of failing
        // Return all results in order.

        final List<CompletableFuture<Integer>> completableFuture = numbers.stream()
                    .filter(Objects::nonNull)
                    .map(e ->  supplyAsync(() -> riskyLookup(e), executor )
                            .exceptionally(ex -> -1))
                    .toList();

        return completableFuture.stream()
                .map(CompletableFuture::join)
                .toList();

    }

    // Simulates an async lookup that can fail
     static int riskyLookup(int n) {
        if (n % 3 == 0) {
            throw new RuntimeException("lookup failed for " + n);
        }
        return n * 10;
    }

    // Simulates: look up a user ID, returns a CompletableFuture
    static CompletableFuture<String> getUserName(int userId) {
        return CompletableFuture.supplyAsync(() -> "user_" + userId);
    }

    // Simulates: given a username, look up their email — ALSO returns a CompletableFuture
    static CompletableFuture<String> getEmailForUser(String username) {
        return CompletableFuture.supplyAsync(() -> username + "@example.com");
    }

    /*
    * Write a method getEmailForUserId(int userId) that returns CompletableFuture<String>
     — the email for that user ID, by chaining getUserName → getEmailForUser.
    * */

    public static CompletableFuture<String> getEmailForUserId(int userId){
        // normal way and this will be sync bc it blocks the code @ get()
//        CompletableFuture<String> userName = getUserName(userId);
//        CompletableFuture<String> email = getEmailForUser(userName.get());
//        return email;
//        return supplyAsync(() -> getUserName(userId))
////                .thenCompose(-> getEmailForUser())
//                .thenApply(e -> {
//                    try {
//                        return getEmailForUser(e.get());
//                    } catch (InterruptedException | ExecutionException ex) {
//                        throw new RuntimeException(ex);
//                    }
//                });
//        return getUserName(userId)
//                .thenApply(username -> {
//                    try {
//                        return getEmailForUser(username).get();  // blocks here!
//                    } catch (InterruptedException | ExecutionException ex) {
//                        throw new RuntimeException(ex);
//                    }
//                });

        return getUserName(userId)
                .thenCompose(ConcurrencyPlayground::getEmailForUser);
    }

    static CompletableFuture<Integer> getPrice(String item) {
        return CompletableFuture.supplyAsync(() -> {
            // simulate lookup
            return item.equals("book") ? 20 : 50;
        });
    }

    static CompletableFuture<Double> getTaxRate(String region) {
        return CompletableFuture.supplyAsync(() -> 0.1); // 10%
    }
    /*
    Write getTotalCost(String item, String region) returning CompletableFuture<Double> — price * (1 + taxRate).
    Both lookups are independent (don't depend on each other's result) and should run concurrently. Which combinator fits this?
     */

    static CompletableFuture<Double> getTotalCost(String item, String region) {
        // total cost = price + tax
        // 1. get price
        // 2. get tax
        // 3. combine
        var cf1 = getPrice(item);
        var cf2 = getTaxRate(region);
        return cf1.thenCombine(cf2, (price, tax) ->{
            return price + (1+tax);
        });
    }


     static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter N: ");
        int n = sc.nextInt();
        new ConcurrencyPlayground().startPrinting(n);
    }
}
