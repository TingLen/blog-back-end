package com.pdl.blog.controllerInterceptor;

import com.pdl.blog.token.Token;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.Date;

public class MyHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        Date now = new Date();
        Cookie[] cookies = request.getCookies();
        String token = "";
        //获取token
        for(Cookie cookie : cookies){
            if("token".equals(cookie.getName())){
                token = cookie.getValue();
            }
        }
        //验证token,验证失败则返回401错误
        if(!Token.Verification(token)) response.setStatus(401);


        //判断token是否过期,验证失败则返回401错误
        Date exp = Token.getExpDate(token);
//        if(now.equals(exp)) response.setStatus(401);
        response.setStatus(500);
        return true;
    }
}
