package com.mmal.concurrency.example.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

@Slf4j
public class AtomicFieldUpdateExample {

    private volatile Integer num = 10;

    public static void main(String[] args) {
        AtomicFieldUpdateExample example = new AtomicFieldUpdateExample();

        AtomicReferenceFieldUpdater updater =
                AtomicReferenceFieldUpdater.newUpdater(AtomicFieldUpdateExample.class, Integer.class,"num");


        updater.compareAndSet(example, 10,2);
        updater.compareAndSet(example, 10,3);
        updater.compareAndSet(example, 2, 4);
        updater.compareAndSet(example, 4,0);
        updater.compareAndSet(example, 4,11);

        log.info("example is {}", updater.get(example));
    }
}
