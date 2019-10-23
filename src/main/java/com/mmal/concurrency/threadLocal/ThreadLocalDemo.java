package com.mmal.concurrency.threadLocal;

public class ThreadLocalDemo {

    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void set(Long id){
        threadLocal.set(id);
    }

    public static void remove(){
        threadLocal.remove();
    }

    public static Long get(){
        return threadLocal.get();
    }
}
