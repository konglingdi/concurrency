package com.mmal.concurrency.example.immutable;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

public class ImmutableExample2 {



    public static void main(String[] args) {
        Map<Integer, Integer> map = Maps.newHashMap();
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);

        map = Collections.unmodifiableMap(map);
        map.put(7,8);
    }
}
