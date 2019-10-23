package com.mmal.concurrency.CollectionSafe;


import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ConcurrentHashMapExample {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new ConcurrentHashMap<>(); // 遍历的同时支持删除操作
//        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < 10; i++){
            map.put(i,i);
        }

        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();

        while(iterator.hasNext()){
            Map.Entry<Integer, Integer> next = iterator.next();
            log.info("key:{},value:{}",next.getKey(),next.getValue());
            map.remove(2);
            map.remove(9);
        }
        System.out.println(map);
    }
}
