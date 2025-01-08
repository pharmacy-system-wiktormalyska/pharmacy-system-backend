package ovh.wiktormalyska.pharmacysystembackend.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderItemDTO {
    private Long id;
    private Long drugId;
    private Long quantity;
    private Long priceInCents;
}
