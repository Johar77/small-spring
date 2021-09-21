package com.johar.springframework.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: UserDao
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/19 23:22
 * @Since: 1.0.0
 */
public class UserDao {
    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("1001", "Anna");
        hashMap.put("1002", "Lynn");
        hashMap.put("1003", "Jin");
    }

    public String queryUserName(String id){
        return hashMap.get(id);
    }

}