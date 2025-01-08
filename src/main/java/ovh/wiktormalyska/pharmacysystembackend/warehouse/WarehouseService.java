package ovh.wiktormalyska.pharmacysystembackend.warehouse;

import ovh.wiktormalyska.pharmacysystembackend.drugorder.DrugOrder;

import java.util.List;

public interface WarehouseService {
  DrugOrder completeOrder(DrugOrder drugOrder);

  List<WarehouseResponseDTO> getAllWarehouseDtos();

  WarehouseResponseDTO getWarehouseDtoById(Long id);
}
