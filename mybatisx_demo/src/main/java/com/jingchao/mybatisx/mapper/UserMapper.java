package com.jingchao.mybatisx.mapper;
import org.apache.ibatis.annotations.Param;

import com.jingchao.mybatisx.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Aubuary
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2022-10-13 15:07:17
* @Entity com.jingchao.mybatisx.pojo.User
*/
public interface UserMapper extends BaseMapper<User> {

    int insertSelective(User user);

    int deleteByUidAndUserName(@Param("uid") Long uid, @Param("userName") String userName);

    int updateAgeAndSexByUid(@Param("age") Integer age, @Param("sex") Integer sex, @Param("uid") Long uid);

    List<User> selectAgeAndSexByAgeBetween(@Param("beginAge") Integer beginAge, @Param("endAge") Integer endAge);

    List<User> selectAllOrderByAgeDesc();

    List<User> selectAllByUserNameBetween(@Param("beginUserName") String beginUserName, @Param("endUserName") String endUserName);
}




