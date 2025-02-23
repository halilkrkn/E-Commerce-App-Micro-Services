package org.halilkrkn.ecommerce.payment;

import jakarta.validation.Valid;

public interface PaymentService {
    Integer createPayment(@Valid PaymentRequest request);
}
