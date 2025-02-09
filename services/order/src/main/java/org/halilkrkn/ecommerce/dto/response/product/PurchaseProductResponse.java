package org.halilkrkn.ecommerce.dto.response.product;

import java.math.BigDecimal;

public record PurchaseProductResponse(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {}
