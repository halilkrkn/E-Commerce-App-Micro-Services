package org.halilkrkn.ecommerce.core.open_feign;

import org.halilkrkn.ecommerce.dto.request.payment.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "payment-service",
        url ="${application.config.payment-url}"
)
public interface PaymentClient {
    @PostMapping
    Integer requestOrderPayment(
            @RequestBody PaymentRequest request
    );
}
