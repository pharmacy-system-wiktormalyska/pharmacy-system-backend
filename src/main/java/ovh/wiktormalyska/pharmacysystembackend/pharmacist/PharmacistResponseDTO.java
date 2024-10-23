package ovh.wiktormalyska.pharmacysystembackend.pharmacist;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PharmacistResponseDTO {
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

  private LocalDateTime modificationDateTime;

  private boolean isActive;
}
