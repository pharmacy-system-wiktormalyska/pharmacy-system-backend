package ovh.wiktormalyska.pharmacysystembackend.drug;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DrugRequestDTO {
  private Long id;

  private String name;
  private String commonName;
  private String activeSubstance;
  private String marketingAuthorizationHolder;
  private String pharmaceuticalForm;
  private String maNumber;
  private String atcCode;
  private String strength;
}
