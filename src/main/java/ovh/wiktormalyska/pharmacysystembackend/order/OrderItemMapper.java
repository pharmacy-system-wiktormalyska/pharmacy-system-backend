package ovh.wiktormalyska.pharmacysystembackend.order;

import org.jetbrains.annotations.NotNull;

public class OrderItemMapper {
  public static OrderItemDto toDTO(@NotNull OrderItem orderItem) {
    return OrderItemDto.builder()
        .id(orderItem.getId())
        .drugId(orderItem.getDrug().getId())
        .quantity(orderItem.getQuantity())
        .priceInCents(orderItem.getPriceInCents())
        .build();
  }
}
