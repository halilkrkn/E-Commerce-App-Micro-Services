package org.halilkrkn.ecommerce.services.order;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.halilkrkn.ecommerce.core.exception.BusinessException;
import org.halilkrkn.ecommerce.core.mapper.OrderMapper;
import org.halilkrkn.ecommerce.core.open_feign.CustomerClient;
import org.halilkrkn.ecommerce.core.rest_template.ProductClient;
import org.halilkrkn.ecommerce.dto.request.order.OrderRequest;
import org.halilkrkn.ecommerce.dto.request.order_line.OrderLineRequest;
import org.halilkrkn.ecommerce.dto.request.product.PurchaseProductRequest;
import org.halilkrkn.ecommerce.dto.response.order.OrderResponse;
import org.halilkrkn.ecommerce.kafka.OrderConfirmation;
import org.halilkrkn.ecommerce.kafka.OrderProducer;
import org.halilkrkn.ecommerce.repositories.OrderRepository;
import org.halilkrkn.ecommerce.services.order_line.OrderLineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    //OpenFeign nedir ve ne için kullanılır?
    //OpenFeign, REST tabanlı servisler arasında iletişim kurmak için kullanılan bir HTTP istemci kütüphanesidir.
    private final CustomerClient customerClient;
    // Bu ProductClient kısmı RestTemplate ile yapıldı.
    // OpenFeign ile yapılacak olan bir servis çağrısı ile de yapılabilir.
    // Ama bu kısmı başka bir yöntem olan RestTemplate ile de yapabiliriz.
    // RestTemplate nedir ve ne için kullanılır?
    // RestTemplate, Spring tarafından sunulan bir HTTP istemci kütüphanesidir. RestTemplate, REST tabanlı servisler arasında iletişim kurmak için kullanılır.
    // Yani OpenFeign ile RestTemplate arasında bir fark yoktur. İkisi de REST tabanlı servisler arasında iletişim kurmak için kullanılır.
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;


    @Override
    public Integer createOrder(OrderRequest orderRequest) {
        // check the customer --> OpenFeign --> customer-ms
        var customer = customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the provided ID:: " + orderRequest.customerId()));

        // purchase the products --> product-ms (RestTemplate)
        var purchasedProducts = productClient.purchaseProducts(orderRequest.products());

        // persist the order
        var order = orderRepository.save(mapper.toOrderRequest(orderRequest));
        // persist the order lines
        for(PurchaseProductRequest purchaseProductRequest : orderRequest.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseProductRequest.productId(),
                            purchaseProductRequest.quantity()
                    )
            );
        }

        // start payment process --> payment-ms

        // send the order confirmation email --> notification-ms (kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();
    }

    @Override
    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream()
                .map(mapper::fromOrderResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse findById(Integer orderId) {
        var orderResponse =  orderRepository.findById(orderId)
                .map(mapper::fromOrderResponse)
                .orElseThrow(() -> new EntityNotFoundException("No order exists with the provided ID:: " + orderId));
        return orderResponse;
    }
}
