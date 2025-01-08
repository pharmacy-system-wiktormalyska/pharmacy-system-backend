package ovh.wiktormalyska.pharmacysystembackend.warehouse;

import org.jetbrains.annotations.NotNull;
import ovh.wiktormalyska.pharmacysystembackend.drug.Drug;

public class WarehouseItemMapper {
  public static WarehouseItemDto toDTO(@NotNull WarehouseItem warehouseItem) {
    return WarehouseItemDto.builder()
      .id(warehouseItem.getId())
      .drugId(warehouseItem.getDrug().getId())
      .quantity(warehouseItem.getQuantity())
      .build();
  }

  public static WarehouseItem fromDTO(@NotNull WarehouseItemDto warehouseItemDto) {
    return WarehouseItem.builder()
      .id(warehouseItemDto.getId())
      .drug(Drug.builder().id(warehouseItemDto.getDrugId()).build())
      .quantity(warehouseItemDto.getQuantity())
      .build();
  }
}
