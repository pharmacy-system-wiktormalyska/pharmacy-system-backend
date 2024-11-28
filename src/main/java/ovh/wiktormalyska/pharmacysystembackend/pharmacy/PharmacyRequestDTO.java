package ovh.wiktormalyska.pharmacysystembackend.pharmacy;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PharmacyRequestDTO {
  private Long id;

  private Long pharmacyId;
  private String name;
  private String address;
  private PharmacyType type;
  private String owner;
  private boolean isPharmacyStatusActive;

  private String phone;
  private String email;
  private String website;
}
