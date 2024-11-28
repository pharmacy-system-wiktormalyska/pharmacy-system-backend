package ovh.wiktormalyska.pharmacysystembackend.drug;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class DrugMapper {
  public static DrugResponseDTO toDTO(@NotNull Drug drug) {
    return DrugResponseDTO.builder()
        .id(drug.getId())
        .name(drug.getName())
        .commonName(drug.getCommonName())
        .activeSubstance(drug.getActiveSubstance())
        .marketingAuthorizationHolder(drug.getMarketingAuthorizationHolder())
        .pharmaceuticalForm(drug.getPharmaceuticalForm())
        .maNumber(drug.getMaNumber())
        .atcCode(drug.getAtcCode())
        .strength(drug.getStrength())
        .modificationDateTime(drug.getModificationDateTime())
        .isActive(drug.isActive())
        .build();
  }

  @Contract(pure = true)
  public static @NotNull Drug fromDTO(@NotNull DrugRequestDTO drugRequestDTO) {
    return Drug.builder()
        .id(drugRequestDTO.getId())
        .name(drugRequestDTO.getName())
        .commonName(drugRequestDTO.getCommonName())
        .activeSubstance(drugRequestDTO.getActiveSubstance())
        .marketingAuthorizationHolder(drugRequestDTO.getMarketingAuthorizationHolder())
        .pharmaceuticalForm(drugRequestDTO.getPharmaceuticalForm())
        .maNumber(drugRequestDTO.getMaNumber())
        .atcCode(drugRequestDTO.getAtcCode())
        .strength(drugRequestDTO.getStrength())
        .build();
  }
}
