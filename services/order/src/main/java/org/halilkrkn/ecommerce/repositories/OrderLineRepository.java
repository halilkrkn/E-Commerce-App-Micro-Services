package org.halilkrkn.ecommerce.repositories;

import org.halilkrkn.ecommerce.entities.order_line.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findAllByOrderId(Integer orderId);
}
