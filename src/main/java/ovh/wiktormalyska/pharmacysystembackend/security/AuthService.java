package ovh.wiktormalyska.pharmacysystembackend.security;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ovh.wiktormalyska.pharmacysystembackend.user.UserService;

@Service
public class AuthService {
  private final AuthenticationManager authenticationManager;
  private final BCryptPasswordEncoder passwordEncoder;
  private final UserService userService;

  public AuthService(
      AuthenticationManager authenticationManager,
      BCryptPasswordEncoder passwordEncoder,
      UserService userService) {
    this.authenticationManager = authenticationManager;
    this.passwordEncoder = passwordEncoder;
    this.userService = userService;
  }

  public UserDetails login(@NotNull AuthRequestDto authRequestDto) {
    UserDetails userDetails = userService.checkIfUserExists(authRequestDto.getUsername());

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            authRequestDto.getUsername(),
            authRequestDto.getPassword()));

    return userDetails;
  }

  public String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }
}
