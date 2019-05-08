package com.piter.hikaricptest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.piter.hikaricptest.mapper")
public class HikaricpTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(HikaricpTestApplication.class, args);
    }

}
