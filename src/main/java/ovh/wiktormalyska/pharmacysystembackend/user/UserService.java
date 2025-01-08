package ovh.wiktormalyska.pharmacysystembackend.user;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ovh.wiktormalyska.pharmacysystembackend.administrator.Administrator;
import ovh.wiktormalyska.pharmacysystembackend.administrator.AdministratorRepository;
import ovh.wiktormalyska.pharmacysystembackend.manager.Manager;
import ovh.wiktormalyska.pharmacysystembackend.manager.ManagerRepository;
import ovh.wiktormalyska.pharmacysystembackend.pharmacist.Pharmacist;
import ovh.wiktormalyska.pharmacysystembackend.pharmacist.PharmacistRepository;

@Service
public class UserService {
  private final PharmacistRepository pharmacistRepository;
  private final ManagerRepository managerRepository;
  private final AdministratorRepository administratorRepository;

  public UserService(PharmacistRepository pharmacistRepository, ManagerRepository managerRepository, AdministratorRepository administratorRepository) {
    this.pharmacistRepository = pharmacistRepository;
    this.managerRepository = managerRepository;
    this.administratorRepository = administratorRepository;
  }

  public CustomUserDetails checkIfUserExists(String username) {
    Pharmacist pharmacist =
        pharmacistRepository.findByUsername(username).orElse(null);
    Manager manager = managerRepository.findByUsername(username).orElse(null);
    Administrator administrator =
        administratorRepository.findByUsername(username).orElse(null);

    CustomUserDetails userDetails =
        pharmacist == null ? (manager == null ? (administrator) : manager) : pharmacist;

    if (userDetails == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    return userDetails;
  }
}
