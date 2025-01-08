package ovh.wiktormalyska.pharmacysystembackend.warehouse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WarehouseItemDto {
  private Long id;
  private Long warehouseId;
  private Long drugId;
  private Long priceInCents;
  private Long quantity;
}
