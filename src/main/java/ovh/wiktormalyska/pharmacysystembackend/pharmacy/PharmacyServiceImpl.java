package ovh.wiktormalyska.pharmacysystembackend.pharmacy;

import java.time.LocalDateTime;
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
    return PharmacyMapper.toDTO(pharmacyRepository.save(PharmacyMapper.fromDTO(pharmacyRequestDTO)));
  }

  @Override
  public PharmacyResponseDTO getPharmacyById(Long id) {
    return PharmacyMapper.toDTO(getPharmacy(id));
  }

  @Override
  public PharmacyResponseDTO updatePharmacy(@NotNull PharmacyRequestDTO pharmacyRequestDTO) {
    Pharmacy pharmacy = getPharmacy(pharmacyRequestDTO.getId());

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
    Pharmacy pharmacy = getPharmacy(id);

    return PharmacyMapper.toDTO(removePharmacy(pharmacy));
  }

  // Utility
  private @NotNull Pharmacy getPharmacy(Long id) {
    Optional<Pharmacy> PharmacyOptional = pharmacyRepository.findById(id);

    if (PharmacyOptional.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pharmacy with this name doesn't exist.");
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
