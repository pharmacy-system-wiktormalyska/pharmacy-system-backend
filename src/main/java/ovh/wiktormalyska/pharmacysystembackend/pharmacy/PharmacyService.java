package ovh.wiktormalyska.pharmacysystembackend.pharmacy;

import java.util.List;

public interface PharmacyService {
  PharmacyResponseDTO addNewPharmacy(PharmacyRequestDTO pharmacyRequestDTO);

  PharmacyResponseDTO getPharmacyDtoById(Long id);

  PharmacyResponseDTO updatePharmacy(PharmacyRequestDTO pharmacyRequestDTO);

  PharmacyResponseDTO removePharmacyById(Long id);

  List<PharmacyResponseDTO> getAllPharmacyDtos();

  Pharmacy getPharmacyById(Long pharmacyId);
}
