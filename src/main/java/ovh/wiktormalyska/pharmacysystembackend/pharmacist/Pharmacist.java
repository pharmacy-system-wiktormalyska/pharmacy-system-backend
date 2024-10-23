package ovh.wiktormalyska.pharmacysystembackend.pharmacist;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.*;
import ovh.wiktormalyska.pharmacysystembackend.pharmacy.Pharmacy;

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
public class Pharmacist {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  @ManyToOne
  @JoinColumn(name = "pharmacy_id")
  private Pharmacy pharmacy;

  private LocalDateTime modificationDateTime;

  @Builder.Default private boolean isActive = true;
}
