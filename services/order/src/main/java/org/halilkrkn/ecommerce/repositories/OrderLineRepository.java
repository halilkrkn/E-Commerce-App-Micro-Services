package org.halilkrkn.ecommerce.repositories;

import org.halilkrkn.ecommerce.entities.order_line.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}
