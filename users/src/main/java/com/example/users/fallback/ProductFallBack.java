package com.example.users.fallback;

import com.example.users.entity.Product;
import com.example.users.feignclients.ProductClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :  yuhao
 * @date: 2020/11/1
 * @description:
 */
@Component
public class ProductFallBack implements ProductClient {

    private Map<String, Object> result = new HashMap<>();

    @Override
    public String showMsg() {
        return "当前服务已被降级";
    }

    @Override
    public String findAll() {
        result.put("status", false);
        result.put("msg", "当前查询所有不可用， 服务已被降级！！");
        return "当前服务已被降级";
    }

    @Override
    public Map<String, Object> findOne(String aa) {
        result.put("status", false);
        result.put("msg", "当前查询一个不可用， 服务已被降级！！");
        return result;
    }

    @Override
    public Map<String, Object> save(Product product) {
        result.put("status", false);
        result.put("msg", "当前保存不可用， 服务已被降级！！");
        return result;
    }
}
