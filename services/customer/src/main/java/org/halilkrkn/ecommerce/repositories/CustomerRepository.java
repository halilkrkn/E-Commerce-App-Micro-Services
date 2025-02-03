package org.halilkrkn.ecommerce.repositories;

import org.halilkrkn.ecommerce.entities.customer.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
