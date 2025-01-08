package ovh.wiktormalyska.pharmacysystembackend.order;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order/")
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping("get/all")
  public List<OrderDTO> getAllOrders() {
    return orderService.getAllOrderDtos();
  }
}
