package org.halilkrkn.ecommerce.core.mapper;

import org.halilkrkn.ecommerce.dto.request.order.OrderRequest;
import org.halilkrkn.ecommerce.dto.response.order.OrderResponse;
import org.halilkrkn.ecommerce.entities.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrderRequest(OrderRequest orderRequest) {
            return Order.builder()
                    .id(orderRequest.id())
                    .customerId(orderRequest.customerId())
                    .reference(orderRequest.reference())
                    .totalAmount(orderRequest.amount())
                    .paymentMethod(orderRequest.paymentMethod())
                    .build();
    }

    public OrderResponse fromOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
