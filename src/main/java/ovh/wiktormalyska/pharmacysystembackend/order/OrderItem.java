package ovh.wiktormalyska.pharmacysystembackend.order;

import jakarta.persistence.*;
import lombok.*;
import ovh.wiktormalyska.pharmacysystembackend.drug.Drug;

@Entity
@Table(name = "order_items")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Drug drug;
  private Long quantity;
  private Long priceInCents;
}
