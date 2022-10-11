package com.jingchao.mybatisplus;

import com.jingchao.mybatisplus.mapper.UserMapper;
import com.jingchao.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MyBatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList(){
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("张三");
        user.setAge(27);
        user.setEmail("zhangsan@gmail.com");
        int result = userMapper.insert(user);
        System.out.println("result = " + result);
        System.out.println("user.getId() = " + user.getId());
    }

    @Test
    public void testDelete(){
        /* int result = userMapper.deleteById(1579632326512488450L);
        System.out.println("result = " + result); */

        /* HashMap<String, Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("age",23);
        int result = userMapper.deleteByMap(map);
        System.out.println("result = " + result); */

        List<Long> list = Arrays.asList(1L, 2L, 3L);
        int result = userMapper.deleteBatchIds(list);
        System.out.println("result = " + result);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(4L);
        user.setName("李四");
        user.setEmail("lisi@qq.com");
        int result = userMapper.updateById(user);
        System.out.println("result = " + result);
    }


    @Test
    public void testSelect(){
        /* User user = userMapper.selectById(1L);
        System.out.println(user); */

        /* List<Long> list = Arrays.asList(1L, 2L, 3L);
        List<User> users = userMapper.selectBatchIds(list);
        users.forEach(System.out::println); */

        /* Map<String, Object> map = new HashMap<>();
        map.put("name","Tom");
        map.put("age",28);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println); */

        /* List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println); */

        Map<String, Object> map = userMapper.selectMapById(1L);
        System.out.println(map);

    }

}
