package com.cq.summer.study.concurrent.syncContainer;

import lombok.extern.slf4j.Slf4j;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
//线程不安全类 同步容器下 不是所有场合都是线程安全的
public class VectorExample2 {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {

        while(true){
            for(int i = 0; i < 10; i++){
                vector.add(i);
            }

            Thread thread1 = new Thread(){
                public void run(){
                    for (int i = 0; i < vector.size(); i++){
                        vector.remove(i);
                    }
                }
            };

            Thread thread2 = new Thread(){
                public void run(){
                    //两个同步方法操作顺序的差异，造成线程不安全
                    for (int i = 0; i < vector.size(); i++){
                        vector.get(i);
                    }
                }
            };

            thread1.start();
            thread2.start();
        }

    }
}
