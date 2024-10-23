package ovh.wiktormalyska.pharmacysystembackend.pharmacy;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class PharmacyMapper {
  public static PharmacyResponseDTO toDTO(@NotNull Pharmacy pharmacy) {
    return PharmacyResponseDTO.builder()
        .id(pharmacy.getId())
        .pharmacyId(pharmacy.getPharmacyId())
        .name(pharmacy.getName())
        .address(pharmacy.getAddress())
        .type(pharmacy.getType())
        .owner(pharmacy.getOwner())
        .isPharmacyStatusActive(pharmacy.isPharmacyStatusActive())
        .phone(pharmacy.getPhone())
        .email(pharmacy.getEmail())
        .website(pharmacy.getWebsite())
        .modificationDateTime(pharmacy.getModificationDateTime())
        .isActive(pharmacy.isActive())
        .build();
  }

  @Contract(pure = true)
  public static @NotNull Pharmacy fromDTO(@NotNull PharmacyRequestDTO pharmacyRequestDTO) {
    return Pharmacy.builder()
        .id(pharmacyRequestDTO.getId())
        .pharmacyId(pharmacyRequestDTO.getPharmacyId())
        .name(pharmacyRequestDTO.getName())
        .address(pharmacyRequestDTO.getAddress())
        .type(pharmacyRequestDTO.getType())
        .owner(pharmacyRequestDTO.getOwner())
        .isPharmacyStatusActive(pharmacyRequestDTO.isPharmacyStatusActive())
        .phone(pharmacyRequestDTO.getPhone())
        .email(pharmacyRequestDTO.getEmail())
        .website(pharmacyRequestDTO.getWebsite())
        .build();
  }
}
