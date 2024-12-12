package ovh.wiktormalyska.pharmacysystembackend.drugorder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ovh.wiktormalyska.pharmacysystembackend.drug.DrugRepository;
import ovh.wiktormalyska.pharmacysystembackend.drug.DrugService;
import ovh.wiktormalyska.pharmacysystembackend.manager.ManagerService;
import ovh.wiktormalyska.pharmacysystembackend.pharmacist.PharmacistService;

@Service
public class DrugOrderServiceImpl implements DrugOrderService {
  private final DrugOrderRepository drugOrderRepository;
  private final DrugService drugService;
  private final PharmacistService pharmacistService;
  private final ManagerService managerService;

  DrugOrderServiceImpl(DrugOrderRepository drugOrderRepository, DrugService drugService, PharmacistService pharmacistService, ManagerService managerService) {
    this.drugOrderRepository = drugOrderRepository;
    this.drugService = drugService;
    this.pharmacistService = pharmacistService;
    this.managerService = managerService;
  }

  @Override
  public DrugOrderResponseDTO addNewDrugOrder(DrugOrderRequestDTO drugRequestDTO) {
    return DrugOrderMapper.toDTO(drugOrderRepository.save(DrugOrderMapper.fromDTO(drugRequestDTO)));
  }

  @Override
  public DrugOrderResponseDTO getDrugOrderById(Long id) {
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
  public DrugOrderResponseDTO removeDrugOrderById(Long id) {
    DrugOrder drugOrder = getDrugOrder(id);

    return DrugOrderMapper.toDTO(removeDrugOrder(drugOrder));
  }

  @Override
  public List<DrugOrderResponseDTO> getAllDrugOrders() {
    return drugOrderRepository.findAll().stream().map(DrugOrderMapper::toDTO).toList();
  }

  // Utility
  private @NotNull DrugOrder getDrugOrder(Long id) {
    Optional<DrugOrder> drugOptional = drugOrderRepository.findById(id);

    if (drugOptional.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "DrugOrder with this name doesn't exist.");
    }

    return drugOptional.get();
  }

  private @NotNull DrugOrder removeDrugOrder(@NotNull DrugOrder drugOrder) {
    if (!drugOrder.isActive()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "DrugOrder has already been deleted.");
    }
    drugOrder.setActive(false);

    return drugOrderRepository.save(drugOrder);
  }
}
