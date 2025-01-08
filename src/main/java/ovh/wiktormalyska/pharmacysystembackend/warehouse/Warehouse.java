package ovh.wiktormalyska.pharmacysystembackend.warehouse;

import jakarta.persistence.*;
import lombok.*;
import ovh.wiktormalyska.pharmacysystembackend.drugorder.DrugOrder;
import ovh.wiktormalyska.pharmacysystembackend.manager.Manager;
import ovh.wiktormalyska.pharmacysystembackend.pharmacy.Pharmacy;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {
  @Id
  @GeneratedValue
  private Long id;

  @OneToMany
  private List<WarehouseItem> stock;

  @ManyToOne
  private Manager manager;

  // Pharmacies using this warehouse to keep stock
  // One pharmacy can use many warehouses
  // One warehouse can stock up multiple pharmacies
  @ManyToMany
  private List<Pharmacy> pharmacy;

  @OneToMany
  private List<DrugOrder> drugOrders;
}
