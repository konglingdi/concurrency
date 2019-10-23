package com.mmal.concurrency.example.immutable;

import com.google.common.collect.ImmutableMap;
import com.mmal.concurrency.annotation.ThreadSafe;

@ThreadSafe
public class ImmutableExample3 {


    private static ImmutableMap<Integer, Integer> map = ImmutableMap.of(1,2,3,4,5,6);

    private static ImmutableMap<Integer, Integer> map2 =
            ImmutableMap.<Integer, Integer>builder().putAll(map).build();

    public static void main(String[] args) {
    }
}
