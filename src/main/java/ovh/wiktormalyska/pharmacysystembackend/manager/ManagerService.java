package ovh.wiktormalyska.pharmacysystembackend.manager;

public interface ManagerService {
  ManagerResponseDTO addNewManager(ManagerRequestDTO managerRequestDTO);

  ManagerResponseDTO getManagerById(Long id);

  ManagerResponseDTO updateManager(ManagerRequestDTO managerRequestDTO);

  ManagerResponseDTO removeManagerById(Long id);
}
