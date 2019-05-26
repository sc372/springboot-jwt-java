package sc372.springbootjwtjava.config;

import java.io.Serializable;
import java.util.Date;
import java.util.Arrays;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import sc372.springbootjwtjava.domain.User;
import static sc372.springbootjwtjava.config.Constants.ACCESS_TOKEN_VALIDITY_SECONDS;

@Component
@SuppressWarnings("serial")
public class JwtTokenUtil implements Serializable {

    @Autowired
    private Environment env;

    public String getUserEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFormToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFormToken(String token) {
        return Jwts.parser()
        // .setSigningKey("signingKey")
            .setSigningKey(env.getProperty("signingKey"))
            .parseClaimsJws(token)
            .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generatToken(User user) {
        return doGenerateToken(user.getUserEmail());
    }

    private String doGenerateToken(String subject) {
        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));

        return Jwts.builder()
            .setClaims(claims)
            .setIssuer("https://www.datasangza.com")
            .setIssuedAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS))
            // .signWith(SignatureAlgorithm.HS256, "signingKey")
            .signWith(SignatureAlgorithm.HS256, env.getProperty("signingKey"))
            .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String userEmail = getUserEmailFromToken(token);
        return (userEmail.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}