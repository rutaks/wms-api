package rw.rutaks.wms.api.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import rw.rutaks.wms.api.model.Auth;

public interface AuthService extends UserDetailsService {
  Auth createAuth(Auth auth);
  Auth getAuthByUsername(String username);
}
