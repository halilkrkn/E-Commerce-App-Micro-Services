package org.halilkrkn.ecommerce.dto.response.customer;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {}
