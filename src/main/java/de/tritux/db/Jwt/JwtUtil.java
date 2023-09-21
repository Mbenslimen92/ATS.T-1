package de.tritux.db.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import de.tritux.db.entities.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class JwtUtil {

    private String secret = "404E635266556A586E32723575387777413F4428472B4B6250645367566B5970";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
	private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

	private Boolean isTokenExpired(String token) {
	    try {
	        return extractExpiration(token).before(new Date());
	    } catch (ExpiredJwtException ex) {
	        return true; // Gérer l'expiration du JWT
	    }
	}


    public String generateToken(String string) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, string);
    }

	private String createToken(Map<String, Object> claims, String string) {

        return Jwts.builder()
        		.setClaims(claims)
        		.setSubject(string.toString())
        		
        		.setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 100))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

   
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String Mail = extractUsername(token);
        return (Mail.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
