package com.dev.restfullapi.rest.jwt;

import com.dev.restfullapi.domain.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class JwtService {

    @Value("${security.jwt.expiration}")
    private String expiration;
    @Value("${security.jwt.secretKey}")
    private String secretKey;

    public String generateToken(UserDetails user) {
        var expirationLong = Long.parseLong(this.expiration);
        var expirationDateTime = LocalDateTime.now().plusMinutes(expirationLong);
        var expiration = Date.from(expirationDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return Jwts
                .builder()
                .setSubject(user.getUsername())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, this.secretKey)
                .compact();
    }

    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(this.secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenIsValid(String token) {
        try {
            var claims = this.getClaims(token);
            var expiration = claims.getExpiration();
            var expirationDateTime = expiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(expirationDateTime);
        } catch (Exception exception) {
            System.out.println("exception" + exception);
            return false;
        }
    }

    public String getUserName(String token) throws ExpiredJwtException {
        return this.getClaims(token).getSubject();
    }
}
