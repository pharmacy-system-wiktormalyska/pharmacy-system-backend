package ovh.wiktormalyska.pharmacysystembackend.security;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ovh.wiktormalyska.pharmacysystembackend.pharmacist.PharmacistRepository;

@Service
public class AuthService {
  private final PharmacistRepository pharmacistRepository;

  private final AuthenticationManager authenticationManager;
  private final BCryptPasswordEncoder passwordEncoder;

  public AuthService(
      PharmacistRepository pharmacistRepository,
      AuthenticationManager authenticationManager,
      BCryptPasswordEncoder passwordEncoder) {
    this.authenticationManager = authenticationManager;
    this.pharmacistRepository = pharmacistRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public UserDetails login(@NotNull AuthRequestDto authRequestDto) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            authRequestDto.getUsername(), authRequestDto.getPassword()));

    return pharmacistRepository.findByUsername(authRequestDto.getUsername()).orElse(null);
  }

  public String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }
}
