package ovh.wiktormalyska.pharmacysystembackend.pharmacist;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {
    Optional<Pharmacist> findByName(String name);

  Optional<Pharmacist> findByUsername(String username);
}
