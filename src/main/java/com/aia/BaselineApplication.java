package com.aia;

import org.apache.ibatis.type.MappedJdbcTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.aia.core.dao")
public class BaselineApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaselineApplication.class, args);
    }

}
