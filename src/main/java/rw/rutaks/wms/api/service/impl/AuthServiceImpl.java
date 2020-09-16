package rw.rutaks.wms.api.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rw.rutaks.wms.api.model.Auth;
import rw.rutaks.wms.api.repo.AuthRepository;
import rw.rutaks.wms.api.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {
  @Autowired private AuthRepository authRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Auth> auth = authRepo.findByUsername(username);
    auth.orElseThrow(() -> new UsernameNotFoundException("Could not find user"));
    return auth.get();
  }

  @Override
  public Auth createAuth(Auth auth) {
    return authRepo.save(auth);
  }

  @Override
  public Auth getAuthByUsername(String username) {
    Optional<Auth> auth = authRepo.findByUsername(username);
    auth.orElseThrow(() -> new UsernameNotFoundException("Could not find user"));
    return auth.get();
  }
}
