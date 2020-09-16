package rw.rutaks.wms.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.rutaks.wms.api.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {}
