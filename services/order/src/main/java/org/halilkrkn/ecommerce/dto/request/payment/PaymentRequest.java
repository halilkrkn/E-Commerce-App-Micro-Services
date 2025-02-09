package org.halilkrkn.ecommerce.dto.request.payment;

import org.halilkrkn.ecommerce.dto.response.customer.CustomerResponse;
import org.halilkrkn.ecommerce.entities.payment_method.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {}