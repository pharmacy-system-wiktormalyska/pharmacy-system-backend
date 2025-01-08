package ovh.wiktormalyska.pharmacysystembackend.order;

import org.jetbrains.annotations.NotNull;

public class OrderMapper {
  public static OrderDTO toDTO(@NotNull Order order) {
    return OrderDTO.builder()
        .id(order.getId())
        .pharmacyId(order.getPharmacy().getId())
        .pharmacistId(order.getPharmacist().getId())
        .itemsInOrder(order.getItemsInOrder().stream().map(OrderItemMapper::toDTO).toList())
        .orderStatus(order.getOrderStatus())
        .creationDateTime(order.getCreationDateTime())
        .modificationDateTime(order.getModificationDateTime())
        .build();
  }
}
