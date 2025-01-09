package ovh.wiktormalyska.pharmacysystembackend.order;

import java.util.List;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ovh.wiktormalyska.pharmacysystembackend.pharmacist.Pharmacist;
import ovh.wiktormalyska.pharmacysystembackend.pharmacy.Pharmacy;

public class OrderMapper {
  public static @NotNull OrderDTO toDTO(@NotNull Order order) {
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

  @Contract(pure = true)
  public static @NotNull Order fromDTO(
      @NotNull OrderDTO orderDTO,
      Pharmacy pharmacy,
      Pharmacist pharmacist,
      List<OrderItem> itemsInOrder) {
    return Order.builder()
        .id(orderDTO.getId())
        .pharmacy(pharmacy)
        .pharmacist(pharmacist)
        .itemsInOrder(itemsInOrder)
        .orderStatus(orderDTO.getOrderStatus())
        .creationDateTime(orderDTO.getCreationDateTime())
        .modificationDateTime(orderDTO.getModificationDateTime())
        .build();
  }
}
