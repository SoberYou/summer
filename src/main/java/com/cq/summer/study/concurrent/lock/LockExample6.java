package com.cq.summer.study.concurrent.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class LockExample6 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            try {
                reentrantLock.lock();//a1.线程1加入AQS的等待队列里面
                log.info("wait signal"); // 1 //a2.输出 wait signal
                condition.await();   //a3.线程1从AQS队列中移除了，对应的操作是锁的释放，加入到condition的等待队列里面，需要信号唤醒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("get signal"); // 4
            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
            reentrantLock.lock(); //a4.获取锁，加入AQS的等待队列中
            log.info("get lock"); // 2  //a5.输出get lock
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll();  //a6.发送信号 condition里面有我们的线程1的等待节点，被取出来放到AQS的等待节点，
                                    // 这个时候线程1没有被唤醒
            log.info("send signal ~ "); // 3 //a7.输出 send signal
            reentrantLock.unlock();  //a8.释放锁,线程1被唤醒，继续执行
        }).start();
    }
}
