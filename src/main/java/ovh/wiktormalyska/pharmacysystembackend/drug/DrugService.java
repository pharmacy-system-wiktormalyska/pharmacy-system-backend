package ovh.wiktormalyska.pharmacysystembackend.drug;

import java.util.List;

public interface DrugService {
  DrugResponseDTO addNewDrug(DrugRequestDTO drugRequestDTO);

  DrugResponseDTO getDrugDtoByName(String name);

  DrugResponseDTO getDrugDtoById(Long id);

  Drug getDrugById(Long id);

  DrugResponseDTO updateDrug(DrugRequestDTO drugRequestDTO);

  DrugResponseDTO removeDrugByName(String name);

  DrugResponseDTO removeDrugById(Long id);

  List<DrugResponseDTO> getAllDrugs();
}
