package com.mmal.concurrency.example.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class ActomicReferenceExample {

    private static AtomicReference<Integer> atomicReference = new AtomicReference<>(0);

    public static void main(String[] args) {
        atomicReference.compareAndSet(0,2);
        atomicReference.compareAndSet(0,3);
        atomicReference.compareAndSet(3,4);
        atomicReference.compareAndSet(3,5);
        log.info("atomicReference is {}", atomicReference.get());
    }
}
