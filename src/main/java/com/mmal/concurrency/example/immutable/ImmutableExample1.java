package com.mmal.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.mmal.concurrency.annotation.NotThreadSafe;

import java.util.Map;

@NotThreadSafe
public class ImmutableExample1 {

    private final static int i= 2;

    private final static Map<Integer, Integer> map = Maps.newHashMap();


    public static void main(String[] args) {
        System.out.println(map);
    }
}
