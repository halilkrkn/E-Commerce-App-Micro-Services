package org.halilkrkn.ecommerce.kafka.notification.service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.halilkrkn.ecommerce.email.EmailService;
import org.halilkrkn.ecommerce.entity.notification.Notification;
import org.halilkrkn.ecommerce.entity.notification.NotificationType;
import org.halilkrkn.ecommerce.kafka.order.OrderConfirmation;
import org.halilkrkn.ecommerce.kafka.payment.PaymentNotificationConfirmation;
import org.halilkrkn.ecommerce.repository.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumerService {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentConfirmationNotification(PaymentNotificationConfirmation paymentNotificationConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message from payment-topic Topic:: %s", paymentNotificationConfirmation));
        // Save notification to database
        notificationRepository.save(Notification.builder()
                .type(NotificationType.PAYMENT_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .paymentNotificationConfirmation(paymentNotificationConfirmation)
                .build()
        );
        // Send email to customer
        var customerName = paymentNotificationConfirmation.customerFirstname() + " " + paymentNotificationConfirmation.customerLastname();
        emailService.sendPaymentSuccessEmail(
                paymentNotificationConfirmation.customerEmail(),
                customerName,
                paymentNotificationConfirmation.amount(),
                paymentNotificationConfirmation.orderReference()
        );
    }

    // Bu methodun amacı, order-topic topic'ine gelen mesajı dinlemek ve gelen mesajı veritabanına kaydetmek.
    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
        // Save notification to database
        notificationRepository.save(Notification.builder()
                .type(NotificationType.ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build()
        );
        // Send email to customer
        var customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }
}
