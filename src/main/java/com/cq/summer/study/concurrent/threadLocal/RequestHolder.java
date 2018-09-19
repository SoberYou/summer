package com.cq.summer.study.concurrent.threadLocal;

public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id){
        requestHolder.set(id);
    }

    public static Long getId(){
        return requestHolder.get();
    }

    //不移除会造成内存泄漏
    public static void remove(){
        requestHolder.remove();
    }
}
