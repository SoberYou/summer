package com.cq.summer.study.concurrent.atomic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

//线程安全的类(演示线程并发)
@Slf4j
public class AtomicExample4 {
    //定义实例
    private static AtomicReference<Integer> count = new AtomicReference<>(0);

}
