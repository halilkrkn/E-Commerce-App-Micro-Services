package org.halilkrkn.ecommerce.entities.product;

import jakarta.persistence.*;
import lombok.*;
import org.halilkrkn.ecommerce.entities.category.Category;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private double availableQuantity;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
