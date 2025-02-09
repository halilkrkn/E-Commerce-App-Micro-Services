package org.halilkrkn.ecommerce.dto.request.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseProductRequest(
        @NotNull(message = "Product id must be mandatory")
        Integer productId,
        @Positive(message = "Quantity must be positive")
        double quantity
) {}
