package ovh.wiktormalyska.pharmacysystembackend.administrator;

import org.jetbrains.annotations.NotNull;

public class AdministratorMapper {
  public static AdministratorResponseDTO toDTO(@NotNull Administrator administrator) {
    return AdministratorResponseDTO.builder()
        .id(administrator.getId())
        .name(administrator.getName())
        .surname(administrator.getSurname())
        .familyName(administrator.getFamilyName())
        .placeOfBirth(administrator.getPlaceOfBirth())
        .dateOfBirth(administrator.getDateOfBirth())
        .nationality(administrator.getNationality())
        .address(administrator.getAddress())
        .correspondenceAddress(administrator.getCorrespondenceAddress())
        .fathersName(administrator.getFathersName())
        .mothersName(administrator.getMothersName())
        .education(administrator.getEducation())
        .modificationDateTime(administrator.getModificationDateTime())
        .isActive(administrator.isActive())
        .build();
  }

  public static @NotNull Administrator fromDTO(
      @NotNull AdministratorRequestDTO administratorRequestDTO) {
    return Administrator.builder()
        .id(administratorRequestDTO.getId())
        .name(administratorRequestDTO.getName())
        .surname(administratorRequestDTO.getSurname())
        .familyName(administratorRequestDTO.getFamilyName())
        .placeOfBirth(administratorRequestDTO.getPlaceOfBirth())
        .dateOfBirth(administratorRequestDTO.getDateOfBirth())
        .nationality(administratorRequestDTO.getNationality())
        .address(administratorRequestDTO.getAddress())
        .correspondenceAddress(administratorRequestDTO.getCorrespondenceAddress())
        .fathersName(administratorRequestDTO.getFathersName())
        .mothersName(administratorRequestDTO.getMothersName())
        .education(administratorRequestDTO.getEducation())
        .build();
  }
}
