package ovh.wiktormalyska.pharmacysystembackend.dataloader;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ovh.wiktormalyska.pharmacysystembackend.administrator.Administrator;
import ovh.wiktormalyska.pharmacysystembackend.administrator.AdministratorRepository;
import ovh.wiktormalyska.pharmacysystembackend.drug.Drug;
import ovh.wiktormalyska.pharmacysystembackend.drug.DrugRepository;

@Component
public class DataLoader implements ApplicationRunner {

  private final AdministratorRepository administratorRepository;
  private final DrugRepository drugRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public DataLoader(
      AdministratorRepository administratorRepository, DrugRepository drugRepository) {
    this.administratorRepository = administratorRepository;
    this.drugRepository = drugRepository;
    this.passwordEncoder = new BCryptPasswordEncoder();
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Administrator administrator =
        Administrator.builder()
            .username("admin")
            .password(passwordEncoder.encode("admin"))
            .name("adminName")
            .surname("adminSurname")
            .build();
    Drug drug =
        Drug.builder()
            .activeSubstance("Ibuprofen")
            .atcCode("M01AE01")
            .commonName("Ibuprofen AFL")
            .maNumber("14984")
            .marketingAuthorizationHolder("Aflofarm Farmacja Polska Sp. z o.o.")
            .name("Ibuprofen AFL")
            .pharmaceuticalForm("Tabletki drażowane")
            .strength("200 mg")
            .build();
    System.out.println(administrator);
    System.out.println(drug);
    administratorRepository
        .findByUsername("admin")
        .ifPresentOrElse(
            a -> System.out.println("Admin already exists"),
            () -> administratorRepository.save(administrator));
    drugRepository.findByName("Ibuprofen AFL")
        .ifPresentOrElse(
            d -> System.out.println("Drug already exists"),
            () -> drugRepository.save(drug));
  }
}
