package com.mo.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
public class MoWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(MoWebApplication.class, args);
    }
}
