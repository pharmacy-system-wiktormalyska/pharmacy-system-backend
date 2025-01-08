package ovh.wiktormalyska.pharmacysystembackend.pharmacy;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.*;
import ovh.wiktormalyska.pharmacysystembackend.manager.Manager;

/* Fields for this class have been determined
 * based on data provided in
 * https://rejestry.ezdrowie.gov.pl/ra/search/public
 * */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pharmacy {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; // System id

  private Long pharmacyId; // Received after registering the pharmacy in Poland
  private String name;
  private String address;
  private PharmacyType type;
  private String owner;
  private boolean isPharmacyStatusActive; // Official status

  // Contact details
  private String phone;
  private String email;
  private String website;

  @OneToOne
  private Manager manager;

  @Builder.Default private LocalDateTime creationDateTime = LocalDateTime.now();
  private LocalDateTime modificationDateTime;

  @Builder.Default private boolean isActive = true; // System status
}
