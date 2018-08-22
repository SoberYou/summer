package com.cq.summer.datasource.aspect;

import com.cq.summer.datasource.DataSourceNames;
import com.cq.summer.datasource.DynamicDataSource;
import com.cq.summer.datasource.annotation.DataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author summer
 * @Date 2018-08-10 15:00
 * @Description 多数据源 切面处理类
 **/
@Aspect
@Component
public class DataSourceAspect implements Ordered{

    protected Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    @Pointcut("@annotation(com.cq.summer.datasource.annotation.DataSource)")
    public void dataSourcePointCut(){

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        DataSource ds = method.getAnnotation(DataSource.class);
        if(ds == null){
            DynamicDataSource.setDataSource(DataSourceNames.APPLE);
            logger.debug("set datasource is " + DataSourceNames.APPLE);
        }else{
            DynamicDataSource.setDataSource(ds.name());
            logger.debug("set datasource is " + ds.name());
        }

        try{
            return point.proceed();
        }finally {
            DynamicDataSource.clearDataSource();
            logger.debug("clean datasource");
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
