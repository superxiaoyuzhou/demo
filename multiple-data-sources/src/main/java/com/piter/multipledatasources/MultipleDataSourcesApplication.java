package com.piter.multipledatasources;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.piter.multipledatasources.mapper")
@SpringBootApplication
public class MultipleDataSourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipleDataSourcesApplication.class, args);
    }

}
