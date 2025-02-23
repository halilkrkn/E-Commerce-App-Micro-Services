package org.halilkrkn.ecommerce.kafka;

import org.halilkrkn.ecommerce.dto.response.customer.CustomerResponse;
import org.halilkrkn.ecommerce.dto.response.product.PurchaseProductResponse;
import org.halilkrkn.ecommerce.entities.payment_method.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseProductResponse> products
) {}
