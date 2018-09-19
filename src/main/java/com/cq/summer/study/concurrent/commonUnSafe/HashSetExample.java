package com.cq.summer.study.concurrent.commonUnSafe;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class HashSetExample {
    //请求总数
    public static int clientTotal = 5000;

    //同事并发执行的线程数
    public static int threadTotal = 200;

    private static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws Exception{
        //定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义型号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //定义计数器闭锁(所有请求完以后统计技术结果)
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0; i < clientTotal; i++){
            final int count = i;
            executorService.execute(() -> {
                try{
                    //在执行add操作之前引入信号量
                    //判断当前线程是否允许被执行，达到一定并发数，add()方法临时被阻塞;acquire()返回值，add()才会被执行
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                }catch (Exception e){
                    log.error("exception", e);
                }
                //每执行完后计数值减一
                countDownLatch.countDown();
            });
        }
        //保证countDownlatch减为0 才向下执行，而减为0前提是所有线程执行完
        countDownLatch.await();
        //线程池执行完后，关闭线程池
        executorService.shutdown();
        //在所有线程都执行完之后，打印计数的值
        log.info("list size {}", set.size());
    }

    private static void update(int i){
        set.add(i);
    }
}
