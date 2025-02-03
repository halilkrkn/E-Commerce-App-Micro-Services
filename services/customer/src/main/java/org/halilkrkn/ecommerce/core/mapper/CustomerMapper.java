package org.halilkrkn.ecommerce.core.mapper;

import org.halilkrkn.ecommerce.dto.requests.CustomerRequest;
import org.halilkrkn.ecommerce.dto.responses.CustomerResponse;
import org.halilkrkn.ecommerce.entities.customer.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer toCustomerRequest(CustomerRequest request) {
        if (request == null) {
            return null;
        }
        return Customer.builder()
                .id(request.id())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .address(request.address()
        ).build();
    }

    public CustomerResponse fromCustomerResponse(Customer customer) {
            return new CustomerResponse(
                    customer.getId(),
                    customer.getFirstname(),
                    customer.getLastname(),
                    customer.getEmail(),
                    customer.getAddress()
            );
    }
}
