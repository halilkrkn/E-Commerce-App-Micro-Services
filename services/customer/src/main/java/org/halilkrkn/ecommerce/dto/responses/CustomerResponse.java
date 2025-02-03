package org.halilkrkn.ecommerce.dto.responses;

import org.halilkrkn.ecommerce.entities.address.Address;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {}
