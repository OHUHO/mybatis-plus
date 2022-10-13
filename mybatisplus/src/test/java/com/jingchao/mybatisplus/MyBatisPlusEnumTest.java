package com.jingchao.mybatisplus;


import com.jingchao.mybatisplus.enums.SexEnums;
import com.jingchao.mybatisplus.mapper.UserMapper;
import com.jingchao.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisPlusEnumTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        User user = new User();
        user.setName("admin");
        user.setAge(25);
        //设置性别信息为枚举项，会将@EnumValue注解所标识的属性值存储到数据库
        user.setSex(SexEnums.MALE);
        int result = userMapper.insert(user);
        System.out.println(result);
    }
}
