package rw.rutaks.wms.api.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import rw.rutaks.wms.api.model.Auth;

public interface AuthRepository extends JpaRepository<Auth, Long> {
  Optional<Auth> findByUsername(String username);
}
