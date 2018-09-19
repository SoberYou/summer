package com.cq.summer.study.concurrent.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class SemaphoreExample1 {

    private final static int treadCount = 200;

    public static void main(String[] args) throws  Exception{

        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(20);

        for(int i = 0; i < treadCount; i++){
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    //拿到许可
                    semaphore.acquire();
                    //释放许可
                    test(threadNum);
                    semaphore.release();
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
        Thread.sleep(1000);
    }
}
