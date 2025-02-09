package org.halilkrkn.ecommerce.services.order;

import org.halilkrkn.ecommerce.dto.request.order.OrderRequest;
import org.halilkrkn.ecommerce.dto.response.order.OrderResponse;

import java.util.List;

public interface OrderService {
    Integer createOrder(OrderRequest orderRequest);

    List<OrderResponse> findAll();

    OrderResponse findById(Integer orderId);
}
