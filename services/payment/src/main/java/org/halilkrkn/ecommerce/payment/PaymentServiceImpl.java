package org.halilkrkn.ecommerce.payment;

import lombok.RequiredArgsConstructor;
import org.halilkrkn.ecommerce.notification.PaymentNotificationConfirmation;
import org.halilkrkn.ecommerce.notification.PaymentNotificationProducer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper mapper;
    private final PaymentNotificationProducer paymentNotificationProducer;

    @Override
    public Integer createPayment(PaymentRequest request) {
        var payment = paymentRepository.save(mapper.toPayment(request));
        paymentNotificationProducer.sendPaymentNotificationConfirmation(mapper.toPaymentNotificationConfirmationRequest(request));
//        paymentNotificationProducer.sendPaymentNotificationConfirmation(new PaymentNotificationConfirmation(
//                request.orderReference(),
//                request.amount(),
//                request.paymentMethod(),
//                request.customer().firstname(),
//                request.customer().lastname(),
//                request.customer().email()
//        ));
        return payment.getId();
    }
}
