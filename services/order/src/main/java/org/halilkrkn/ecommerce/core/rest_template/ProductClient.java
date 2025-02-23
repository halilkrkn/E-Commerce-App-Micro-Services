package org.halilkrkn.ecommerce.core.rest_template;

import lombok.RequiredArgsConstructor;
import org.halilkrkn.ecommerce.core.exception.BusinessException;
import org.halilkrkn.ecommerce.dto.request.product.PurchaseProductRequest;
import org.halilkrkn.ecommerce.dto.response.product.PurchaseProductResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl;
    private final RestTemplate restTemplate;

    public List<PurchaseProductResponse> purchaseProducts(List<PurchaseProductRequest> requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseProductRequest>> requestEntity = new HttpEntity<>(requestBody, headers);
        ParameterizedTypeReference<List<PurchaseProductResponse>> responseType = new ParameterizedTypeReference<>() {};

        ResponseEntity<List<PurchaseProductResponse>> responseEntity = restTemplate.exchange(
                productUrl + "/purchase",
                HttpMethod.POST,
                requestEntity,
                responseType
        );

        if (responseEntity.getStatusCode().isError()) {
            throw new BusinessException("Error occurred while purchasing products: " + responseEntity.getStatusCode());
        }

        return responseEntity.getBody();
    }
}
