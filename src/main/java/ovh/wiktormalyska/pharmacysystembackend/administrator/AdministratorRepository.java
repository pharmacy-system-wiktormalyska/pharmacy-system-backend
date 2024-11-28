package ovh.wiktormalyska.pharmacysystembackend.administrator;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
  Optional<Administrator> findByName(String name);

  Optional<Administrator> findByUsername(String username);
}
