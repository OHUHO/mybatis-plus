package com.jingchao.mybatisplus;

import com.jingchao.mybatisplus.pojo.User;
import com.jingchao.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class MyBatisPlusServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void test(){
        long count = userService.count();
        System.out.println(count);
    }

    @Test
    public void testInsertMore(){
        ArrayList<User> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setName("jingchao"+i);
            user.setAge(18+i);
            user.setEmail("jingchao"+i+"@gmail.com");
            list.add(user);
        }
        boolean b = userService.saveBatch(list);
        System.out.println(b);
    }
}
