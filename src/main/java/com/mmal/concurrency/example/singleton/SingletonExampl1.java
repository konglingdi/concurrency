package com.mmal.concurrency.example.singleton;

import com.mmal.concurrency.annotation.Recommend;
import com.mmal.concurrency.annotation.ThreadSafe;

/**
 * 懒汉式
 */
@ThreadSafe
public class SingletonExampl1 {

    private SingletonExampl1(){}

    /*
    创建对象的步骤：
    1.分配空间
    2.创建对象
    3.指向内存空间
     */
    // volatile 创建对象时不会发生指令重排
    private static volatile SingletonExampl1 exampl1 = null;

    public static SingletonExampl1 getInstance(){
        if(exampl1 == null){
            synchronized (SingletonExampl1.class) {
                if(exampl1 == null) {
                    exampl1 = new SingletonExampl1();
                }
            }
        }
        return exampl1;
    }

}
