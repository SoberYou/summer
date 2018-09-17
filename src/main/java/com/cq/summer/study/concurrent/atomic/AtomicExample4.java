package com.cq.summer.study.concurrent.atomic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

//线程安全的类(演示线程并发)
@Slf4j
public class AtomicExample4 {

    private static final Logger log = LoggerFactory.getLogger(AtomicExample1.class);

//    //1.AtomicReference的使用
//    //定义实例
//    private static AtomicReference<Integer> count = new AtomicReference<>(0);
//
//    public static void main(String[] args) {
//        //如果时前面的值则将count更新为后面的值
//        count.compareAndSet(0,2);//2
//        count.compareAndSet(0,1);
//        count.compareAndSet(1,3);
//        count.compareAndSet(2,4);
//        count.compareAndSet(3,5);
//        log.info("count:{}",count.get());
//    }

    //2.AtomicIntegerFieldUpdater的使用
    //原子性的去更新某个类的实例
    //作用：更新指定某个类的某个字段的值，这个字段要求必须通过volatile修饰，而且不能是静态的
    public static AtomicIntegerFieldUpdater<AtomicExample4> updater
            = AtomicIntegerFieldUpdater.newUpdater(AtomicExample4.class, "count");

    //必须用volatile修饰
    @Getter
    public volatile  int count = 100;

    private static AtomicExample4 atomicExample4 = new AtomicExample4();

    public static void main(String[] args) {
        if(updater.compareAndSet(atomicExample4, 100, 120)){
            log.info("update success, {}", atomicExample4.getCount());
        }

        if(updater.compareAndSet(atomicExample4, 100, 120)){
            log.info("update success, {}", atomicExample4.getCount());
        }else{
            log.info("update fail, {}", atomicExample4.getCount());
        }
    }
}
