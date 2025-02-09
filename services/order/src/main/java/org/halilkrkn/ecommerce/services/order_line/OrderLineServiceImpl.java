package org.halilkrkn.ecommerce.services.order_line;

import lombok.RequiredArgsConstructor;
import org.halilkrkn.ecommerce.core.mapper.OrderLineMapper;
import org.halilkrkn.ecommerce.dto.request.order_line.OrderLineRequest;
import org.halilkrkn.ecommerce.dto.response.order_line.OrderLineResponse;
import org.halilkrkn.ecommerce.repositories.OrderLineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineServiceImpl implements OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    @Override
    public Integer saveOrderLine(OrderLineRequest request) {
        var order = orderLineMapper.toOrderLineRequest(request);
        return orderLineRepository.save(order).getId();
    }

    @Override
    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(orderLineMapper::fromOrderLineResponse)
                .toList();
    }
}
