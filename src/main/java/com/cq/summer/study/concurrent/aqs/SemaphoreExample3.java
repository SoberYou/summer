package com.cq.summer.study.concurrent.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SemaphoreExample3 {

    private final static int treadCount = 20;

    public static void main(String[] args) throws  Exception{

        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for(int i = 0; i < treadCount; i++){
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    //尝试获得许可，拿到许可执行，没有则直接丢弃
                    if(semaphore.tryAcquire(1, TimeUnit.SECONDS)){
                        test(threadNum);
                        semaphore.release();
                    }
                } catch (Exception e) {
                    log.error("exception", e);
                } finally {

                }
            });
        }

        //支持指定时间的等待

        log.info("finish");
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception{
//        Thread.sleep(20);
        log.info("{}", threadNum);
        Thread.sleep(500);
    }
}
