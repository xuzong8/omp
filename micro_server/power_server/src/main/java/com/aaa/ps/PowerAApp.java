package com.aaa.ps;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.aaa.ps.dao")
@EnableDiscoveryClient
@EnableSwagger2 // 开启swagger功能
public class PowerAApp {
    public static void main(String[] args) {
        SpringApplication.run(PowerAApp.class,args);
    }
}
