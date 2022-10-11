package com.jingchao.mybatisplus.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jingchao.mybatisplus.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据 id 查询用户信息为 map 集合
     * @param id
     * @return
     */
    Map<String, Object> selectMapById(Long id);



}
