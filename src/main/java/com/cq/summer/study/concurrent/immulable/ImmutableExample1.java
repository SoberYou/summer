package com.cq.summer.study.concurrent.immulable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
//线程不安全
@Slf4j
public class ImmutableExample1 {

    private final static Integer a = 1;

    private final static String b = "2";

    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
        //基础类型不能修改
//        a = 2; //报错
//        b = "4"; //报错
        //引用类型不能指向另一个对象，但是允许修改里面的值
        //这样就会有线程安全的为题
//        map = Maps.newHashMap(); //报错
        map.put(1,3);
        log.info("{}", map.get(1));
    }

    private void test(final int a){
        //final修饰形参，也是不能够被修改的
//        a = 1; //报错
    }


}
