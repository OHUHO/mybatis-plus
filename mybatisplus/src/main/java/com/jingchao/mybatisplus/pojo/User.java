package com.jingchao.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@TableName("t_user")
public class User {

    @TableId(value = "uid")
    private Long id;

    private String name;

    private Integer age;

    private String email;

}
