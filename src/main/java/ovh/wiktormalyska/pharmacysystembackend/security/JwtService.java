package ovh.wiktormalyska.pharmacysystembackend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ovh.wiktormalyska.pharmacysystembackend.user.CustomUserDetails;

@Service
public class JwtService {

  @Value("${jwt.secret}")
  private String secretKey;

  private final long jwtExpiration = 43200000; // 12 hours; in millis

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, @NotNull Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public String generateToken(@NotNull CustomUserDetails userDetails) {
    Collection<String> authorities =
        userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

    Map<String, Object> info = new HashMap<>();
    info.put("authorities", authorities);
    info.put("user_id", userDetails.getId());
    info.put("name", userDetails.getRealName());

    return generateToken(info, userDetails);
  }

  public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    return buildToken(extraClaims, userDetails, jwtExpiration);
  }

  public long getExpirationTime() {
    return jwtExpiration;
  }

  private String buildToken(
      Map<String, Object> extraClaims, @NotNull UserDetails userDetails, long expiration) {
    return Jwts.builder()
        .claims(extraClaims)
        .subject(userDetails.getUsername())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(getSignInKey())
        .compact();
  }

  public boolean isTokenValid(String token, @NotNull UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser()
        .verifyWith((SecretKey) getSignInKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  private @NotNull Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
