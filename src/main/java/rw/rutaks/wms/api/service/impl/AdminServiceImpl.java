package rw.rutaks.wms.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import rw.rutaks.wms.api.model.Admin;
import rw.rutaks.wms.api.repo.AdminRepository;
import rw.rutaks.wms.api.service.AdminService;

public class AdminServiceImpl implements AdminService {
  @Autowired private AdminRepository adminRepo;

  @Override
  public Admin createAdmin(Admin admin) {
    return adminRepo.save(admin);
  }
}
