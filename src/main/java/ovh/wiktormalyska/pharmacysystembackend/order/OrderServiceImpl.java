package ovh.wiktormalyska.pharmacysystembackend.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ovh.wiktormalyska.pharmacysystembackend.drug.DrugService;
import ovh.wiktormalyska.pharmacysystembackend.pharmacist.Pharmacist;
import ovh.wiktormalyska.pharmacysystembackend.pharmacist.PharmacistService;
import ovh.wiktormalyska.pharmacysystembackend.pharmacy.Pharmacy;
import ovh.wiktormalyska.pharmacysystembackend.pharmacy.PharmacyService;

@Service
public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;
  private final PharmacyService pharmacyService;
  private final PharmacistService pharmacistService;
  private final DrugService drugService;

  public OrderServiceImpl(
      OrderRepository orderRepository,
      PharmacyService pharmacyService,
      PharmacistService pharmacistService,
      DrugService drugService) {
    this.orderRepository = orderRepository;
    this.pharmacyService = pharmacyService;
    this.pharmacistService = pharmacistService;
    this.drugService = drugService;
  }

  @Override
  public OrderDTO addOrder(@NotNull OrderDTO orderDTO) {
    Pharmacy pharmacy = pharmacyService.getPharmacyById(orderDTO.getPharmacyId());
    Pharmacist pharmacist = pharmacistService.getPharmacistById(orderDTO.getPharmacistId());
    List<OrderItem> itemsInOrder =
        orderDTO.getItemsInOrder().stream()
            .map(i -> OrderItemMapper.fromDTO(i, drugService.getDrugById(i.getDrugId())))
            .toList();
    Order order = OrderMapper.fromDTO(orderDTO, pharmacy, pharmacist, itemsInOrder);

    return OrderMapper.toDTO(orderRepository.save(order));
  }

  @Override
  public OrderDTO getOrderDtoById(@NotNull Long id) {
    return OrderMapper.toDTO(getOrderById(id));
  }

  @Override
  public List<OrderDTO> getAllOrderDtos() {
    return orderRepository.findAll().stream().map(OrderMapper::toDTO).toList();
  }

  @Override
  public OrderDTO updateOrder(@NotNull OrderDTO orderDTO) {
    Order order = getOrderById(orderDTO.getId());

    order.setPharmacy(pharmacyService.getPharmacyById(orderDTO.getPharmacyId()));
    order.setPharmacist(pharmacistService.getPharmacistById(orderDTO.getPharmacistId()));
    order.setItemsInOrder(
        orderDTO.getItemsInOrder().stream()
            .map(i -> OrderItemMapper.fromDTO(i, drugService.getDrugById(i.getDrugId())))
            .toList());
    order.setOrderStatus(orderDTO.getOrderStatus());
    order.setModificationDateTime(LocalDateTime.now());

    return OrderMapper.toDTO(orderRepository.save(order));
  }

  @Override
  public OrderDTO removeOrderById(@NotNull Long id) {
    return OrderMapper.toDTO(removeOrder(getOrderById(id)));
  }

  // Utilities
  public Order getOrderById(@NotNull Long id) {
    Optional<Order> order = orderRepository.findById(id);

    if (order.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found.");
    }

    if (!order.get().isActive()) {
      throw new ResponseStatusException(
          HttpStatus.UNPROCESSABLE_ENTITY, "Order has already been deleted.");
    }

    return order.get();
  }

  public Order removeOrder(@NotNull Order order) {
    if (!order.isActive()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Order has already been deleted.");
    }

    order.setActive(false);

    return orderRepository.save(order);
  }
}
