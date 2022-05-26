package com.jyl.spring.auth.config.security;

import com.jyl.spring.auth.common.keystore.Keystore;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * packageName    : com.jyl.spring.auth.config.security
 * fileName       : JwtTokenProvider
 * author         : leejaeyoon
 * date           : 2022/05/19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/19        leejaeyoon       최초 생성
 */
@Component
@Slf4j
public class JwtTokenProvider implements InitializingBean {

    private static final String AUTHORITIES_KEY = "auth";
    @Value("${spring.jks.path}")
    private String jksPath;
    private Key key;
    public JwtTokenProvider() {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        var keystore = new Keystore(jksPath, "jks");
        this.key = keystore.getKey();
    }

    public String generateJwtToken(){
        Date now = new Date();
        return Jwts.builder()
                .setSubject("SAMPLE")
                .claim(AUTHORITIES_KEY, "ADMIN")
                .signWith(key, SignatureAlgorithm.RS512)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofSeconds(30).toMillis()))
                .compact();

    }
    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities = Stream.of(claims.get(AUTHORITIES_KEY))
                .map(e-> new SimpleGrantedAuthority("ROLE_"+e))
                .collect(Collectors.toSet());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.error("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.error("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.error("지원되지 않는 JWT 토큰입니다.");
        } catch (Exception e) {
            log.error("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

}
