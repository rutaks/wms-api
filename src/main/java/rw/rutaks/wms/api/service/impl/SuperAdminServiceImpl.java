package rw.rutaks.wms.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.rutaks.wms.api.model.SuperAdmin;
import rw.rutaks.wms.api.repo.SuperAdminRepository;
import rw.rutaks.wms.api.service.SuperAdminService;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {
  @Autowired
  private SuperAdminRepository superAdminRepo;

  @Override
  public SuperAdmin createSuperAdmin(SuperAdmin superAdmin) {
    return superAdminRepo.save(superAdmin);
  }
}
