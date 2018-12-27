package com.pdl.blog.service;

import com.pdl.blog.dao.UserMapper;
import com.pdl.blog.pojo.User;
import com.pdl.blog.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public int login(String username,String password){
        UserExample example = new UserExample();
        example.or().andUsernameEqualTo(username).andPasswordEqualTo(password);
        List<User> userList = userMapper.selectByExample(example);
        return userList.size();
    }

}
