package com.pdl.blog;

import com.pdl.blog.token.Token;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenVerify {

    @Test
    public void verify(){
        String s = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJibG9nIiwiaXNzIjoiVGluZ0xlbiIsImF1ZCI6ImRpbmdsaW5sb3ZlcyIsImV4cCI6MTU0OTE3MDQxMH0.CepqYzYa0dsoO3AeyoPAoL0V-TLGGdHsVRZFy6R8XRo";
        Token token = new Token();
        Assert.assertTrue(token.Verification(s));
    }
}
