package ovh.wiktormalyska.pharmacysystembackend.order;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;

  public OrderServiceImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public List<OrderDto> getAllOrderDtos() {
    return orderRepository.findAll().stream().map(OrderMapper::toDTO).toList();
  }
}
