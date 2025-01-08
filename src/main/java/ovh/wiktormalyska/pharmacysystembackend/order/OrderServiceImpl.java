package ovh.wiktormalyska.pharmacysystembackend.order;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;

  public OrderServiceImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public List<OrderDTO> getAllOrderDtos() {
    return orderRepository.findAll().stream().map(OrderMapper::toDTO).toList();
  }
}
