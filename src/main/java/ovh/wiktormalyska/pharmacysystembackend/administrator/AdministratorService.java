package ovh.wiktormalyska.pharmacysystembackend.administrator;

public interface AdministratorService {
  AdministratorResponseDTO addNewAdministrator(AdministratorRequestDTO administratorRequestDTO);

  AdministratorResponseDTO getAdministratorById(Long id);

  AdministratorResponseDTO updateAdministrator(AdministratorRequestDTO administratorRequestDTO);

  AdministratorResponseDTO removeAdministratorById(Long id);
}
