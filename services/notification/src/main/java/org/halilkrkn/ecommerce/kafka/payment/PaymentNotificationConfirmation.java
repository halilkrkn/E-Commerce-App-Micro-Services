package org.halilkrkn.ecommerce.kafka.payment;

import java.math.BigDecimal;

public record PaymentNotificationConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail

) {
}
