package ovh.wiktormalyska.pharmacysystembackend.drugorder;

public interface DrugOrderService {
  DrugOrderResponseDTO addNewDrugOrder(DrugOrderRequestDTO drugRequestDTO);

  DrugOrderResponseDTO getDrugOrderById(Long id);

  DrugOrderResponseDTO updateDrugOrder(DrugOrderRequestDTO drugRequestDTO);

  DrugOrderResponseDTO removeDrugOrderById(Long id);
}
