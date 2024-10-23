package ovh.wiktormalyska.pharmacysystembackend.pharmacist;

public interface PharmacistService {
  PharmacistResponseDTO addNewPharmacist(PharmacistRequestDTO pharmacistRequestDTO);

  PharmacistResponseDTO getPharmacistById(Long id);

  PharmacistResponseDTO updatePharmacist(PharmacistRequestDTO pharmacistRequestDTO);

  PharmacistResponseDTO removePharmacistById(Long id);
}
