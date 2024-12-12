package ovh.wiktormalyska.pharmacysystembackend.drugorder;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/drug/order/")
public class DrugOrderController {
  private final DrugOrderService drugService;

  public DrugOrderController(DrugOrderService drugService) {
    this.drugService = drugService;
  }

  @PostMapping("add")
  public DrugOrderResponseDTO addNewDrugOrder(@RequestBody DrugOrderRequestDTO drugRequestDTO) {
    return drugService.addNewDrugOrder(drugRequestDTO);
  }

  @GetMapping("get/id/{id}")
  public DrugOrderResponseDTO getDrugOrderById(@PathVariable Long id) {
    return drugService.getDrugOrderById(id);
  }

  @GetMapping("get/all")
  public List<DrugOrderResponseDTO> getDrugOrderById() {
    return drugService.getAllDrugOrders();
  }

  @PutMapping("update")
  public DrugOrderResponseDTO updateDrugOrder(@RequestBody DrugOrderRequestDTO drugRequestDTO) {
    return drugService.updateDrugOrder(drugRequestDTO);
  }

  @DeleteMapping("remove/id/{id}")
  public DrugOrderResponseDTO removeDrugOrderById(@PathVariable Long id) {
    return drugService.removeDrugOrderById(id);
  }
}
