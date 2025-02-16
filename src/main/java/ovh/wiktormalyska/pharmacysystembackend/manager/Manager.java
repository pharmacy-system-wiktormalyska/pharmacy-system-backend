package ovh.wiktormalyska.pharmacysystembackend.manager;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ovh.wiktormalyska.pharmacysystembackend.pharmacy.Pharmacy;
import ovh.wiktormalyska.pharmacysystembackend.security.UserRole;
import ovh.wiktormalyska.pharmacysystembackend.user.CustomUserDetails;

/* Fields for this class have been determined
 * based on data provided in
 * https://www.biznes.gov.pl/pl/portal/ou117
 * */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Manager implements CustomUserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String username;
  private String surname;
  private String pesel;
  private String familyName;
  private String placeOfBirth;
  private LocalDate dateOfBirth;
  private String nationality;
  private String address;
  private String correspondenceAddress;
  private String fathersName;
  private String mothersName;
  private String education;

  @OneToOne private Pharmacy pharmacy;

  @Builder.Default private LocalDateTime creationDateTime = LocalDateTime.now();
  private LocalDateTime modificationDateTime;

  private String password;
  @Builder.Default private boolean isActive = true;
  @Builder.Default private UserRole role = UserRole.MANAGER;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singleton(new SimpleGrantedAuthority(role.toString()));
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return isActive;
  }

  @Override
  public boolean isAccountNonLocked() {
    return isActive;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return isActive;
  }

  @Override
  public boolean isEnabled() {
    return isActive;
  }

  @Override
  public String getRealName() {
    return (name == null || surname == null) ? username : (name + " " + surname);
  }
}
