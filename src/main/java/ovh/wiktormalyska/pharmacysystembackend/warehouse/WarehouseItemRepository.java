package ovh.wiktormalyska.pharmacysystembackend.warehouse;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ovh.wiktormalyska.pharmacysystembackend.drug.Drug;

public interface WarehouseItemRepository extends JpaRepository<WarehouseItem, Long> {
  Optional<WarehouseItem> findByWarehouseAndDrug(Warehouse warehouse, Drug drug);
}
