package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
     static void main(String[] args) {
        // Create a pool of 4 fixed threads
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        for (int i = 0; i < 10; i++) {
            int taskID = i;
            var threadId = Thread.currentThread().threadId();
            var classLoader = Thread.currentThread().getContextClassLoader();
            Thread.currentThread().setName("King");
            System.out.println("name -> " + Thread.currentThread().getName());
            var stackTrack = Thread.currentThread().getThreadGroup();
            System.out.println("threadId -> " + threadId);
            System.out.println("classLoader -> " + classLoader);
//            System.out.println("state -> " + state);
            System.out.println("group -> " + stackTrack);
            executor.submit(() -> {

                System.out.println("Task " + taskID + " executed by " + Thread.currentThread().getName());
            });
        }

        executor.shutdown(); // Gracefully shut down the pool
    }
}
/*
* ExecutorService
* Executors
* concurrent package
* threadPoolExecutor
* yield()
* locks
* debugging in threads(multithreading)
*var threadId = Thread.currentThread().threadId();
            var classLoader = Thread.currentThread().getContextClassLoader();
            Thread.currentThread().setName("King");
            System.out.println("name -> " + Thread.currentThread().getName());
            var stackTrack = Thread.currentThread().getThreadGroup();
            *
       does exectuor service code runs only once when all the iterations of the loop has finished.
* */

