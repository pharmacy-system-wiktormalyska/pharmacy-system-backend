package ovh.wiktormalyska.pharmacysystembackend.drugorder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ovh.wiktormalyska.pharmacysystembackend.drug.Drug;
import ovh.wiktormalyska.pharmacysystembackend.drug.DrugService;
import ovh.wiktormalyska.pharmacysystembackend.manager.Manager;
import ovh.wiktormalyska.pharmacysystembackend.manager.ManagerService;
import ovh.wiktormalyska.pharmacysystembackend.pharmacist.Pharmacist;
import ovh.wiktormalyska.pharmacysystembackend.pharmacist.PharmacistService;
import ovh.wiktormalyska.pharmacysystembackend.warehouse.Warehouse;
import ovh.wiktormalyska.pharmacysystembackend.warehouse.WarehouseService;

@Service
public class DrugOrderServiceImpl implements DrugOrderService {
  private final DrugOrderRepository drugOrderRepository;
  private final DrugService drugService;
  private final PharmacistService pharmacistService;
  private final ManagerService managerService;
  private final WarehouseService warehouseService;

  DrugOrderServiceImpl(
      DrugOrderRepository drugOrderRepository,
      DrugService drugService,
      PharmacistService pharmacistService,
      ManagerService managerService,
      WarehouseService warehouseService) {
    this.drugOrderRepository = drugOrderRepository;
    this.drugService = drugService;
    this.pharmacistService = pharmacistService;
    this.managerService = managerService;
    this.warehouseService = warehouseService;
  }

  @Override
  public DrugOrderResponseDTO addNewDrugOrder(@NotNull DrugOrderRequestDTO drugRequestDTO) {
    Warehouse warehouse = warehouseService.getWarehouseById(drugRequestDTO.getWarehouseId());
    Drug drug = drugService.getDrugById(drugRequestDTO.getDrugId());
    Pharmacist pharmacist = pharmacistService.getPharmacistById(drugRequestDTO.getPharmacistId());
    Manager manager = managerService.getManagerById(drugRequestDTO.getManagerId());

    return DrugOrderMapper.toDTO(
        drugOrderRepository.save(
            DrugOrderMapper.fromDTO(drugRequestDTO, warehouse, drug, pharmacist, manager)));
  }

  @Override
  public DrugOrderResponseDTO getDrugOrderById(@NotNull Long id) {
    return DrugOrderMapper.toDTO(getDrugOrder(id));
  }

  @Override
  public DrugOrderResponseDTO updateDrugOrder(@NotNull DrugOrderRequestDTO drugRequestDTO) {
    DrugOrder drugOrder = getDrugOrder(drugRequestDTO.getId());

    drugOrder.setDrug(drugService.getDrugById(drugRequestDTO.getDrugId()));
    drugOrder.setQuantity(drugRequestDTO.getQuantity());
    drugOrder.setPharmacist(pharmacistService.getPharmacistById(drugRequestDTO.getPharmacistId()));
    drugOrder.setManager(managerService.getManagerById(drugRequestDTO.getManagerId()));

    drugOrder.setModificationDateTime(LocalDateTime.now());

    return DrugOrderMapper.toDTO(drugOrderRepository.save(drugOrder));
  }

  @Override
  public DrugOrderResponseDTO removeDrugOrderById(@NotNull Long id) {
    DrugOrder drugOrder = getDrugOrder(id);

    return DrugOrderMapper.toDTO(removeDrugOrder(drugOrder));
  }

  @Override
  public List<DrugOrderResponseDTO> getAllDrugOrders() {
    return drugOrderRepository.findAll().stream().map(DrugOrderMapper::toDTO).toList();
  }

  @Override
  public DrugOrderResponseDTO acceptDrugOrderById(@NotNull Long id) {
    DrugOrder drugOrder = getDrugOrderWithStatusCheck(id);

    drugOrder.setModificationDateTime(LocalDateTime.now());
    drugOrder.setDrugOrderStatus(DrugOrderStatus.ACCEPTED);

    return DrugOrderMapper.toDTO(drugOrderRepository.save(drugOrder));
  }

  @Override
  public DrugOrderResponseDTO rejectDrugOrderById(@NotNull Long id) {
    DrugOrder drugOrder = getDrugOrderWithStatusCheck(id);

    drugOrder.setModificationDateTime(LocalDateTime.now());
    drugOrder.setDrugOrderStatus(DrugOrderStatus.REJECTED);

    return DrugOrderMapper.toDTO(drugOrderRepository.save(drugOrder));
  }

  @Override
  public DrugOrderResponseDTO completeDrugOrderById(@NotNull Long id) {
    DrugOrder drugOrder = getDrugOrder(id);

    if (drugOrder.getDrugOrderStatus() != DrugOrderStatus.ACCEPTED) {
      throw new ResponseStatusException(
          HttpStatus.CONFLICT, "Drug order has not been accepted yet.");
    }

    drugOrder.setModificationDateTime(LocalDateTime.now());

    DrugOrder processedOrder = warehouseService.completeOrder(drugOrder);

    return DrugOrderMapper.toDTO(drugOrderRepository.save(processedOrder));
  }

  // Utility
  private @NotNull DrugOrder getDrugOrder(@NotNull Long id) {
    Optional<DrugOrder> drugOptional = drugOrderRepository.findById(id);

    if (drugOptional.isEmpty()) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "DrugOrder with this name doesn't exist.");
    }

    return drugOptional.get();
  }

  public @NotNull DrugOrder getDrugOrderWithStatusCheck(@NotNull Long id) {
    DrugOrder drugOrder = getDrugOrder(id);

    if (!drugOrder.isActive()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "DrugOrder has already been deleted.");
    }

    if (drugOrder.getDrugOrderStatus() == DrugOrderStatus.ACCEPTED) {
      throw new ResponseStatusException(
          HttpStatus.CONFLICT, "Drug order has already been accepted.");
    }

    if (drugOrder.getDrugOrderStatus() == DrugOrderStatus.REJECTED) {
      throw new ResponseStatusException(
          HttpStatus.CONFLICT, "Drug order has already been rejected.");
    }

    if (drugOrder.getDrugOrderStatus() == DrugOrderStatus.COMPLETED) {
      throw new ResponseStatusException(
          HttpStatus.CONFLICT, "Drug order has already been completed.");
    }

    return drugOrder;
  }

  private @NotNull DrugOrder removeDrugOrder(@NotNull DrugOrder drugOrder) {
    if (!drugOrder.isActive()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "DrugOrder has already been deleted.");
    }
    drugOrder.setActive(false);

    return drugOrderRepository.save(drugOrder);
  }
}
