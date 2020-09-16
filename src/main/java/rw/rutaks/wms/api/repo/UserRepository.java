package rw.rutaks.wms.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.rutaks.wms.api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
