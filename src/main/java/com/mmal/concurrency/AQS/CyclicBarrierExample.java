package com.mmal.concurrency.AQS;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CyclicBarrierExample {

    private final static int COUNT = 20;

    private final static CyclicBarrier barrier = new CyclicBarrier(5,()->{
        log.info("runnable run");
    });

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i = 0; i < 5; i++){
            final int count = i;
            executorService.submit(()-> {
                try {
                    race(count);
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();

    }

    public static void race(int i) throws BrokenBarrierException, InterruptedException {
        log.info("thread{} ready...",i);
        barrier.await();
        log.info("thread{} continue...", i);
    }
}
