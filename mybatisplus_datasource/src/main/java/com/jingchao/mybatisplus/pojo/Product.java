package com.jingchao.mybatisplus.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Product {

    private Integer id;

    private String name;

    private Integer price;

    private Integer version;

}
