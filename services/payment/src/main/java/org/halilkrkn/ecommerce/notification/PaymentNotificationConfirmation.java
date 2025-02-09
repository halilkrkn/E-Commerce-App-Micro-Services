package org.halilkrkn.ecommerce.notification;

import org.halilkrkn.ecommerce.payment.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {}
