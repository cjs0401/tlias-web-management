package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//@SpringBootTest
class TliasWebManagementApplicationTests {

    @Test
    void testGenJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","tom");
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "itheima") //设置header
                .addClaims(claims) //设置playload
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) //有效期 1h
                .compact();
        System.out.println(jwt);
    }

    @Test
    void TestParse() {
        Claims itheima = Jwts.parser().setSigningKey("itheima")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTY5MjI0MjU0OH0.RSuCnV0J2ivfOS9nKRc8qz8yXfRvrEymfn0ZnMrQheo")
                .getBody();
        System.out.println(itheima);
    }

}
