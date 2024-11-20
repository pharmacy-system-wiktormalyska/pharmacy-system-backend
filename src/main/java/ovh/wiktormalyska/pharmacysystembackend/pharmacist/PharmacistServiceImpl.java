package ovh.wiktormalyska.pharmacysystembackend.pharmacist;

import java.time.LocalDateTime;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ovh.wiktormalyska.pharmacysystembackend.pharmacy.PharmacyServiceImpl;

@Service
public class PharmacistServiceImpl implements PharmacistService {
  private final PharmacistRepository pharmacistRepository;
  private final PharmacyServiceImpl pharmacyServiceImpl;

  PharmacistServiceImpl(PharmacistRepository pharmacistRepository, PharmacyServiceImpl pharmacyServiceImpl) {
    this.pharmacistRepository = pharmacistRepository;
    this.pharmacyServiceImpl = pharmacyServiceImpl;
  }

  @Override
  public PharmacistResponseDTO addNewPharmacist(PharmacistRequestDTO pharmacistRequestDTO) {
    Pharmacist pharmacist = PharmacistMapper.fromDTO(pharmacistRequestDTO);
    pharmacist.setPharmacy(pharmacyServiceImpl.getPharmacy(pharmacistRequestDTO.getPharmacyId()));

    return PharmacistMapper.toDTO(pharmacist);
  }

  @Override
  public PharmacistResponseDTO getPharmacistDtoById(Long id) {
    return PharmacistMapper.toDTO(getPharmacistById(id));
  }

  @Override
  public PharmacistResponseDTO updatePharmacist(
      @NotNull PharmacistRequestDTO pharmacistRequestDTO) {
    Pharmacist pharmacist = getPharmacistById(pharmacistRequestDTO.getId());

    pharmacist.setName(pharmacistRequestDTO.getName());
    pharmacist.setSurname(pharmacistRequestDTO.getSurname());
    pharmacist.setFamilyName(pharmacistRequestDTO.getFamilyName());
    pharmacist.setPlaceOfBirth(pharmacistRequestDTO.getPlaceOfBirth());
    pharmacist.setDateOfBirth(pharmacistRequestDTO.getDateOfBirth());
    pharmacist.setNationality(pharmacistRequestDTO.getNationality());
    pharmacist.setAddress(pharmacistRequestDTO.getAddress());
    pharmacist.setCorrespondenceAddress(pharmacistRequestDTO.getCorrespondenceAddress());
    pharmacist.setFathersName(pharmacistRequestDTO.getFathersName());
    pharmacist.setMothersName(pharmacistRequestDTO.getMothersName());
    pharmacist.setEducation(pharmacistRequestDTO.getEducation());

    pharmacist.setPharmacy(pharmacyServiceImpl.getPharmacy(pharmacistRequestDTO.getPharmacyId()));

    pharmacist.setModificationDateTime(LocalDateTime.now());

    return PharmacistMapper.toDTO(pharmacistRepository.save(pharmacist));
  }

  @Override
  public PharmacistResponseDTO removePharmacistById(Long id) {
    Pharmacist pharmacist = getPharmacistById(id);

    return PharmacistMapper.toDTO(removePharmacist(pharmacist));
  }

  // Utility
  private @NotNull Pharmacist getPharmacistById(Long id) {
    Optional<Pharmacist> pharmacistOptional = pharmacistRepository.findById(id);

    if (pharmacistOptional.isEmpty()) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Pharmacist with this id doesn't exist.");
    }

    if (!pharmacistOptional.get().isActive()) {
      throw new ResponseStatusException(
          HttpStatus.CONFLICT, "Pharmacist has been deleted.");
    }

    return pharmacistOptional.get();
  }

  private @NotNull Pharmacist removePharmacist(@NotNull Pharmacist pharmacist) {
    if (!pharmacist.isActive()) {
      throw new ResponseStatusException(
          HttpStatus.CONFLICT, "Pharmacist has already been deleted.");
    }
    pharmacist.setActive(false);

    return pharmacistRepository.save(pharmacist);
  }
}
