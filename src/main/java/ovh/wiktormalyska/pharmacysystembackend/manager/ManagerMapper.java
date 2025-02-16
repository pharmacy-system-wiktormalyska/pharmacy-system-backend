package ovh.wiktormalyska.pharmacysystembackend.manager;

import org.jetbrains.annotations.NotNull;
import ovh.wiktormalyska.pharmacysystembackend.pharmacy.Pharmacy;

public class ManagerMapper {
  public static @NotNull ManagerResponseDTO toDTO(@NotNull Manager manager) {
    return ManagerResponseDTO.builder()
        .id(manager.getId())
        .name(manager.getName())
        .username(manager.getUsername())
        .surname(manager.getSurname())
        .pesel(manager.getPesel())
        .familyName(manager.getFamilyName())
        .placeOfBirth(manager.getPlaceOfBirth())
        .dateOfBirth(manager.getDateOfBirth())
        .nationality(manager.getNationality())
        .address(manager.getAddress())
        .correspondenceAddress(manager.getCorrespondenceAddress())
        .fathersName(manager.getFathersName())
        .mothersName(manager.getMothersName())
        .education(manager.getEducation())
        .pharmacyId(manager.getPharmacy() == null ? null : manager.getPharmacy().getId())
        .creationDateTime(manager.getCreationDateTime())
        .modificationDateTime(manager.getModificationDateTime())
        .build();
  }

  public static @NotNull Manager fromDTO(@NotNull ManagerRequestDTO managerRequestDTO, Pharmacy pharmacy) {
    return Manager.builder()
        .id(managerRequestDTO.getId())
        .name(managerRequestDTO.getName())
        .username(managerRequestDTO.getUsername())
        .surname(managerRequestDTO.getSurname())
        .pesel(managerRequestDTO.getPesel())
        .familyName(managerRequestDTO.getFamilyName())
        .placeOfBirth(managerRequestDTO.getPlaceOfBirth())
        .dateOfBirth(managerRequestDTO.getDateOfBirth())
        .nationality(managerRequestDTO.getNationality())
        .address(managerRequestDTO.getAddress())
        .correspondenceAddress(managerRequestDTO.getCorrespondenceAddress())
        .fathersName(managerRequestDTO.getFathersName())
        .mothersName(managerRequestDTO.getMothersName())
        .education(managerRequestDTO.getEducation())
        .pharmacy(pharmacy)
        .build();
  }
}
