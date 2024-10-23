package ovh.wiktormalyska.pharmacysystembackend.drug;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

/* Fields for this class have been determined
 * based on data provided in
 * https://rejestry.ezdrowie.gov.pl/rpl/search/public
 * */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Drug {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String commonName;
  private String activeSubstance;
  private String marketingAuthorizationHolder;
  private String pharmaceuticalForm;
  private String maNumber;
  private String atcCode;
  private String strength;

  @Builder.Default private boolean isActive = true;
}
