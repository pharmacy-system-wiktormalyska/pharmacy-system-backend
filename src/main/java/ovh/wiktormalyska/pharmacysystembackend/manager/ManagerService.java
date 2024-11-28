package ovh.wiktormalyska.pharmacysystembackend.manager;

public interface ManagerService {
  ManagerResponseDTO addNewManager(ManagerRequestDTO managerRequestDTO);

  ManagerResponseDTO getManagerDtoById(Long id);
  Manager getManagerById(Long id);

  ManagerResponseDTO updateManager(ManagerRequestDTO managerRequestDTO);

  ManagerResponseDTO removeManagerById(Long id);
}
