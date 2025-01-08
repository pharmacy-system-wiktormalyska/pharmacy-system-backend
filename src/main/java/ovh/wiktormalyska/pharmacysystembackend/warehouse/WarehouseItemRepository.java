package ovh.wiktormalyska.pharmacysystembackend.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.wiktormalyska.pharmacysystembackend.drug.Drug;

import java.util.Optional;

public interface WarehouseItemRepository extends JpaRepository<WarehouseItem, Long> {
  Optional<WarehouseItem> findByWarehouseAndDrug(Warehouse warehouse, Drug drug);
}
