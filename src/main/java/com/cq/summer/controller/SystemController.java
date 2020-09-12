package com.cq.summer.controller;

import com.cq.summer.entity.QueryParam;
import com.cq.summer.entity.ResponseEntity;
import com.cq.summer.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("test4")
    public ResponseEntity test4(QueryParam queryParam){
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        for(int i = 0 ; i < 10; i++){
           map = new HashMap<>();
            map.put("userName", queryParam.getUserName() + i);
            map.put("shopCode", queryParam.getShopCode() + i);
            list.add(map);
        }
        list.add(map);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("startDate", queryParam.getStartDate());
        resultMap.put("endDate", queryParam.getEndDate());
        resultMap.put("list", list);
        ResponseEntity res = new ResponseEntity();
        res.setCode("200");
        res.setData(resultMap);
        return res;
    }
}
