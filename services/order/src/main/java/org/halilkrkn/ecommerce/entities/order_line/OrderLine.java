package org.halilkrkn.ecommerce.entities.order_line;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.halilkrkn.ecommerce.entities.order.Order;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class OrderLine {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private Integer productId;
    private double quantity;
}
