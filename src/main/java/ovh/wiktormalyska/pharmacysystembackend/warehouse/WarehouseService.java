package ovh.wiktormalyska.pharmacysystembackend.warehouse;

import ovh.wiktormalyska.pharmacysystembackend.drugorder.DrugOrder;

import java.util.Optional;

public interface WarehouseService {
  DrugOrder completeOrder(DrugOrder drugOrder);
}
