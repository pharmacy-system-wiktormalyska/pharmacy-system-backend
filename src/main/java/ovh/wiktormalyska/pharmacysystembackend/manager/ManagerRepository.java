package ovh.wiktormalyska.pharmacysystembackend.manager;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
  Optional<Manager> findByName(String name);

  Optional<Manager> findByUsername(String username);
}
