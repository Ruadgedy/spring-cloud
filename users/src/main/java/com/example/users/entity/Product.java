package com.example.users.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author :  yuhao
 * @date: 2020/10/29
 * @description:
 */
@Data
public class Product {
    private String id;
    private String name;
    private Double price;
    private Date date;
}
