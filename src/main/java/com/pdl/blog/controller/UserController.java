package com.pdl.blog.controller;

import com.pdl.blog.message.ResMessage;
import com.pdl.blog.pojo.User;
import com.pdl.blog.service.UserService;
import com.pdl.blog.token.Token;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "用户登录")
    @ApiImplicitParam(name = "user", value = "从前端获取的登录user对象", dataType = "User", required = true)
    @PostMapping(value = "/login")
    public ResMessage login(@RequestBody User user, HttpServletResponse response) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        ResMessage loginMessage = new ResMessage();
        String str = user.getPassword();
        byte[] passwordByte = user.getPassword().getBytes("utf-8");
        //获取前端传来的password md5值
        String passwordMd5 = DigestUtils.md5DigestAsHex(passwordByte);
        //调用userservice登录事务
        int i = userService.login(user.getUsername(),passwordMd5);
        if(i > 0 ){
            loginMessage.setSuccess(true);
            loginMessage.setMessage("登录成功");
            //token
            Map<String,String> map = new HashMap<>();

            //username
            map.put("username",user.getUsername());
            //失效时间
            String expTime = String.valueOf(System.currentTimeMillis() + 1000 * 60 * 60);
            map.put("exp",expTime);
            String jwt = Token.createToken(map);
            //cookies
            Cookie cookie = new Cookie("token",jwt);
            cookie.setPath("/");
            response.addCookie(cookie);
            return loginMessage;
        }
        loginMessage.setSuccess(false);
        loginMessage.setMessage("账号或密码错误");
        return loginMessage;
    }
}
