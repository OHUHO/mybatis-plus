package com.jingchao.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@TableName("t_user")
public class User {

    private Long id;

    private String name;

    private Integer age;

    private String email;

}
