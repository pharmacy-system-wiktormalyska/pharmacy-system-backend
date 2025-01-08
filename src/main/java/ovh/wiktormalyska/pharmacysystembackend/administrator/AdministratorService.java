package ovh.wiktormalyska.pharmacysystembackend.administrator;

import java.util.List;

public interface AdministratorService {
  AdministratorResponseDTO addNewAdministrator(AdministratorRequestDTO administratorRequestDTO);

  AdministratorResponseDTO getAdministratorDtoById(Long id);

  AdministratorResponseDTO updateAdministrator(AdministratorRequestDTO administratorRequestDTO);

  AdministratorResponseDTO removeAdministratorById(Long id);

  List<AdministratorResponseDTO> getAllAdministratorDtos();
}
