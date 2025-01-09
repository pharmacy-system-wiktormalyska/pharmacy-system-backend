package ovh.wiktormalyska.pharmacysystembackend.order;

import org.jetbrains.annotations.NotNull;
import ovh.wiktormalyska.pharmacysystembackend.drug.Drug;

public class OrderItemMapper {
  public static OrderItemDTO toDTO(@NotNull OrderItem orderItem) {
    return OrderItemDTO.builder()
        .id(orderItem.getId())
        .drugId(orderItem.getDrug().getId())
        .quantity(orderItem.getQuantity())
        .priceInCents(orderItem.getPriceInCents())
        .build();
  }

  public static @NotNull OrderItem fromDTO(@NotNull OrderItemDTO orderItemDTO, @NotNull Drug drug) {
    return OrderItem.builder()
        .id(orderItemDTO.getId())
        .drug(drug)
        .quantity(orderItemDTO.getQuantity())
        .priceInCents(orderItemDTO.getPriceInCents())
        .build();
  }
}
