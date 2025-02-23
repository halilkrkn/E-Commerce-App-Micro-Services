package org.halilkrkn.ecommerce.repositories;

import org.halilkrkn.ecommerce.entities.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
