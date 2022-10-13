package com.jingchao.mybatisx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingchao.mybatisx.pojo.User;
import com.jingchao.mybatisx.service.UserService;
import com.jingchao.mybatisx.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author Aubuary
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2022-10-13 15:07:17
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




