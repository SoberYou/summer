package com.cq.summer.aspect;

import com.cq.summer.annotation.DataSource;
import com.cq.summer.annotation.DynamicDataSource;
import com.cq.summer.config.DataSourceNames;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author summer
 * @Date 2018-08-10 15:00
 * @Description 多数据源 切面处理类
 **/
@Aspect
@Component
public class DataSourceAspect {

    protected Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    @Pointcut("@annotation(com.cq.summer.annotation.DataSource)")
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
}
