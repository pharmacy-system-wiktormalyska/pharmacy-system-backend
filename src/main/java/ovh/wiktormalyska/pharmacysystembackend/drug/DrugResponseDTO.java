package ovh.wiktormalyska.pharmacysystembackend.drug;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DrugResponseDTO {
  private Long id;

  private String name;
  private String commonName;
  private String activeSubstance;
  private String marketingAuthorizationHolder;
  private String pharmaceuticalForm;
  private String maNumber;
  private String atcCode;
  private String strength;

  private String relativeImageUrl;

  private LocalDateTime modificationDateTime;

  private boolean isActive;
}
