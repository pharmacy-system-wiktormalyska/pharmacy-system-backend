package ovh.wiktormalyska.pharmacysystembackend.order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import ovh.wiktormalyska.pharmacysystembackend.drug.Drug;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  private Drug drug;
  private Long quantity;
  private Long priceInCents;
}
