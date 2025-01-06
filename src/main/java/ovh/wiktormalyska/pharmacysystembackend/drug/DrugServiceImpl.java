package ovh.wiktormalyska.pharmacysystembackend.drug;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DrugServiceImpl implements DrugService {
  private final DrugRepository drugRepository;

  DrugServiceImpl(DrugRepository drugRepository) {
    this.drugRepository = drugRepository;
  }

  @Override
  public DrugResponseDTO addNewDrug(DrugRequestDTO drugRequestDTO) {
    return DrugMapper.toDTO(drugRepository.save(DrugMapper.fromDTO(drugRequestDTO)));
  }

  @Override
  public DrugResponseDTO getDrugDtoByName(String name) {
    return DrugMapper.toDTO(getDrug(name));
  }

  @Override
  public DrugResponseDTO getDrugDtoById(Long id) {
    return DrugMapper.toDTO(getDrug(id));
  }

  @Override
  public Drug getDrugById(Long id) {
    return getDrug(id);
  }

  @Override
  public DrugResponseDTO updateDrug(@NotNull DrugRequestDTO drugResponseDTO) {
    Drug drug = getDrug(drugResponseDTO.getId());

    drug.setName(drugResponseDTO.getName());
    drug.setCommonName(drugResponseDTO.getCommonName());
    drug.setActiveSubstance(drugResponseDTO.getActiveSubstance());
    drug.setMarketingAuthorizationHolder(drugResponseDTO.getMarketingAuthorizationHolder());
    drug.setPharmaceuticalForm(drugResponseDTO.getPharmaceuticalForm());
    drug.setMaNumber(drugResponseDTO.getMaNumber());
    drug.setAtcCode(drugResponseDTO.getAtcCode());
    drug.setStrength(drugResponseDTO.getStrength());

    drug.setModificationDateTime(LocalDateTime.now());

    return DrugMapper.toDTO(drugRepository.save(drug));
  }

  @Override
  public DrugResponseDTO removeDrugByName(String name) {
    Drug drug = getDrug(name);

    return DrugMapper.toDTO(removeDrug(drug));
  }

  @Override
  public DrugResponseDTO removeDrugById(Long id) {
    Drug drug = getDrug(id);

    return DrugMapper.toDTO(removeDrug(drug));
  }

  @Override
  public List<DrugResponseDTO> getAllDrugs() {
    System.out.println("getAllDrugs");
    return drugRepository.findAll().stream().map(DrugMapper::toDTO).toList();
  }

  // Utility
  private @NotNull Drug getDrug(Long id) {
    Optional<Drug> drugOptional = drugRepository.findById(id);

    if (drugOptional.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drug with this name doesn't exist.");
    }

    return drugOptional.get();
  }

  private @NotNull Drug getDrug(String name) {
    Optional<Drug> drugOptional = drugRepository.findByName(name);

    if (drugOptional.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drug with this id doesn't exist.");
    }

    return drugOptional.get();
  }

  private @NotNull Drug removeDrug(@NotNull Drug drug) {
    if (!drug.isActive()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Drug has already been deleted.");
    }
    drug.setActive(false);

    return drugRepository.save(drug);
  }
}
