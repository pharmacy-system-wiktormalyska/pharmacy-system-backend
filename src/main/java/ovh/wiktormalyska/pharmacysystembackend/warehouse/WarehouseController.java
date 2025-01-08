package ovh.wiktormalyska.pharmacysystembackend.warehouse;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/warehouse/")
public class WarehouseController {
  private final WarehouseService warehouseService;

  public WarehouseController(WarehouseService warehouseService) {
    this.warehouseService = warehouseService;
  }

  @GetMapping("get/id/{id}")
  public WarehouseResponseDTO getWarehouseById(@PathVariable Long id) {
    return warehouseService.getWarehouseDtoById(id);
  }

  @GetMapping("get/all")
  public List<WarehouseResponseDTO> getAllWarehouses() {
    return warehouseService.getAllWarehouseDtos();
  }
}
