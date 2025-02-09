package org.halilkrkn.ecommerce.services.order;

import org.halilkrkn.ecommerce.dto.request.order.OrderRequest;

public interface OrderService {
    Integer createOrder(OrderRequest orderRequest);
}
