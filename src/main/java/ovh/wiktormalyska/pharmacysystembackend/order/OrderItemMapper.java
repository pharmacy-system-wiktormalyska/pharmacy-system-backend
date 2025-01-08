package ovh.wiktormalyska.pharmacysystembackend.order;

import org.jetbrains.annotations.NotNull;

public class OrderItemMapper {
  public static OrderItemDTO toDTO(@NotNull OrderItem orderItem) {
    return OrderItemDTO.builder()
        .id(orderItem.getId())
        .drugId(orderItem.getDrug().getId())
        .quantity(orderItem.getQuantity())
        .priceInCents(orderItem.getPriceInCents())
        .build();
  }
}
