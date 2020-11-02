package com.example.users.feignclients;

import com.example.users.entity.Product;
import com.example.users.fallback.ProductFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author :  yuhao
 * @date: 2020/10/28
 * @description:
 */
// 调用商品服务的openfeign 组件
@FeignClient(value = "products", fallback = ProductFallBack.class)  // 作用： 用来标识 当前接口是一个feign组件   value: 要访问的服务名
public interface ProductClient {

    @GetMapping("/product/showMsg")
    String showMsg();

    @GetMapping("/product/findAll")
    String findAll();

    // 注意：使用openfeign的get方式传递参数，参数必须通过@requestParams注解进行修饰
    @GetMapping("/product/findOne")
    Map<String, Object> findOne(@RequestParam("productId") String aa); // 表示将aa绑定到请求参数里面的productId

    // 测试pos传递参数
    // 使用openfeign传递对象信息
    @PostMapping("/product/save")
    Map<String, Object> save(@RequestBody Product product);

}
