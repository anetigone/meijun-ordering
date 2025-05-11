package com.mo.web;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan("com.mo.service, com.mo.common")
@MapperScan("com.mo.service.mapper")
@EnableTransactionManagement
@Slf4j
@EnableConfigurationProperties
@ServletComponentScan
public class MoWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(MoWebApplication.class, args);
        log.info("启动成功");
    }
}
