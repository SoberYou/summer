package com.cq.summer.study.concurrent.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

//线程安全的类(演示线程并发) sychronized
@Slf4j
public class LockExample5 {

    //请求总数
    public static int clientTotal = 5000;

    //同事并发执行的线程数
    public static int threadTotal = 200;

    public static int count = 0;

    private static StampedLock lock = new StampedLock();

    public static void main(String[] args) throws Exception{
        //定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义型号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //定义计数器闭锁(所有请求完以后统计技术结果)
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0; i < clientTotal; i++){
            executorService.execute(() -> {
                try{
                    //在执行add操作之前引入信号量
                    //判断当前线程是否允许被执行，达到一定并发数，add()方法临时被阻塞;acquire()返回值，add()才会被执行
                    semaphore.acquire();
                    add();
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
        log.info("count:{}",count);
    }

    private synchronized static void add(){
        long stamp = lock.writeLock();
        try {
            count++;
        }finally {
            lock.unlock(stamp);
        }
    }
}
