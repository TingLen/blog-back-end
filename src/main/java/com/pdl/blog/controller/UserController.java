package com.pdl.blog.controller;

import com.pdl.blog.message.LoginMessage;
import com.pdl.blog.pojo.User;
import com.pdl.blog.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户登录")
    @ApiImplicitParam(name = "user", value = "从前端获取的登录user对象", dataType = "User", required = true)
    @PostMapping(value = "/login")
    public LoginMessage login(@RequestBody User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        LoginMessage loginMessage = new LoginMessage();
        String str = user.getPassword();
        byte[] passwordByte = user.getPassword().getBytes("utf-8");
        //获取前端传来的password md5值
        String passwordMd5 = DigestUtils.md5DigestAsHex(passwordByte);
        //调用userservice登录事务
        int i = userService.login(user.getUsername(),passwordMd5);
        if(i > 0 ){
            loginMessage.setSuccess(true);
            loginMessage.setMessage("登录成功");
            return loginMessage;
        }
        loginMessage.setSuccess(false);
        loginMessage.setMessage("账号或密码错误");
        return loginMessage;
    }
}
