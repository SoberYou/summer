package com.cq.summer.service.impl;

import com.cq.summer.dao.SystemDao;
import com.cq.summer.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("systemServic")
public class SystemServiceImpl implements SystemService{

    @Autowired
    private SystemDao systemDao;

    @Override
    public String test(){
        return systemDao.test();
    }

    @Override
    public String test1(){
        return systemDao.test1();
    }

    @Override
    public String test2(){
        return systemDao.test2();
    }

}
