package com.jingchao.mybatisplus.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingchao.mybatisplus.mapper.UserMapper;
import com.jingchao.mybatisplus.pojo.User;
import com.jingchao.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

@Service
@DS("master")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
