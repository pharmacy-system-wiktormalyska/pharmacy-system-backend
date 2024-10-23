package ovh.wiktormalyska.pharmacysystembackend.drug;

public interface DrugService {
  DrugResponseDTO addNewDrug(DrugRequestDTO drugRequestDTO);

  DrugResponseDTO getDrugByName(String name);

  DrugResponseDTO getDrugById(Long id);

  DrugResponseDTO updateDrug(DrugRequestDTO drugRequestDTO);

  DrugResponseDTO removeDrugByName(String name);

  DrugResponseDTO removeDrugById(Long id);
}
