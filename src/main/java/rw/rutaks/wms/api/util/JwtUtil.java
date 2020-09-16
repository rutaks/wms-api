package rw.rutaks.wms.api.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import rw.rutaks.wms.api.model.Auth;

@Component
@Slf4j
public class JwtUtil {
  private static final long JWT_TOKEN_DURATION = 1000 * 60 * 60 * 10;

  @Value(("${jwt.secret}"))
  private String secret;

  public String extractSubject(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Long extractUserId(String token) {
    return extractClaim(token, claims -> claims.get("user_id", Long.class));
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  public String generateToken(String username, Auth auth) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("role", auth.getRole());
    claims.put("first_name", auth.getUser().getFirstName());
    claims.put("last_name", auth.getUser().getLastName());
    claims.put("user_id", auth.getUser().getId());
    return createToken(claims, username);
  }

  private String createToken(Map<String, Object> claims, String subject) {
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(JWT_TOKEN_DURATION))
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
  }

  public Boolean validateToken(String token, Auth auth) {
    final String username = extractSubject(token);
    boolean isUsernameCorrect = username.equals(auth.getUser().getEmail());
    return (isUsernameCorrect && !isTokenExpired(token));
  }
}
