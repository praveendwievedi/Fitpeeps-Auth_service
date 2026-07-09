package reviso_project.auth_service.Configs;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//import reviso_project.auth_service.dtos.User;

@Configuration
//@RequiredArgsConstructor
public class JwtService {
    @Value("${jwt.secret}")
    private  String jwtSecret;
    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    public String buildTokens(UserDetails userDetails){
        return createTokenWithUserDetails(new HashMap<>(),userDetails);
    }

    private String createTokenWithUserDetails(
            Map<String , Object> extraRolesAndClaims,
            UserDetails userDetails) {
        return Jwts.builder()
                .claims(extraRolesAndClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .signWith(getSigningKey())
                .compact();
    }

    private String extractUsername(String  token){
        return extractAllClaims(token).getSubject();
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey() {

        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
