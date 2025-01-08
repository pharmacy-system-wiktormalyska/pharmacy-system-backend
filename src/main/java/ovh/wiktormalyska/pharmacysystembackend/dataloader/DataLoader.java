package ovh.wiktormalyska.pharmacysystembackend.dataloader;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ovh.wiktormalyska.pharmacysystembackend.administrator.Administrator;
import ovh.wiktormalyska.pharmacysystembackend.administrator.AdministratorRepository;

@Component
public class DataLoader implements ApplicationRunner {

    private final AdministratorRepository administratorRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public DataLoader(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Administrator administrator = Administrator.builder().username("admin").password(passwordEncoder.encode("admin")).name("adminName").surname("adminSurname").build();
        System.out.println(administrator);
        administratorRepository.findByUsername("admin").ifPresentOrElse(
                admin -> System.out.println("Admin already exists"),
                () -> administratorRepository.save(administrator)
        );
    }
}
