package org.halilkrkn.ecommerce.entities.category;

import jakarta.persistence.*;
import lombok.*;
import org.halilkrkn.ecommerce.entities.product.Product;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE) // buradaki cascade = CascadeType.REMOVE silme işlemi yaparken ilişkili olan productları da siler
    private List<Product> products;

}