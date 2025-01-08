package ovh.wiktormalyska.pharmacysystembackend.manager;

import java.util.List;

public interface ManagerService {
  ManagerResponseDTO addNewManager(ManagerRequestDTO managerRequestDTO);

  ManagerResponseDTO getManagerDtoById(Long id);

  Manager getManagerById(Long id);

  ManagerResponseDTO updateManager(ManagerRequestDTO managerRequestDTO);

  ManagerResponseDTO removeManagerById(Long id);

  List<ManagerResponseDTO> getAllManagerDtos();
}
