package ru.marketplace.security.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.marketplace.service.UserServiceImpl;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.*;

import static org.springframework.http.ResponseEntity.ok;

@Component
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:Ld0wSkWK0w2lOfDNI1QVv8PhsfXjlK4AKwiat2wSSdW/U6H8MZLExSR14MroIJPOeRU8jz21TmAmy9O6}")
    private String secretKey = "Ld0wSkWK0w2lOfDNI1QVv8PhsfXjlK4AKwiat2wSSdW/U6H8MZLExSR14MroIJPOeRU8jz21TmAmy9O6";

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000; // 1h
    //
    @Autowired
    private UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);

        Date now = new Date();
        Date validity = new Date(System.currentTimeMillis() + validityInMilliseconds);
        String token = Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .claim("refreshToken", Jwts.builder()//
                        .setClaims(claims)//
                        .setIssuedAt(now)//
                        .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds*60*24))//
                        .signWith(SignatureAlgorithm.HS256, secretKey)//
                        .compact())
                .claim("refreshToken-expired", new Date(System.currentTimeMillis() + validityInMilliseconds*60*24))
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
        System.out.println(token);
        return token;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String getRefreshToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody();

        return claims.get("refreshToken").toString();

    }

    public Date getTokenExpiredTime(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody();
        return claims.getExpiration();

    }

    public Object getRefreshTokenExpiredTime(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody();
        return claims.get("refreshToken-expired");

    }

    public String resolveToken(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        if (token != null) {
            return token;
        }
        return null;
    }


    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        }
    }

    public Map<Object, Object> createTokenModel(String username, String token) {
        Map<Object, Object> model = new HashMap<>();
        model.put("userName", username);
        model.put("tokenAccess", token);
        model.put("tokenAccessExpiration", getTokenExpiredTime(token));
        model.put("refreshTokenAccess", getRefreshToken(token));
        model.put("refreshTokenAccessExpiration", getRefreshTokenExpiredTime(token));
        return model;
    }

}
