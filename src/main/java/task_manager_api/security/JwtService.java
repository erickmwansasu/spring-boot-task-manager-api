package task_manager_api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.awt.*;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${app.security.jwt.secret}")
    private String SECRET_KEY;

    public SecretKey getSigningKey() { return Keys.hmacShaKeyFor(SECRET_KEY.getBytes()); }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 10 * 60 * 60))
                .signWith(getSigningKey())
                .compact();
    }

    public String extractUsername(String token) { return extractClaim(token, Claims::getSubject); }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
         Claims claims = Jwts.parser()
                 .verifyWith(getSigningKey())
                 .build()
                 .parseSignedClaims(token)
                 .getPayload();

         return resolver.apply(claims);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String userName = extractUsername(token);
        Date expiration = extractClaim(token, Claims::getExpiration);

        return userName.equals(userDetails.getUsername()) && expiration.after(new Date());
    }

}
