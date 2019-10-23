package com.mmal.concurrency.AQS;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SemaphoreExample {

    private final static int COUNT = 20;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);
        for(int i = 0 ; i < COUNT; i ++) {
            final int count = i;
            executorService.submit(() -> {
                try {
                    if(semaphore.tryAcquire(3000, TimeUnit.MILLISECONDS)) {
                        test(count);
                        semaphore.release();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }

    public static void test(int i) throws InterruptedException {
        Thread.sleep(1000);
        log.info("{}", i);
    }
}
