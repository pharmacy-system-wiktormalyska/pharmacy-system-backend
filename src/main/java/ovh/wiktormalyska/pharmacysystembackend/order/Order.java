package ovh.wiktormalyska.pharmacysystembackend.order;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;
import ovh.wiktormalyska.pharmacysystembackend.pharmacist.Pharmacist;
import ovh.wiktormalyska.pharmacysystembackend.pharmacy.Pharmacy;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne private Pharmacy pharmacy;

  @ManyToOne private Pharmacist pharmacist;

  @OneToMany private List<OrderItem> itemsInOrder;

  @Builder.Default private OrderStatus orderStatus = OrderStatus.CREATED;

  @Builder.Default private LocalDateTime creationDateTime = LocalDateTime.now();
  private LocalDateTime modificationDateTime;

  @Builder.Default private boolean isActive = true;
}
