package org.halilkrkn.ecommerce.core.mapper;

import org.halilkrkn.ecommerce.dto.request.order_line.OrderLineRequest;
import org.halilkrkn.ecommerce.entities.order.Order;
import org.halilkrkn.ecommerce.entities.order_line.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLineRequest(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .order(
                        Order.builder()
                        .id(request.orderId())
                        .build()
                )
                .productId(request.productId())
                .quantity(request.quantity())
                .build();
    }
}
