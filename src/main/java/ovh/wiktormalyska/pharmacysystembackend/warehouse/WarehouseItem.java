package ovh.wiktormalyska.pharmacysystembackend.warehouse;

import jakarta.persistence.*;
import lombok.*;
import ovh.wiktormalyska.pharmacysystembackend.drug.Drug;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne private Warehouse warehouse;

  @ManyToOne private Drug drug;

  private Long priceInCents;
  private Long quantity;
}
