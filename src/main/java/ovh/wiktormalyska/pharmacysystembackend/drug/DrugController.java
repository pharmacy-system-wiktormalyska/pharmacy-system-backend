package ovh.wiktormalyska.pharmacysystembackend.drug;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drug/")
public class DrugController {
  private final DrugService drugService;

  public DrugController(DrugService drugService) {
    this.drugService = drugService;
  }

  @PostMapping("add")
  public DrugResponseDTO addNewDrug(@RequestBody DrugRequestDTO drugRequestDTO) {
    return drugService.addNewDrug(drugRequestDTO);
  }

  @GetMapping("get/name/{name}")
  public DrugResponseDTO getDrugByName(@PathVariable String name) {
    return drugService.getDrugByName(name);
  }

  @GetMapping("get/id/{id}")
  public DrugResponseDTO getDrugById(@PathVariable Long id) {
    return drugService.getDrugById(id);
  }

  @PutMapping("update")
  public DrugResponseDTO updateDrug(@RequestBody DrugRequestDTO drugRequestDTO) {
    return drugService.updateDrug(drugRequestDTO);
  }

  @DeleteMapping("remove/name/{name}")
  public DrugResponseDTO removeDrugByName(@PathVariable String name) {
    return drugService.removeDrugByName(name);
  }

  @DeleteMapping("remove/id/{id}")
  public DrugResponseDTO removeDrugById(@PathVariable Long id) {
    return drugService.removeDrugById(id);
  }
}
