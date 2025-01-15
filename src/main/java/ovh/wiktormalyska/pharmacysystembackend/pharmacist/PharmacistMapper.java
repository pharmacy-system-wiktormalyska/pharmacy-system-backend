package ovh.wiktormalyska.pharmacysystembackend.pharmacist;

import org.jetbrains.annotations.NotNull;

public class PharmacistMapper {
  public static PharmacistResponseDTO toDTO(@NotNull Pharmacist pharmacist) {
    return PharmacistResponseDTO.builder()
        .id(pharmacist.getId())
        .name(pharmacist.getName())
        .username(pharmacist.getUsername())
        .surname(pharmacist.getSurname())
        .pesel(pharmacist.getPesel())
        .familyName(pharmacist.getFamilyName())
        .placeOfBirth(pharmacist.getPlaceOfBirth())
        .dateOfBirth(pharmacist.getDateOfBirth())
        .nationality(pharmacist.getNationality())
        .address(pharmacist.getAddress())
        .correspondenceAddress(pharmacist.getCorrespondenceAddress())
        .fathersName(pharmacist.getFathersName())
        .mothersName(pharmacist.getMothersName())
        .education(pharmacist.getEducation())
        .pharmacyId(pharmacist.getPharmacy() == null ? null : pharmacist.getPharmacy().getId())
        .creationDateTime(pharmacist.getCreationDateTime())
        .modificationDateTime(pharmacist.getModificationDateTime())
        .isActive(pharmacist.isActive())
        .build();
  }

  public static @NotNull Pharmacist fromDTO(@NotNull PharmacistRequestDTO pharmacistRequestDTO) {
    return Pharmacist.builder()
        .id(pharmacistRequestDTO.getId())
        .name(pharmacistRequestDTO.getName())
        .username(pharmacistRequestDTO.getUsername())
        .surname(pharmacistRequestDTO.getSurname())
        .pesel(pharmacistRequestDTO.getPesel())
        .familyName(pharmacistRequestDTO.getFamilyName())
        .placeOfBirth(pharmacistRequestDTO.getPlaceOfBirth())
        .dateOfBirth(pharmacistRequestDTO.getDateOfBirth())
        .nationality(pharmacistRequestDTO.getNationality())
        .address(pharmacistRequestDTO.getAddress())
        .correspondenceAddress(pharmacistRequestDTO.getCorrespondenceAddress())
        .fathersName(pharmacistRequestDTO.getFathersName())
        .mothersName(pharmacistRequestDTO.getMothersName())
        .education(pharmacistRequestDTO.getEducation())
        .build();
  }
}
