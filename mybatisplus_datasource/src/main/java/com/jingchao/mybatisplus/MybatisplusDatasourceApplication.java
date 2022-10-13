package com.jingchao.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jingchao.mybatisplus.mapper")
public class MybatisplusDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisplusDatasourceApplication.class, args);
    }

}
