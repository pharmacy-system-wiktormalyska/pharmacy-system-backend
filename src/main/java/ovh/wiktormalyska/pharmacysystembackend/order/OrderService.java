package ovh.wiktormalyska.pharmacysystembackend.order;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
  List<OrderDTO> getAllOrderDtos();
}
