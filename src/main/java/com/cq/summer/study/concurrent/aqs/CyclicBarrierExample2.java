package com.cq.summer.study.concurrent.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CyclicBarrierExample2 {

    private  final static CyclicBarrier  barrier = new CyclicBarrier(5);

    public static void main(String[] args) throws Exception{
        ExecutorService executor = Executors.newCachedThreadPool();

        for(int i = 0; i < 10; i++){
            final int threadNum = i;
            Thread.sleep(1000);
            executor.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception e) {
                    log.error("exception", e);
                }
            });
        }
    }

    public static void race(int threadNum) throws Exception{
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        //要继续执行 捕捉抛出的异常
        try {
            barrier.await(2000, TimeUnit.MILLISECONDS);
        }catch (BrokenBarrierException e){
            log.warn("BrokenBarrierException", e);
        }catch (TimeoutException e){
            log.warn("TimeoutException", e);
        }
        log.info("{} continue", threadNum);
    }
}
