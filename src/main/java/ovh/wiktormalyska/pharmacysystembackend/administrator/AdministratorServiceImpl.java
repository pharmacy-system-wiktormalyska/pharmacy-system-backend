package ovh.wiktormalyska.pharmacysystembackend.administrator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ovh.wiktormalyska.pharmacysystembackend.security.AuthService;

@Service
public class AdministratorServiceImpl implements AdministratorService {
  private final AdministratorRepository administratorRepository;
  private final AuthService authService;

  AdministratorServiceImpl(
      AdministratorRepository administratorRepository, AuthService authService) {
    this.administratorRepository = administratorRepository;
    this.authService = authService;
  }

  @Override
  public AdministratorResponseDTO addNewAdministrator(
      @NotNull AdministratorRequestDTO administratorRequestDTO) {
    Administrator administrator = AdministratorMapper.fromDTO(administratorRequestDTO);
    administrator.setPassword(authService.encodePassword(administrator.getPassword()));

    return AdministratorMapper.toDTO(administratorRepository.save(administrator));
  }

  @Override
  public AdministratorResponseDTO getAdministratorDtoById(@NotNull Long id) {
    return AdministratorMapper.toDTO(getAdministratorById(id));
  }

  @Override
  public AdministratorResponseDTO updateAdministrator(
      @NotNull AdministratorRequestDTO administratorRequestDTO) {
    Administrator administrator = getAdministratorById(administratorRequestDTO.getId());

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
  public AdministratorResponseDTO removeAdministratorById(@NotNull Long id) {
    Administrator administrator = getAdministratorById(id);

    return AdministratorMapper.toDTO(removeAdministrator(administrator));
  }

  @Override
  public List<AdministratorResponseDTO> getAllAdministratorDtos() {
    return administratorRepository.findAll().stream()
        .filter(Administrator::isActive)
        .map(AdministratorMapper::toDTO)
        .toList();
  }

  // Utility
  private @NotNull Administrator getAdministratorById(@NotNull Long id) {
    Optional<Administrator> administratorOptional = administratorRepository.findById(id);

    if (administratorOptional.isEmpty()) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Administrator with this id doesn't exist.");
    }

    if (!administratorOptional.get().isActive()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Administrator has been deleted.");
    }

    return administratorOptional.get();
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
