package com.jingchao.mybatisplus.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingchao.mybatisplus.mapper.ProductMapper;
import com.jingchao.mybatisplus.pojo.Product;
import com.jingchao.mybatisplus.service.ProductService;
import org.springframework.stereotype.Service;

@Service
@DS("slave_1")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
