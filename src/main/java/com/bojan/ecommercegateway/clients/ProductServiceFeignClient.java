package com.bojan.ecommercegateway.clients;

import com.bojan.ecommercegateway.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service-client", url = "http://localhost:8081/ecommerce/api/v1/products")
public interface ProductServiceFeignClient {

    @GetMapping("/{uid}")
    ResponseEntity<ProductDto> get(@PathVariable String uid);

    @PostMapping
    ResponseEntity<Void> createProduct(@RequestBody ProductDto dto);
}
