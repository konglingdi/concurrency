package com.mmal.concurrency.commonUnSafe;

import com.mmal.concurrency.annotation.NotThreadSafe;
import com.mmal.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class DateFormatExample2 {

    /**
     * 并发数
     */
    private final static int total = 5000;

    /**
     * 允许的并发数
     */
    private final static int curCount = 200;

//    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newFixedThreadPool(total);

        Semaphore semaphore = new Semaphore(curCount);
        CountDownLatch countDownLatch = new CountDownLatch(total);
        for(int i = 0; i < total; i++){
            executorService.submit(()->{
                try {
                    semaphore.acquire();
                    method();
                    semaphore.release();
                    countDownLatch.countDown();
                }catch (Exception e){
                    e.printStackTrace();
                }

            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }

    public static void method(){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            simpleDateFormat.parse("20191011");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
