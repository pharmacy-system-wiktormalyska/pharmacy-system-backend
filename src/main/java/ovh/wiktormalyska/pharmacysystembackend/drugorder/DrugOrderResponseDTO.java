package ovh.wiktormalyska.pharmacysystembackend.drugorder;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DrugOrderResponseDTO {
  private Long id;

  private Long drugId;
  private Long quantity;

  private long pharmacistId;
  private long managerId;

  private OrderStatus orderStatus;

  private LocalDateTime creationDateTime;
  private LocalDateTime modificationDateTime;

  private boolean isActive;
}
