package rw.rutaks.wms.api.mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import rw.rutaks.wms.api.enums.Role;
import rw.rutaks.wms.api.model.Auth;
import rw.rutaks.wms.api.model.SuperAdmin;
import rw.rutaks.wms.api.service.impl.AuthServiceImpl;
import rw.rutaks.wms.api.service.impl.SuperAdminServiceImpl;

@Component
public class Mocks {
  @Autowired private SuperAdminServiceImpl superAdminService;
  @Autowired private AuthServiceImpl authService;

  public void createSuperAdmin() {
    SuperAdmin superAdmin = new SuperAdmin();
    superAdmin.setFirstName("Super");
    superAdmin.setLastName("God");
    superAdmin.setEmail("rootsum.dev@gmail.com");
    superAdmin.setPhoneNumber("+250782697954");
    superAdminService.createSuperAdmin(superAdmin);

    Auth superAdminAuth = new Auth();
    superAdminAuth.setUsername(superAdmin.getEmail());
    superAdminAuth.setPassword(new BCryptPasswordEncoder().encode("Password123!"));
    superAdminAuth.setRole(Role.SUPER_ADMIN);
    superAdminAuth.setUser(superAdmin);
    authService.createAuth(superAdminAuth);
  }
}
