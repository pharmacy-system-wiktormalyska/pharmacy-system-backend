package ovh.wiktormalyska.pharmacysystembackend.pharmacist;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PharmacistRequestDTO {
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

  private Long pharmacyId;
}
