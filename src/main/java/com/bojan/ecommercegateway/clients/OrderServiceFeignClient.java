package com.bojan.ecommercegateway.clients;

import com.bojan.ecommercegateway.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "order-service-client", url = "http://localhost:8082/ecommerce/api/v1/orders")
public interface OrderServiceFeignClient {

    @GetMapping("/{customerId}")
    ResponseEntity<List<OrderDto>> listCustomerOrderHistory(@PathVariable String customerId);

    @PostMapping
    ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto dto);
}
