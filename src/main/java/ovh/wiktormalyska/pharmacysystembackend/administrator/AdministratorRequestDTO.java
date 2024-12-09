package ovh.wiktormalyska.pharmacysystembackend.administrator;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdministratorRequestDTO {
  private Long id;

  private String name;
  private String username;
  private String surname;
  private String familyName;
  private String placeOfBirth;
  private LocalDateTime dateOfBirth;
  private String nationality;
  private String address;
  private String correspondenceAddress;
  private String fathersName;
  private String mothersName;
  private String education;

  private String password;

  private Long pharmacyId;
}
