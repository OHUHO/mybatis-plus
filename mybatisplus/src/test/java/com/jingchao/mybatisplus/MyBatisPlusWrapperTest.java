package com.jingchao.mybatisplus;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jingchao.mybatisplus.mapper.UserMapper;
import com.jingchao.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class MyBatisPlusWrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01(){
        // 查询用户名包含 a , 年龄 20 ~ 30 , 邮箱不为null
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name", "a")
                .between("age", 20, 30)
                .isNotNull("email");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void  test02(){
        // 查询用户信息，按照年龄降序排列，id升序排列
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age")
                .orderByAsc("uid");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test03(){
        // 删除邮箱信息为 null 的记录
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println(result);
    }

    @Test
    public void test04(){
        // 将 (年龄大于 20 并且用户名中包含 a) 或邮箱为 null的用户信息修改
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 20)
                .like("user_name","a")
                .or()
                .isNull("email");
        User user = new User();
        user.setName("小名");
        user.setEmail("jignchao@qq.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println(result);
    }

    @Test
    public void test05(){
        // 将用户名中包含 a 并且（年龄大于20或邮箱为null）的用户信息修改
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // lambda表达式先执行
        queryWrapper.like("user_name", "a")
                .and(i -> i.gt("age", 20)
                        .or()
                        .isNull("email")
                );

        User user = new User();
        user.setAge(18);
        user.setEmail("jingchao.jc@qq.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println(result);
    }

    @Test
    public void test06(){
        // 查询用户的 user_name 和 age 字段
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_name", "age");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void test07(){
        // 查询 id 小于10的用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("uid", "select uid from t_user where uid < 10");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);

    }

    @Test
    public void test08(){
        // 将用户名中包含 a 并且（年龄大于20或邮箱为null）的用户信息修改
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("user_name", "a")
                .and(i -> i.gt("age", 20)
                        .or()
                        .isNull("email")
                );
        updateWrapper.set("user_name", "小红").set("email", "jc@qq.com");
        int result = userMapper.update(null, updateWrapper);
        System.out.println(result);
    }

    @Test
    public void test09(){
        // 定义查询条件
        String username = "a";
        Integer ageBegin = 18;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)){
            queryWrapper.like("user_name", username);
        }
        if (ageBegin != null){
            queryWrapper.ge("age", ageBegin);
        }
        if (ageEnd != null){
            queryWrapper.le("age", ageEnd);
        }
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test10(){
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username), "user_name", username)
                .ge(ageBegin != null, "age", ageBegin)
                .le(ageEnd != null, "age", ageEnd);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test11(){
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username), User::getName, username)
                .ge(ageBegin != null, User::getAge, ageBegin)
                .le(ageEnd != null, User::getAge, ageEnd);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test12(){
        // 将用户名中包含 a 并且（年龄大于20或邮箱为null）的用户信息修改
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.like(User::getName, "a")
                .and(i -> i.gt(User::getAge, 20)
                        .or()
                        .isNull(User::getEmail)
                );
        updateWrapper.set(User::getName, "小红").set(User::getEmail, "jc@qq.com");
        int result = userMapper.update(null, updateWrapper);
        System.out.println(result);
    }

}
