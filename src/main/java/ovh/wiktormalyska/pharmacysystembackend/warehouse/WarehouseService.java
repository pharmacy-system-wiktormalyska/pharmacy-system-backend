package ovh.wiktormalyska.pharmacysystembackend.warehouse;

import java.util.List;
import ovh.wiktormalyska.pharmacysystembackend.drugorder.DrugOrder;

public interface WarehouseService {
  DrugOrder completeOrder(DrugOrder drugOrder);

  List<WarehouseResponseDTO> getAllWarehouseDtos();

  WarehouseResponseDTO getWarehouseDtoById(Long id);

  Warehouse getWarehouseById(Long warehouseId);
}
