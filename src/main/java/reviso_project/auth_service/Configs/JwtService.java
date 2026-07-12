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
    @Value("${jwt.acess-token-expiration}")
    private long accessTokenExpiration;

    public String buildTokens(UserDetails userDetails,int val){
        return createTokenWithUserDetails(new HashMap<>(),userDetails,val);
    }

    private String createTokenWithUserDetails(
            Map<String , Object> extraRolesAndClaims,
            UserDetails userDetails,
            int val
            ) {
        long expiration= (val > 0 ? refreshTokenExpiration : accessTokenExpiration);
        return Jwts.builder()
                .claims(extraRolesAndClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
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
