package ovh.wiktormalyska.pharmacysystembackend.security;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ovh.wiktormalyska.pharmacysystembackend.user.CustomUserDetails;

@RestController
@RequestMapping("auth")
public class AuthController {
  private final JwtService jwtService;
  private final AuthService authService;

  public AuthController(JwtService jwtService, AuthService authService) {
    this.jwtService = jwtService;
    this.authService = authService;
  }

  @PostMapping("login")
  public AuthResponseDTO login(@RequestBody AuthRequestDTO authRequestDto) {
    CustomUserDetails authenticatedUser = authService.login(authRequestDto);

    return new AuthResponseDTO(jwtService.generateToken(authenticatedUser));
  }
}
