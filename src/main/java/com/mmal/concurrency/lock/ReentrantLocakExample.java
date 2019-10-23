package com.mmal.concurrency.lock;

import com.mmal.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@ThreadSafe
public class ReentrantLocakExample {
    /**
     * 并发数
     */
    private final static int total = 5000;

    /**
     * 允许的并发数
     */
    private final static int curCount = 200;

    private static List<Integer> list = new ArrayList<>();

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService =
                Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(curCount);
        CountDownLatch countDownLatch = new CountDownLatch(total);
        for(int i = 0; i < total; i++) {
            final int count = i;
            executorService.submit(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                    countDownLatch.countDown();
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("集合的size={}", list.size());
    }

    public static void update(int i){
        lock.lock();
        list.add(i);
        lock.unlock();
    }
}
