package com.cq.summer.controller;

import com.cq.summer.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController {

    @Autowired
    private SystemService systemService;

    @RequestMapping("test")
    public String test(){
        return systemService.test();
    }
    @RequestMapping("test1")
    public String test1(){
        return systemService.test1();
    }
    @RequestMapping("test2")
    public String test2(){
        return systemService.test2();
    }

    @RequestMapping("test3")
    public void test3(){
        systemService.test3();
    }
}
