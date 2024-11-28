package ovh.wiktormalyska.pharmacysystembackend.drugorder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DrugOrderRequestDTO {
  private Long id;

  private Long drugId;
  private Long quantity;

  private long pharmacistId;
  private long managerId;
}
