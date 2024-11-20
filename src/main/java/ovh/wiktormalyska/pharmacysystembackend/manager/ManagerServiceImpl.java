package ovh.wiktormalyska.pharmacysystembackend.manager;

import java.time.LocalDateTime;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ovh.wiktormalyska.pharmacysystembackend.pharmacy.PharmacyServiceImpl;

@Service
public class ManagerServiceImpl implements ManagerService {
  private final ManagerRepository managerRepository;
  private final PharmacyServiceImpl pharmacyServiceImpl;

  ManagerServiceImpl(ManagerRepository managerRepository, PharmacyServiceImpl pharmacyServiceImpl) {
    this.managerRepository = managerRepository;
    this.pharmacyServiceImpl = pharmacyServiceImpl;
  }

  @Override
  public ManagerResponseDTO addNewManager(ManagerRequestDTO managerRequestDTO) {
    Manager manager = ManagerMapper.fromDTO(managerRequestDTO);
    manager.setPharmacies(managerRequestDTO.getPharmacyIds().stream().map(pharmacyServiceImpl::getPharmacy).toList());

    return ManagerMapper.toDTO(manager);
  }

  @Override
  public ManagerResponseDTO getManagerById(Long id) {
    return ManagerMapper.toDTO(getManager(id));
  }

  @Override
  public ManagerResponseDTO updateManager(
      @NotNull ManagerRequestDTO managerRequestDTO) {
    Manager manager = getManager(managerRequestDTO.getId());

    manager.setName(managerRequestDTO.getName());
    manager.setSurname(managerRequestDTO.getSurname());
    manager.setFamilyName(managerRequestDTO.getFamilyName());
    manager.setPlaceOfBirth(managerRequestDTO.getPlaceOfBirth());
    manager.setDateOfBirth(managerRequestDTO.getDateOfBirth());
    manager.setNationality(managerRequestDTO.getNationality());
    manager.setAddress(managerRequestDTO.getAddress());
    manager.setCorrespondenceAddress(managerRequestDTO.getCorrespondenceAddress());
    manager.setFathersName(managerRequestDTO.getFathersName());
    manager.setMothersName(managerRequestDTO.getMothersName());
    manager.setEducation(managerRequestDTO.getEducation());

    manager.setPharmacies(managerRequestDTO.getPharmacyIds().stream().map(pharmacyServiceImpl::getPharmacy).toList());

    manager.setModificationDateTime(LocalDateTime.now());

    return ManagerMapper.toDTO(managerRepository.save(manager));
  }

  @Override
  public ManagerResponseDTO removeManagerById(Long id) {
    Manager manager = getManager(id);

    return ManagerMapper.toDTO(removeManager(manager));
  }

  // Utility
  private @NotNull Manager getManager(Long id) {
    Optional<Manager> ManagerOptional = managerRepository.findById(id);

    if (ManagerOptional.isEmpty()) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Manager with this id doesn't exist.");
    }

    return ManagerOptional.get();
  }

  private @NotNull Manager removeManager(@NotNull Manager manager) {
    if (!manager.isActive()) {
      throw new ResponseStatusException(
          HttpStatus.CONFLICT, "Manager has already been deleted.");
    }
    manager.setActive(false);

    return managerRepository.save(manager);
  }
}
