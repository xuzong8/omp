package com.aaa.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author
 * @ClassName ConfigApp
 * @Description git 功能启动类
 * @date 2020/8/24 9:30
 */
@SpringBootApplication
@EnableConfigServer  // 开启配置服务端功能
public class ConfigApp {
    public static void main(String[] args) {
        SpringApplication.run(com.aaa.config.ConfigApp.class,args);
    }
}
