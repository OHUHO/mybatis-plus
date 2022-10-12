package com.jingchao.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingchao.mybatisplus.mapper.ProductMapper;
import com.jingchao.mybatisplus.mapper.UserMapper;
import com.jingchao.mybatisplus.pojo.Product;
import com.jingchao.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyBatisPlusPluginsTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testPage(){
        Page<User> page = new Page<>(2,3);
        userMapper.selectPage(page, null);
        //获取分页数据
        List<User> list = page.getRecords();
        list.forEach(System.out::println);
        System.out.println("当前页："+page.getCurrent());
        System.out.println("每页显示的条数："+page.getSize());
        System.out.println("总记录数："+page.getTotal());
        System.out.println("总页数："+page.getPages());
        System.out.println("是否有上一页："+page.hasPrevious());
        System.out.println("是否有下一页："+page.hasNext());
    }

    @Test
    public void testPageVo(){
        Page<User> page = new Page<>(1,3);
        userMapper.selectPageVo(page, 20);
        List<User> list = page.getRecords();
        list.forEach(System.out::println);
    }

    @Test
    public void testProduct(){
        // 小李查询商品价格
        Product productLi = productMapper.selectById(1L);
        System.out.println("productLi = " + productLi);

        // 小王查询商品价格
        Product productWang = productMapper.selectById(1L);
        System.out.println("productWang = " + productWang);

        // 小李价格加 50，存入数据库
        productLi.setPrice(productLi.getPrice() + 50);
        productMapper.updateById(productLi);

        // 小王价格减 30，存入数据库
        productWang.setPrice(productWang.getPrice() - 30);
        productMapper.updateById(productWang);

        // 最后结果
        Product product = productMapper.selectById(1L);
        System.out.println("product = " + product);

    }
}
