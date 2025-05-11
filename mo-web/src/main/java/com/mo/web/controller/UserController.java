package com.mo.web.controller;

import com.mo.api.service.UserService;
import com.mo.common.context.BaseContext;
import com.mo.common.result.Result;
import com.mo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public Result<User> info(){
        String uuid = BaseContext.getCurrentId();
        if(uuid == null) return Result.error();

        User user = userService.info(uuid);
        return Result.success(user);
    }
}
