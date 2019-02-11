package com.pdl.blog.token;

import com.google.common.annotations.Beta;
import com.pdl.blog.exception.TokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.xml.ws.http.HTTPException;
import java.security.Key;
import java.util.Date;
import java.util.Map;


public class Token {

    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String createToken(Map<String,String> map){
        //失效时间
        Date exp = new Date(Long.valueOf(map.get("exp")));
        String jws = Jwts.builder()
                .setSubject("blog")
                .setIssuer("TingLen")
                .setAudience(map.get("username"))
                .setExpiration(exp)
                .signWith(KEY)
                .compact();
        return jws;
    }

    public static boolean Verification(String token){
        Jws<Claims> jws;
        try {
            jws = Jwts.parser()
                    .setSigningKey(KEY)
                    .requireSubject("blog")
                    .requireIssuer("TingLen")
                    .parseClaimsJws(token);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Date getExpDate(String token){
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody().getExpiration();
    }

}
