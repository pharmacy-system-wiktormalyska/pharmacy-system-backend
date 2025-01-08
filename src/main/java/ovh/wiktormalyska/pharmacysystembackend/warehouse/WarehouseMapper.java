package ovh.wiktormalyska.pharmacysystembackend.warehouse;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ovh.wiktormalyska.pharmacysystembackend.pharmacy.Pharmacy;
import ovh.wiktormalyska.pharmacysystembackend.drugorder.DrugOrder;

public class WarehouseMapper {
  @Contract(pure = true)
  public static WarehouseResponseDTO toDTO(@NotNull Warehouse warehouse) {
    return WarehouseResponseDTO.builder()
        .id(warehouse.getId())
        .stock(warehouse.getStock().stream().map(WarehouseItemMapper::toDTO).toList())
        .managerId(warehouse.getManager().getId())
        .pharmacyIds(warehouse.getPharmacies().stream().map(Pharmacy::getId).toList())
        .drugOrderIds(warehouse.getDrugOrders().stream().map(DrugOrder::getId).toList())
        .build();
  }
}
