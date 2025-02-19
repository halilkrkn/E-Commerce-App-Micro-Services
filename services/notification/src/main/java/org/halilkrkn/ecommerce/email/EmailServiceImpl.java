package org.halilkrkn.ecommerce.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.halilkrkn.ecommerce.kafka.order.Product;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    @Override
    public void sendPaymentSuccessEmail(String destinationEmail, String customerName, BigDecimal amount, String orderReference) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED, UTF_8.name());
        messageHelper.setFrom("halilkrkn000@gmail.com");
        final String templateName = EmailTemplates.PAYMENT_CONFIRMATION.getTemplate();

        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("customerName", customerName);
        templateModel.put("amount", amount);
        templateModel.put("orderReference", orderReference);

        Context context = new Context();
        context.setVariables(templateModel);
        messageHelper.setSubject(EmailTemplates.PAYMENT_CONFIRMATION.getSubject());

        try {
            String html = templateEngine.process(templateName, context);
            messageHelper.setText(html, true);
            messageHelper.setTo(destinationEmail);
            javaMailSender.send(mimeMessage);
            log.info(String.format("INFO - Email sent successfully sent to %s with template %s,", destinationEmail));
        } catch (MessagingException e) {
            log.error("WARNING - Cannot send email to {}", destinationEmail);
            throw e;
        }
    }

    @Override
    public void sendOrderConfirmationEmail(String destinationEmail, String customerName, BigDecimal totalAmount, String orderReference, List<Product> products) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED, UTF_8.name());
        messageHelper.setFrom("halilkrkn000@gmail.com");
        final String templateName = EmailTemplates.ORDER_CONFIRMATION.getTemplate();

        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("customerName", customerName);
        templateModel.put("totalAmount", totalAmount);
        templateModel.put("orderReference", orderReference);
        templateModel.put("products", products);

        Context context = new Context();
        context.setVariables(templateModel);
        messageHelper.setSubject(EmailTemplates.ORDER_CONFIRMATION.getSubject());

        try {
            String html = templateEngine.process(templateName, context);
            messageHelper.setText(html, true);
            messageHelper.setTo(destinationEmail);
            javaMailSender.send(mimeMessage);
            log.info(String.format("INFO - Email sent successfully sent to %s with template %s,", destinationEmail));
        } catch (MessagingException e) {
            log.error("WARNING - Cannot send email to {}", destinationEmail);
            throw e;
        }
    }

}
