package com.mo.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@Slf4j
@EnableConfigurationProperties
@ServletComponentScan
public class MoWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(MoWebApplication.class, args);
    }
}
