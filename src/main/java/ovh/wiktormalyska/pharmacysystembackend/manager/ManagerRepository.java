package ovh.wiktormalyska.pharmacysystembackend.manager;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
  Optional<Manager> findByUsername(@NotNull String username);
}
