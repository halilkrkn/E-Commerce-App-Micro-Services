package org.halilkrkn.ecommerce.services.customer;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.halilkrkn.ecommerce.core.exception.CustomerNotFoundException;
import org.halilkrkn.ecommerce.core.mapper.CustomerMapper;
import org.halilkrkn.ecommerce.dto.requests.CustomerRequest;
import org.halilkrkn.ecommerce.dto.responses.CustomerResponse;
import org.halilkrkn.ecommerce.entities.customer.Customer;
import org.halilkrkn.ecommerce.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    @Override
    public String createCustomer(CustomerRequest request) {
        var customer = customerRepository.save(customerMapper.toCustomerRequest(request));
        return customer.getId();
    }

    @Override
    public void updateCustomer(CustomerRequest request) {
        var customer = customerRepository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Cannot update customer:: No customer found with the provide:: %s", request.id())
                ));
        mergerCustomer(customer, request);
        customerRepository.save(customer);
    }

    @Override
    public List<CustomerResponse> findAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomerResponse)
                .toList();
    }

    @Override
    public Boolean existsByCustomerId(String customerId) {
//        return customerRepository.findById(customerId).isPresent();
        return customerRepository.existsById(customerId);
    }

    @Override
    public CustomerResponse findByCustomerId(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::fromCustomerResponse)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Cannot find customer:: No customer found with the provided ID:: %s", customerId)
                ));
    }

    @Override
    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }

    // Bu mergerCustomer neden oluşturuldu? Kullanım amacı nedir?
    // Cevabınızı aşağıdaki boşlukta cevaplayabilirsiniz.
    // Bu metod, Customer nesnesi ve CustomerRequest nesnesi arasında güncelleme işlemi yaparken
    // Customer nesnesinin güncellenmesi gereken alanlarını CustomerRequest nesnesinden alarak
    // Customer nesnesini güncellemek için oluşturulmuştur.
    // Bu sayede Customer nesnesi üzerindeki güncellenmesi gereken alanlar güncellenirken
    // gereksiz alanlar güncellenmemiş olur.
    private void mergerCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstname())) {
            customer.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank(request.lastname())) {
            customer.setLastname(request.lastname());
        }
        if(StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }
        if(request.address() != null) {
            customer.setAddress(request.address());
        }
    }
}
