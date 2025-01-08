package ovh.wiktormalyska.pharmacysystembackend.warehouse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/warehouse/")
public class WarehouseController {
    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping("get/all")
    public List<WarehouseResponseDTO> getAllWarehouses() {
        return warehouseService.getAllWarehouseDtos();
    }
}
