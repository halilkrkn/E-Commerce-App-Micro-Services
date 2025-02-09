package org.halilkrkn.ecommerce.payment;

import org.halilkrkn.ecommerce.notification.PaymentNotificationConfirmation;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment(PaymentRequest request) {
        return Payment.builder()
                .id(request.id())
                .orderId(request.orderId())
                .paymentMethod(request.paymentMethod())
                .amount(request.amount())
//                .orderReference(request.orderReference())
//                .customer(request.customer())
                .build();
    }

    public PaymentNotificationConfirmation toPaymentNotificationConfirmationRequest(PaymentRequest request) {
        return new PaymentNotificationConfirmation(
                request.orderReference(),
                request.amount(),
                request.paymentMethod(),
                request.customer().firstname(),
                request.customer().lastname(),
                request.customer().email()
        );
    }
}
