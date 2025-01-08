package ovh.wiktormalyska.pharmacysystembackend.pharmacist;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pharmacist/")
public class PharmacistController {
  private final PharmacistService pharmacistService;

  public PharmacistController(PharmacistService pharmacistService) {
    this.pharmacistService = pharmacistService;
  }

  @PostMapping("add")
  public PharmacistResponseDTO addNewPharmacist(
      @RequestBody PharmacistRequestDTO pharmacistRequestDTO) {
    return pharmacistService.addNewPharmacist(pharmacistRequestDTO);
  }

  @GetMapping("get/all")
  public List<PharmacistResponseDTO> getAllPharmacists() {
    return pharmacistService.getAllPharmacistDtos();
  }

  @GetMapping("get/id/{id}")
  public PharmacistResponseDTO getPharmacistById(@PathVariable Long id) {
    return pharmacistService.getPharmacistDtoById(id);
  }

  @PutMapping("update")
  public PharmacistResponseDTO updatePharmacist(
      @RequestBody PharmacistRequestDTO pharmacistRequestDTO) {
    return pharmacistService.updatePharmacist(pharmacistRequestDTO);
  }

  @DeleteMapping("remove/id/{id}")
  public PharmacistResponseDTO removePharmacistById(@PathVariable Long id) {
    return pharmacistService.removePharmacistById(id);
  }
}
