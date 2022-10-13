package com.jingchao.mybatisplus.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum SexEnums {
    MALE(1,"男"),
    FEMALE(2,"nv");

    @EnumValue
    private Integer sex;

    private String sexName;

    SexEnums(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }
}
