package com.cq.summer;

import com.cq.summer.study.concurrent.threadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {

    //调用方法前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle, RequestHolder.getId()--{}", RequestHolder.getId());
        return true;
    }

    //请求结束 (包括正常 非正常结束)
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        //移除数据
        RequestHolder.remove();
        log.info("afterCompletion");
        return;
    }
}
