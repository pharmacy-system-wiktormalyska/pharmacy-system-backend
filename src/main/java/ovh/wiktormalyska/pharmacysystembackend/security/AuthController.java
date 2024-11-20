package ovh.wiktormalyska.pharmacysystembackend.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  public AuthResponseDto login(@RequestBody AuthRequestDto authRequestDto) {
    UserDetails authenticatedUser = authService.login(authRequestDto);

    return new AuthResponseDto(jwtService.generateToken(authenticatedUser));
  }
}