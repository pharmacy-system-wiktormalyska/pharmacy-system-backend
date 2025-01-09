package ovh.wiktormalyska.pharmacysystembackend.order;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
  List<OrderDTO> getAllOrderDtos();

  OrderDTO getOrderDtoById(Long id);

  OrderDTO addOrder(OrderDTO orderDTO);

  OrderDTO removeOrderById(Long id);

  OrderDTO updateOrder(OrderDTO orderDTO);
}
