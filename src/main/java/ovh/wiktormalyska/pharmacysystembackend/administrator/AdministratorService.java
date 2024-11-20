package ovh.wiktormalyska.pharmacysystembackend.administrator;

public interface AdministratorService {
  AdministratorResponseDTO addNewAdministrator(AdministratorRequestDTO administratorRequestDTO);

  AdministratorResponseDTO getAdministratorDtoById(Long id);

  AdministratorResponseDTO updateAdministrator(AdministratorRequestDTO administratorRequestDTO);

  AdministratorResponseDTO removeAdministratorById(Long id);
}
