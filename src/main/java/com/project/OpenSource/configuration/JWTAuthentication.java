package com.project.OpenSource.configuration;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import com.project.OpenSource.model.TokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTAuthentication {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public String getToken(String userId, String role) {
        String jwt = null;
        try {
            long time = System.currentTimeMillis();
            jwt = Jwts.builder().setSubject("users/TzMUocMF4p").setExpiration(new Date(time + time / 3))
                    .claim("user_id", userId).claim("role", role).signWith(SignatureAlgorithm.HS256, "secretKey".getBytes("UTF-8"))
                    .compact();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return jwt;
    }

    public TokenResponse verifyToken(String authToken) {
        TokenResponse tokenResponse = new TokenResponse();
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser().setSigningKey("secretKey".getBytes("UTF-8")).parseClaimsJws(authToken);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        String id =  claims.getBody().get("user_id").toString();
        tokenResponse.setUserId(Integer.parseInt(id));
        String role =  claims.getBody().get("role").toString();
        tokenResponse.setRole(role);
        return tokenResponse;
    }
}
