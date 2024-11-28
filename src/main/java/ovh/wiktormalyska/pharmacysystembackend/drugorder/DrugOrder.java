package ovh.wiktormalyska.pharmacysystembackend.drugorder;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;
import ovh.wiktormalyska.pharmacysystembackend.drug.Drug;
import ovh.wiktormalyska.pharmacysystembackend.manager.Manager;
import ovh.wiktormalyska.pharmacysystembackend.pharmacist.Pharmacist;

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
public class DrugOrder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne private Drug drug;
  private Long quantity;

  @ManyToOne private Pharmacist pharmacist;
  @ManyToOne private Manager manager;

  @Builder.Default private OrderStatus orderStatus = OrderStatus.PENDING;

  @Builder.Default private LocalDateTime creationDateTime = LocalDateTime.now();
  private LocalDateTime modificationDateTime;

  @Builder.Default private boolean isActive = true;
}
