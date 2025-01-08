package ovh.wiktormalyska.pharmacysystembackend.order;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
  List<OrderDto> getAllOrderDtos();
}
