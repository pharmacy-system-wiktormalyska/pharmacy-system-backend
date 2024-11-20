package ovh.wiktormalyska.pharmacysystembackend.security;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequestDto {
  private String email;
  private String password;
}