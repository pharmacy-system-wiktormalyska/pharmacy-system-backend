package ovh.wiktormalyska.pharmacysystembackend.manager;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager/")
public class ManagerController {
  private final ManagerService managerService;

  public ManagerController(ManagerService managerService) {
    this.managerService = managerService;
  }

  @PostMapping("add")
  public ManagerResponseDTO addNewManager(@RequestBody ManagerRequestDTO managerRequestDTO) {
    return managerService.addNewManager(managerRequestDTO);
  }

  @GetMapping("get/all")
  public List<ManagerResponseDTO> getAllManagers() {
    return managerService.getAllManagerDtos();
  }

  @GetMapping("get/id/{id}")
  public ManagerResponseDTO getManagerById(@PathVariable Long id) {
    return managerService.getManagerDtoById(id);
  }

  @PutMapping("update")
  public ManagerResponseDTO updateManager(@RequestBody ManagerRequestDTO managerRequestDTO) {
    return managerService.updateManager(managerRequestDTO);
  }

  @DeleteMapping("remove/id/{id}")
  public ManagerResponseDTO removeManagerById(@PathVariable Long id) {
    return managerService.removeManagerById(id);
  }
}
