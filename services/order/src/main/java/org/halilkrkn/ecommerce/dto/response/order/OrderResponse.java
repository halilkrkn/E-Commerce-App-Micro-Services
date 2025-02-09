package org.halilkrkn.ecommerce.dto.response.order;

import org.halilkrkn.ecommerce.entities.payment_method.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {}
