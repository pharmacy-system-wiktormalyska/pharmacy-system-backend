package ovh.wiktormalyska.pharmacysystembackend.security;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ovh.wiktormalyska.pharmacysystembackend.administrator.AdministratorRepository;
import ovh.wiktormalyska.pharmacysystembackend.manager.ManagerRepository;
import ovh.wiktormalyska.pharmacysystembackend.pharmacist.PharmacistRepository;
import ovh.wiktormalyska.pharmacysystembackend.user.UserService;

@Service
public class AuthService {
  private final PharmacistRepository pharmacistRepository;

  private final AuthenticationManager authenticationManager;
  private final BCryptPasswordEncoder passwordEncoder;
  private final ManagerRepository managerRepository;
  private final AdministratorRepository administratorRepository;
  private final UserService userService;

  public AuthService(
      PharmacistRepository pharmacistRepository,
      AuthenticationManager authenticationManager,
      BCryptPasswordEncoder passwordEncoder,
      ManagerRepository managerRepository,
      AdministratorRepository administratorRepository, UserService userService) {
    this.authenticationManager = authenticationManager;
    this.pharmacistRepository = pharmacistRepository;
    this.passwordEncoder = passwordEncoder;
    this.managerRepository = managerRepository;
    this.administratorRepository = administratorRepository;
    this.userService = userService;
  }

  public UserDetails login(@NotNull AuthRequestDto authRequestDto) {
    UserDetails userDetails = userService.checkIfUserExists(authRequestDto.getUsername());

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            authRequestDto.getUsername(),
            authRequestDto.getPassword(),
            userDetails.getAuthorities()));

    return userDetails;
  }

  public String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }
}
