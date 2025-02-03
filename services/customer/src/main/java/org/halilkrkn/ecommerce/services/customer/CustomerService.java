package org.halilkrkn.ecommerce.services.customer;

import jakarta.validation.Valid;
import org.halilkrkn.ecommerce.dto.requests.CustomerRequest;
import org.halilkrkn.ecommerce.dto.responses.CustomerResponse;

import java.util.List;

public interface CustomerService {
    String createCustomer(@Valid CustomerRequest request);

    void updateCustomer(@Valid CustomerRequest request);

    List<CustomerResponse> findAllCustomers();

    Boolean existsByCustomerId(String customerId);

    CustomerResponse findByCustomerId(String customerId);

    void deleteCustomer(String customerId);
}
