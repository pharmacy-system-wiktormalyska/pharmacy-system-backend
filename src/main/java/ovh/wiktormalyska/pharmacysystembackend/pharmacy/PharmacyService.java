package ovh.wiktormalyska.pharmacysystembackend.pharmacy;

public interface PharmacyService {
  PharmacyResponseDTO addNewPharmacy(PharmacyRequestDTO pharmacyRequestDTO);

  PharmacyResponseDTO getPharmacyById(Long id);

  PharmacyResponseDTO updatePharmacy(PharmacyRequestDTO pharmacyRequestDTO);

  PharmacyResponseDTO removePharmacyById(Long id);
}
