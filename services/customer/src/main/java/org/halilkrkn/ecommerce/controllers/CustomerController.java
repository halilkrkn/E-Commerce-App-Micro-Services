package org.halilkrkn.ecommerce.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.halilkrkn.ecommerce.dto.requests.CustomerRequest;
import org.halilkrkn.ecommerce.dto.responses.CustomerResponse;
import org.halilkrkn.ecommerce.services.customer.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid CustomerRequest request
    ) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(
            @RequestBody @Valid CustomerRequest request
    ) {
        customerService.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAllCustomers() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> existsByCustomerId(
            @PathVariable("customer-id") String customerId
    ) {
        return ResponseEntity.ok(customerService.existsByCustomerId(customerId));
    }

    @GetMapping("{customer-id}")
    public ResponseEntity<CustomerResponse> findByCustomerId(
            @PathVariable("customer-id") String customerId
    ) {
        return ResponseEntity.ok(customerService.findByCustomerId(customerId));
    }

    @DeleteMapping("{customer-id}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable("customer-id") String customerId
    ) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }
}
