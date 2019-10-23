package com.mmal.concurrency.CollectionSafe;

import com.mmal.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.*;

@Slf4j
@ThreadSafe
public class CopyOnWriteArraySetExample {
    /**
     * 并发数
     */
    private final static int total = 5000;

    /**
     * 允许的并发数
     */
    private final static int curCount = 200;

    private static Set<Integer> set = new CopyOnWriteArraySet<>();

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
        log.info("集合的size={}", set.size());
    }

    public static void update(int i){
        set.add(i);
    }
}
