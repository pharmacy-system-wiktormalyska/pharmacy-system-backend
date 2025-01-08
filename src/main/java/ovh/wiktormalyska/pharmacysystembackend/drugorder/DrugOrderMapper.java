package ovh.wiktormalyska.pharmacysystembackend.drugorder;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ovh.wiktormalyska.pharmacysystembackend.drug.Drug;
import ovh.wiktormalyska.pharmacysystembackend.manager.Manager;
import ovh.wiktormalyska.pharmacysystembackend.pharmacist.Pharmacist;
import ovh.wiktormalyska.pharmacysystembackend.warehouse.Warehouse;

public class DrugOrderMapper {
  public static DrugOrderResponseDTO toDTO(@NotNull DrugOrder drugOrder) {
    return DrugOrderResponseDTO.builder()
        .id(drugOrder.getId())
        .warehouseId(drugOrder.getWarehouse().getId())
        .drugId(drugOrder.getDrug().getId())
        .quantity(drugOrder.getQuantity())
        .pharmacistId(drugOrder.getPharmacist().getId())
        .managerId(drugOrder.getManager().getId())
        .drugOrderStatus(drugOrder.getDrugOrderStatus())
        .creationDateTime(drugOrder.getCreationDateTime())
        .modificationDateTime(drugOrder.getModificationDateTime())
        .completionDateTime(drugOrder.getCompletionDateTime())
        .build();
  }

  @Contract(pure = true)
  public static @NotNull DrugOrder fromDTO(
      @NotNull DrugOrderRequestDTO drugRequestDTO,
      Warehouse warehouse,
      Drug drug,
      Pharmacist pharmacist,
      Manager manager) {
    return DrugOrder.builder()
        .id(drugRequestDTO.getId())
        .warehouse(warehouse)
        .drug(drug)
        .quantity(drugRequestDTO.getQuantity())
        .pharmacist(pharmacist)
        .manager(manager)
        .build();
  }
}
