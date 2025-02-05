package org.halilkrkn.ecommerce.dto.requests.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "Product id is mandatory")
        Integer productId,
        @NotNull(message = "Quantity id is mandatory")
        double quantity
) {}
