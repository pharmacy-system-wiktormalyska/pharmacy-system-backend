package ovh.wiktormalyska.pharmacysystembackend.administrator;

import java.time.LocalDateTime;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ovh.wiktormalyska.pharmacysystembackend.pharmacy.PharmacyServiceImpl;

@Service
public class AdministratorServiceImpl implements AdministratorService {
  private final AdministratorRepository administratorRepository;
  private final PharmacyServiceImpl pharmacyServiceImpl;

  AdministratorServiceImpl(AdministratorRepository administratorRepository, PharmacyServiceImpl pharmacyServiceImpl) {
    this.administratorRepository = administratorRepository;
    this.pharmacyServiceImpl = pharmacyServiceImpl;
  }

  @Override
  public AdministratorResponseDTO addNewAdministrator(AdministratorRequestDTO administratorRequestDTO) {
    Administrator administrator = AdministratorMapper.fromDTO(administratorRequestDTO);

    return AdministratorMapper.toDTO(administrator);
  }

  @Override
  public AdministratorResponseDTO getAdministratorById(Long id) {
    return AdministratorMapper.toDTO(getAdministrator(id));
  }

  @Override
  public AdministratorResponseDTO updateAdministrator(
      @NotNull AdministratorRequestDTO administratorRequestDTO) {
    Administrator administrator = getAdministrator(administratorRequestDTO.getId());

    administrator.setName(administratorRequestDTO.getName());
    administrator.setSurname(administratorRequestDTO.getSurname());
    administrator.setFamilyName(administratorRequestDTO.getFamilyName());
    administrator.setPlaceOfBirth(administratorRequestDTO.getPlaceOfBirth());
    administrator.setDateOfBirth(administratorRequestDTO.getDateOfBirth());
    administrator.setNationality(administratorRequestDTO.getNationality());
    administrator.setAddress(administratorRequestDTO.getAddress());
    administrator.setCorrespondenceAddress(administratorRequestDTO.getCorrespondenceAddress());
    administrator.setFathersName(administratorRequestDTO.getFathersName());
    administrator.setMothersName(administratorRequestDTO.getMothersName());
    administrator.setEducation(administratorRequestDTO.getEducation());

    administrator.setModificationDateTime(LocalDateTime.now());

    return AdministratorMapper.toDTO(administratorRepository.save(administrator));
  }

  @Override
  public AdministratorResponseDTO removeAdministratorById(Long id) {
    Administrator administrator = getAdministrator(id);

    return AdministratorMapper.toDTO(removeAdministrator(administrator));
  }

  // Utility
  private @NotNull Administrator getAdministrator(Long id) {
    Optional<Administrator> AdministratorOptional = administratorRepository.findById(id);

    if (AdministratorOptional.isEmpty()) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Administrator with this id doesn't exist.");
    }

    return AdministratorOptional.get();
  }

  private @NotNull Administrator removeAdministrator(@NotNull Administrator administrator) {
    if (!administrator.isActive()) {
      throw new ResponseStatusException(
          HttpStatus.CONFLICT, "Administrator has already been deleted.");
    }
    administrator.setActive(false);

    return administratorRepository.save(administrator);
  }
}
