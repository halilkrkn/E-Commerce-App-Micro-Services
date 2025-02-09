package org.halilkrkn.ecommerce.controllers;

import lombok.RequiredArgsConstructor;
import org.halilkrkn.ecommerce.dto.response.order_line.OrderLineResponse;
import org.halilkrkn.ecommerce.services.order_line.OrderLineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
public class OrderLineController {

    private final OrderLineService orderLineService;


    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> findAllByOrderId(
            @PathVariable("order-id") Integer orderId
    ) {
        return ResponseEntity.ok(orderLineService.findAllByOrderId(orderId));
    }

}
