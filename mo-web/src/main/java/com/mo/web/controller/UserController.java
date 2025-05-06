package com.mo.web.controller;

import com.mo.common.result.Result;
import com.mo.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @GetMapping("/info")
    public Result<User> info(){
        return Result.success();
    }
}
