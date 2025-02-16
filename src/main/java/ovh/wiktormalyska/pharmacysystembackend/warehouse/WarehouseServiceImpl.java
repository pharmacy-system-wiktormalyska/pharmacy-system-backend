package ovh.wiktormalyska.pharmacysystembackend.warehouse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ovh.wiktormalyska.pharmacysystembackend.drugorder.DrugOrder;
import ovh.wiktormalyska.pharmacysystembackend.drugorder.DrugOrderStatus;

@Service
public class WarehouseServiceImpl implements WarehouseService {
  private final WarehouseRepository warehouseRepository;
  private final WarehouseItemRepository warehouseItemRepository;

  WarehouseServiceImpl(
      WarehouseRepository warehouseRepository, WarehouseItemRepository warehouseItemRepository) {
    this.warehouseRepository = warehouseRepository;
    this.warehouseItemRepository = warehouseItemRepository;
  }

  @Override
  public DrugOrder completeOrder(@NotNull DrugOrder drugOrder) {
    Optional<WarehouseItem> warehouseItem =
        warehouseItemRepository.findByWarehouseAndDrug(
            drugOrder.getWarehouse(), drugOrder.getDrug());

    if (warehouseItem.isEmpty()) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Drug with id of "
              + drugOrder.getDrug().getId()
              + " not found for warehouse with id of "
              + drugOrder.getWarehouse().getId());
    }

    warehouseItem.get().setQuantity(warehouseItem.get().getQuantity() + drugOrder.getQuantity());

    drugOrder.setModificationDateTime(LocalDateTime.now());
    drugOrder.setCompletionDateTime(LocalDateTime.now());
    drugOrder.setDrugOrderStatus(DrugOrderStatus.COMPLETED);

    return drugOrder;
  }

  @Override
  public List<WarehouseResponseDTO> getAllWarehouseDtos() {
    return warehouseRepository.findAll().stream().filter(Warehouse::isActive).map(WarehouseMapper::toDTO).toList();
  }

  @Override
  public WarehouseResponseDTO getWarehouseDtoById(Long id) {
    return WarehouseMapper.toDTO(getWarehouseById(id));
  }

  // Utility
  @Override
  public Warehouse getWarehouseById(Long id) {
    Optional<Warehouse> warehouse = warehouseRepository.findById(id);

    if (warehouse.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Warehouse not found");
    }

    return warehouse.get();
  }
}
