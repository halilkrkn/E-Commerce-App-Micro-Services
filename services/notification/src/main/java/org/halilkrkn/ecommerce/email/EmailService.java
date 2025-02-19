package org.halilkrkn.ecommerce.email;

import jakarta.mail.MessagingException;
import org.halilkrkn.ecommerce.kafka.order.Product;

import java.math.BigDecimal;
import java.util.List;

public interface EmailService {
    void sendPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) throws MessagingException;

    void sendOrderConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal totalAmount,
            String orderReference,
            List<Product> products
    ) throws MessagingException;
}
