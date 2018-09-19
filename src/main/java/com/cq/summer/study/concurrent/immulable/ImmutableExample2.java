package com.cq.summer.study.concurrent.immulable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

//线程安全
@Slf4j
public class ImmutableExample2 {


    private static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        //使用 Collections.unmodifiableMap(map)修饰的map也不可被修改；
        //做put操作时，允许被操作，但是操作时会抛出异常
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1,3);
        log.info("{}", map.get(1));
    }


}
