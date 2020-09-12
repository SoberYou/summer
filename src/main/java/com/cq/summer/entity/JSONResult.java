package com.cq.summer.entity;

import java.util.HashMap;
import java.util.Map;

public class JSONResult {
    static Map<String,String>  map = new HashMap<>();

    public Map<String, String> getMap() {
        return map;
    }

    public static JSONResult ok(String info) {
        map.put("info", info);
        return new JSONResult();
    }
}
