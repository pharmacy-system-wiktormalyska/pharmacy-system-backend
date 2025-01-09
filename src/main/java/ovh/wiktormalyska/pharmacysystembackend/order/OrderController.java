package ovh.wiktormalyska.pharmacysystembackend.order;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order/")
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping("add")
  public OrderDTO addOrder(@RequestBody OrderDTO orderDTO) {
    return orderService.addOrder(orderDTO);
  }

  @GetMapping("get/id/{id}")
  public OrderDTO getOrdersById(@PathVariable Long id) {
    return orderService.getOrderDtoById(id);
  }

  @GetMapping("get/all")
  public List<OrderDTO> getAllOrders() {
    return orderService.getAllOrderDtos();
  }

  @PutMapping("update")
  public OrderDTO removeOrder(@RequestBody OrderDTO orderDTO) {
    return orderService.updateOrder(orderDTO);
  }

  @DeleteMapping("remove/id/{id}")
  public OrderDTO removeOrder(@PathVariable Long id) {
    return orderService.removeOrderById(id);
  }
}
