package ovh.wiktormalyska.pharmacysystembackend.administrator;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrator/")
public class AdministratorController {
  private final AdministratorService administratorService;

  public AdministratorController(AdministratorService administratorService) {
    this.administratorService = administratorService;
  }

  @PostMapping("add")
  public AdministratorResponseDTO addNewAdministrator(
      @RequestBody AdministratorRequestDTO administratorRequestDTO) {
    return administratorService.addNewAdministrator(administratorRequestDTO);
  }

  @GetMapping("get/all")
  public List<AdministratorResponseDTO> getAllAdministrators() {
    return administratorService.getAllAdministratorDtos();
  }

  @GetMapping("get/id/{id}")
  public AdministratorResponseDTO getAdministratorById(@PathVariable Long id) {
    return administratorService.getAdministratorDtoById(id);
  }

  @PutMapping("update")
  public AdministratorResponseDTO updateAdministrator(
      @RequestBody AdministratorRequestDTO administratorRequestDTO) {
    return administratorService.updateAdministrator(administratorRequestDTO);
  }

  @DeleteMapping("remove/id/{id}")
  public AdministratorResponseDTO removeAdministratorById(@PathVariable Long id) {
    return administratorService.removeAdministratorById(id);
  }
}
