package org.halilkrkn.ecommerce.services.category;

import jakarta.validation.Valid;
import org.halilkrkn.ecommerce.dto.requests.product.ProductPurchaseRequest;
import org.halilkrkn.ecommerce.dto.responses.product.ProductPurchaseResponse;
import org.halilkrkn.ecommerce.dto.requests.product.ProductRequest;
import org.halilkrkn.ecommerce.dto.responses.product.ProductResponse;

import java.util.List;

public interface ProductService {
    Integer createProduct(@Valid ProductRequest request);

    List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request);

    ProductResponse findById(Integer productId);

    List<ProductResponse> findAll();

}
