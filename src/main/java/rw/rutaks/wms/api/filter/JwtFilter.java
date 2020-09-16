package rw.rutaks.wms.api.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import rw.rutaks.wms.api.model.Auth;
import rw.rutaks.wms.api.service.AuthService;
import rw.rutaks.wms.api.util.JwtUtil;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
  @Autowired AuthService authService;
  @Autowired JwtUtil jwtUtil;

  @Override
  protected void doFilterInternal(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      FilterChain filterChain)
      throws ServletException, IOException {
    final String authHeader = httpServletRequest.getHeader("Authorization");
    String username;
    String jwt;
    try {
      if (authHeader != null
          && authHeader.startsWith("Bearer ")
          && SecurityContextHolder.getContext().getAuthentication() == null) {
        jwt = authHeader.substring(7);
        username = jwtUtil.extractSubject(jwt);
        Auth auth = authService.getAuthByUsername(username);
        if (auth != null && jwtUtil.validateToken(jwt, auth)) {
          System.out.println("FFF");
          UsernamePasswordAuthenticationToken token =
              new UsernamePasswordAuthenticationToken(auth, null, auth.getAuthorities());
          token.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
          SecurityContextHolder.getContext().setAuthentication(token);
        }
      }
    } catch (Exception e) {
      log.error("Error validating jwt:", e);
    }
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
