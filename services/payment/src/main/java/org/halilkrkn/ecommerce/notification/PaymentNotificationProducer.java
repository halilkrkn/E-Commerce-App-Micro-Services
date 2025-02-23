package org.halilkrkn.ecommerce.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentNotificationProducer {

    private final KafkaTemplate<String, PaymentNotificationConfirmation> kafkaTemplate;

    public void sendPaymentNotificationConfirmation(PaymentNotificationConfirmation request) {
        log.info("Sending notification with body: <{}>", request);
        Message<PaymentNotificationConfirmation> message = MessageBuilder
                .withPayload(request)
                .setHeader(KafkaHeaders.TOPIC, "payment-topic")
                .build();
        kafkaTemplate.send(message);
    }

}
