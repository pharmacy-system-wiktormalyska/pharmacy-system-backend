package ovh.wiktormalyska.pharmacysystembackend.security;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequestDTO {
  private String username;
  private String password;
}

/*
"username": "string",
"password": "string"
*/
