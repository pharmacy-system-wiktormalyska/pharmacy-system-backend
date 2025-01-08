package ovh.wiktormalyska.pharmacysystembackend.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order/")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("get/all")
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrderDtos();
    }
}
