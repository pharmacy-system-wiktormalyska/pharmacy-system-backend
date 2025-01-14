package ovh.wiktormalyska.pharmacysystembackend.manager;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ManagerRequestDTO {
  private Long id;

  private String name;
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
}
