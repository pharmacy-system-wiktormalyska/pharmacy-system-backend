package ovh.wiktormalyska.pharmacysystembackend.user;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetails extends UserDetails {
  String getRealName();
}
