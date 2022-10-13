package com.jingchao.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.jingchao.mybatisplus.enums.SexEnums;
import lombok.*;

@Data
@TableName("t_user")
public class User {

    @TableId(value = "uid")
    private Long id;

    @TableField("user_name")
    private String name;

    private Integer age;

    private String email;

    private SexEnums sex;

    @TableLogic
    private Integer isDelete;
}
