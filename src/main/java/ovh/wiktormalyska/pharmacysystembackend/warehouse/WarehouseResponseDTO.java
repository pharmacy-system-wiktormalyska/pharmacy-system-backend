package ovh.wiktormalyska.pharmacysystembackend.warehouse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class WarehouseResponseDTO {
  private Long id;
  private List<WarehouseItemDto> stock;
  private Long managerId;
  private List<Long> pharmacyIds;
  private List<Long> drugOrderIds;
}
