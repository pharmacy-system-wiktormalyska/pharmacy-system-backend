package ovh.wiktormalyska.pharmacysystembackend.pharmacy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PharmacyServiceImpl implements PharmacyService {
  private final PharmacyRepository pharmacyRepository;

  PharmacyServiceImpl(PharmacyRepository pharmacyRepository) {
    this.pharmacyRepository = pharmacyRepository;
  }

  @Override
  public PharmacyResponseDTO addNewPharmacy(PharmacyRequestDTO pharmacyRequestDTO) {
    return PharmacyMapper.toDTO(
        pharmacyRepository.save(PharmacyMapper.fromDTO(pharmacyRequestDTO)));
  }

  @Override
  public PharmacyResponseDTO getPharmacyDtoById(Long id) {
    return PharmacyMapper.toDTO(getPharmacyById(id));
  }

  @Override
  public PharmacyResponseDTO updatePharmacy(@NotNull PharmacyRequestDTO pharmacyRequestDTO) {
    Pharmacy pharmacy = getPharmacyById(pharmacyRequestDTO.getId());

    pharmacy.setName(pharmacyRequestDTO.getName());
    pharmacy.setAddress(pharmacyRequestDTO.getAddress());
    pharmacy.setType(pharmacyRequestDTO.getType());
    pharmacy.setOwner(pharmacyRequestDTO.getOwner());
    pharmacy.setPharmacyStatusActive(pharmacyRequestDTO.isPharmacyStatusActive());
    pharmacy.setPhone(pharmacyRequestDTO.getPhone());
    pharmacy.setEmail(pharmacyRequestDTO.getEmail());
    pharmacy.setWebsite(pharmacyRequestDTO.getWebsite());

    pharmacy.setModificationDateTime(LocalDateTime.now());

    return PharmacyMapper.toDTO(pharmacyRepository.save(pharmacy));
  }

  @Override
  public PharmacyResponseDTO removePharmacyById(Long id) {
    Pharmacy pharmacy = getPharmacyById(id);

    return PharmacyMapper.toDTO(removePharmacy(pharmacy));
  }

  @Override
  public PharmacyResponseDTO getPharmacyDtoByManagerId(Long managerId) {
    Optional<Pharmacy> pharmacy = pharmacyRepository.findByManagerId(managerId);

    if (pharmacy.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pharmacy found for manager with this id.");
    }

    return PharmacyMapper.toDTO(pharmacy.get());
  }

  @Override
  public List<PharmacyResponseDTO> getAllPharmacyDtos() {
    return pharmacyRepository.findAll().stream().filter(Pharmacy::isActive).map(PharmacyMapper::toDTO).toList();
  }

  // Utility
  public @NotNull Pharmacy getPharmacyById(Long id) {
    Optional<Pharmacy> PharmacyOptional = pharmacyRepository.findById(id);

    if (PharmacyOptional.isEmpty()) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Pharmacy with this name doesn't exist.");
    }

    return PharmacyOptional.get();
  }

  private @NotNull Pharmacy removePharmacy(@NotNull Pharmacy pharmacy) {
    if (!pharmacy.isActive()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Pharmacy has already been deleted.");
    }
    pharmacy.setActive(false);

    return pharmacyRepository.save(pharmacy);
  }
}
