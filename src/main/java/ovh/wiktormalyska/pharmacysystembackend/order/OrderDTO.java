package ovh.wiktormalyska.pharmacysystembackend.order;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderDTO {
  private Long id;
  private Long pharmacyId;
  private Long pharmacistId;
  private List<OrderItemDTO> itemsInOrder;
  private OrderStatus orderStatus;
  private LocalDateTime creationDateTime;
  private LocalDateTime modificationDateTime;
}
