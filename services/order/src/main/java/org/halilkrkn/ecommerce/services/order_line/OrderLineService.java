package org.halilkrkn.ecommerce.services.order_line;

import org.halilkrkn.ecommerce.dto.request.order_line.OrderLineRequest;

public interface OrderLineService {
    Integer saveOrderLine(OrderLineRequest orderLineRequest);
}
