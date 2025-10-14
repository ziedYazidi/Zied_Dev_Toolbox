package com.dawden;

import java.util.ArrayList;
import java.util.SequencedCollection;
import java.util.concurrent.*;

public class Java21LTS {
    public static void main(String[] args) {
        virtualThread();
        sequencedCollections();
    }

    private static void virtualThread() {
        Runnable sayHello = () -> System.out.println("Hello Thread 1");
        Thread.ofVirtual().start(sayHello);

        ExecutorService virtualExecutorService = Executors.newVirtualThreadPerTaskExecutor();
        virtualExecutorService.submit(() -> System.out.println("Hello Thread 2"));
    }

    private static void StructuredConcurrency() throws InterruptedException, ExecutionException {
        try(var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            var task1 = scope.fork(() -> "Hello");
            var task2 = scope.fork(() -> "World");

            scope.join();
            scope.throwIfFailed();

            String res = task1.get() + task2.get();
            System.out.println(res);
        }
    }

    private static void sequencedCollections() {
        SequencedCollection<String> list = new ArrayList<>();
        list.addFirst("first");
        list.add("test");
        list.addLast("last");

        System.out.println(list.getFirst());
        System.out.println(list.getLast());
    }
}
