package ovh.wiktormalyska.pharmacysystembackend.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderDto {
  private Long id;
  private Long pharmacyId;
  private Long pharmacistId;
  private OrderStatus orderStatus;
  private List<OrderItemDto> itemsInOrder;
  private LocalDateTime creationDateTime;
  private LocalDateTime modificationDateTime;
}
