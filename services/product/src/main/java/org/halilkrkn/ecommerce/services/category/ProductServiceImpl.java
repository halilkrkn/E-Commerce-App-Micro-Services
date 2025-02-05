package org.halilkrkn.ecommerce.services.category;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.halilkrkn.ecommerce.core.exception.ProductPurchaseException;
import org.halilkrkn.ecommerce.core.mapper.ProductMapper;
import org.halilkrkn.ecommerce.dto.requests.product.ProductPurchaseRequest;
import org.halilkrkn.ecommerce.dto.responses.product.ProductPurchaseResponse;
import org.halilkrkn.ecommerce.dto.requests.product.ProductRequest;
import org.halilkrkn.ecommerce.dto.responses.product.ProductResponse;
import org.halilkrkn.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Integer createProduct(ProductRequest request) {
        var product = productMapper.toProductRequest(request);
        return productRepository.save(product).getId();
    }

    @Override
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        var productIds = request.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if (storedProducts.size() != productIds.size()) {
            throw new ProductPurchaseException("One or more products does not exists");
        }

        var storedRequest = request.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var requestProduct = storedRequest.get(i);
            if (product.getAvailableQuantity() < requestProduct.quantity()) {
                throw new ProductPurchaseException("Not enough quantity for product with ID:: " + product.getId());
            }

            var newAvailableQuantity = product.getAvailableQuantity() - requestProduct.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, requestProduct.quantity()));
        }
        return List.of();
    }

    @Override
    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::fromProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID:: " + productId));
    }

    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::fromProductResponse)
                .toList();
    }
}
