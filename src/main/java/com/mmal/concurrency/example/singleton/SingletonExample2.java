package com.mmal.concurrency.example.singleton;

import com.mmal.concurrency.annotation.Recommend;
import com.mmal.concurrency.annotation.ThreadSafe;

@ThreadSafe
@Recommend
public class SingletonExample2 {

    private SingletonExample2(){

    }

    public static SingletonExample2 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        SingletonExample2 singletonExample2;
        // jvm保证了只会调用一次
        Singleton(){
            singletonExample2 = new SingletonExample2();
        }

        SingletonExample2 getInstance(){
            return singletonExample2;
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {
            SingletonExample2 instance = SingletonExample2.getInstance();
            System.out.println(instance);
        }
    }
}
