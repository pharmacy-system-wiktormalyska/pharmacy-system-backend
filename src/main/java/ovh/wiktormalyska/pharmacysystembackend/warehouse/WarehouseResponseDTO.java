package ovh.wiktormalyska.pharmacysystembackend.warehouse;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WarehouseResponseDTO {
  private Long id;
  private List<WarehouseItemDTO> stock;
  private Long managerId;
  private List<Long> pharmacyIds;
  private List<Long> drugOrderIds;
}
