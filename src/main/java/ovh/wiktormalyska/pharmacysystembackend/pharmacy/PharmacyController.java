package ovh.wiktormalyska.pharmacysystembackend.pharmacy;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pharmacy/")
public class PharmacyController {
  private final PharmacyService pharmacyService;

  public PharmacyController(PharmacyService pharmacyService) {
    this.pharmacyService = pharmacyService;
  }

  @PostMapping("add")
  public PharmacyResponseDTO addNewPharmacy(@RequestBody PharmacyRequestDTO pharmacyRequestDTO) {
    return pharmacyService.addNewPharmacy(pharmacyRequestDTO);
  }

  @GetMapping("get/all")
  public List<PharmacyResponseDTO> getPharmacyById() {
    return pharmacyService.getAllPharmacyDtos();
  }

  @GetMapping("get/id/{id}")
  public PharmacyResponseDTO getPharmacyById(@PathVariable Long id) {
    return pharmacyService.getPharmacyDtoById(id);
  }

  @GetMapping("get/manager/{managerId}")
  public PharmacyResponseDTO getPharmacyByManagerId(@PathVariable Long managerId) {
    return pharmacyService.getPharmacyDtoByManagerId(managerId);
  }

  @PutMapping("update")
  public PharmacyResponseDTO updatePharmacy(@RequestBody PharmacyRequestDTO pharmacyRequestDTO) {
    return pharmacyService.updatePharmacy(pharmacyRequestDTO);
  }

  @DeleteMapping("remove/id/{id}")
  public PharmacyResponseDTO removePharmacyById(@PathVariable Long id) {
    return pharmacyService.removePharmacyById(id);
  }
}
