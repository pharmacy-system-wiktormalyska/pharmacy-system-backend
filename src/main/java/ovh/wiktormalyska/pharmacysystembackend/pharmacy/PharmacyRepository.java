package ovh.wiktormalyska.pharmacysystembackend.pharmacy;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
  Optional<Pharmacy> findByName(String name);

  Optional<Pharmacy> findByManagerId(Long managerId);
}
