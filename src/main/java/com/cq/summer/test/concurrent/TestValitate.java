package com.cq.summer.test.concurrent;

public class TestValitate {
    public volatile int inc = 0;
    public void increase(){
        inc = inc + 1;
    }

    public static void main(String[] args) {
        final TestValitate test = new TestValitate();
        for(int i = 0; i < 100; i++){
            new Thread(){
                public void run(){
                    for (int j = 0; j < 1000; j++){
                        test.increase();
                    }
                }
            }.start();
        }

        while(Thread.activeCount() > 2){
            Thread.yield();
        }

        System.err.println(test.inc);
    }
}
