package ovh.wiktormalyska.pharmacysystembackend.drugorder;

import java.util.List;

public interface DrugOrderService {
  DrugOrderResponseDTO addNewDrugOrder(DrugOrderRequestDTO drugRequestDTO);

  DrugOrderResponseDTO getDrugOrderById(Long id);

  DrugOrderResponseDTO updateDrugOrder(DrugOrderRequestDTO drugRequestDTO);

  DrugOrderResponseDTO removeDrugOrderById(Long id);

  List<DrugOrderResponseDTO> getAllDrugOrders();

  DrugOrderResponseDTO completeDrugOrderById(Long id);

  DrugOrderResponseDTO acceptDrugOrderById(Long id);

  DrugOrderResponseDTO rejectDrugOrderById(Long id);
}
