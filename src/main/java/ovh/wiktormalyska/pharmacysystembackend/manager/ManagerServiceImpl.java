package ovh.wiktormalyska.pharmacysystembackend.manager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ovh.wiktormalyska.pharmacysystembackend.pharmacy.Pharmacy;
import ovh.wiktormalyska.pharmacysystembackend.pharmacy.PharmacyService;

@Service
public class ManagerServiceImpl implements ManagerService {
  private final ManagerRepository managerRepository;
  private final PharmacyService pharmacyService;

  ManagerServiceImpl(ManagerRepository managerRepository, PharmacyService pharmacyService) {
    this.managerRepository = managerRepository;
    this.pharmacyService = pharmacyService;
  }

  @Override
  public ManagerResponseDTO addNewManager(@NotNull ManagerRequestDTO managerRequestDTO) {
    Pharmacy pharmacy = null;

    if (managerRequestDTO.getPharmacyId() != null) {
      pharmacy = pharmacyService.getPharmacyById(managerRequestDTO.getPharmacyId());
    }

    Optional<Manager> manager = managerRepository.findByUsername(managerRequestDTO.getUsername());

    if (manager.isPresent()) {
      throw new ResponseStatusException(
          HttpStatus.CONFLICT, "Manager with this username already exists.");
    }

    return ManagerMapper.toDTO(
        managerRepository.save(ManagerMapper.fromDTO(managerRequestDTO, pharmacy)));
  }

  @Override
  public ManagerResponseDTO getManagerDtoById(Long id) {
    return ManagerMapper.toDTO(getManagerById(id));
  }

  @Override
  public ManagerResponseDTO updateManager(@NotNull ManagerRequestDTO managerRequestDTO) {
    Manager manager = getManagerById(managerRequestDTO.getId());

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

    manager.setPharmacy(
        managerRequestDTO.getPharmacyId() == null
            ? null
            : pharmacyService.getPharmacyById(managerRequestDTO.getPharmacyId()));

    manager.setModificationDateTime(LocalDateTime.now());

    return ManagerMapper.toDTO(managerRepository.save(manager));
  }

  @Override
  public ManagerResponseDTO removeManagerById(@NotNull Long id) {
    Manager manager = getManagerById(id);

    return ManagerMapper.toDTO(removeManager(manager));
  }

  @Override
  public List<ManagerResponseDTO> getAllManagerDtos() {
    return managerRepository.findAll().stream().filter(Manager::isActive).map(ManagerMapper::toDTO).toList();
  }

  // Utility
  public @NotNull Manager getManagerById(@NotNull Long id) {
    Optional<Manager> managerOptional = managerRepository.findById(id);

    if (managerOptional.isEmpty()) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Manager with this id doesn't exist.");
    }

    if (!managerOptional.get().isActive()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Manager has been deleted.");
    }

    return managerOptional.get();
  }

  private @NotNull Manager removeManager(@NotNull Manager manager) {
    if (!manager.isActive()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Manager has already been deleted.");
    }
    manager.setActive(false);

    return managerRepository.save(manager);
  }
}
