package ovh.wiktormalyska.pharmacysystembackend.drugorder;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

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
  public static @NotNull DrugOrder fromDTO(@NotNull DrugOrderRequestDTO drugRequestDTO) {
    // TODO: make this work
    return DrugOrder.builder()
        .id(drugRequestDTO.getId())
//        .drug(drugService.getDrugById(drugRequestDTO.getDrugId()))
        .quantity(drugRequestDTO.getQuantity())
//        .pharmacist(pharmacistService.getPharmacistById(drugRequestDTO.getPharmacistId()))
//        .manager(managerService.getManagerById(drugRequest
        .build();
  }
}
