package com.bojan.ecommercegateway;

import com.bojan.ecommercegateway.dto.OrderDto;
import com.bojan.ecommercegateway.dto.ProductDto;
import com.bojan.ecommercegateway.services.OrderCommunicatorService;
import com.bojan.ecommercegateway.services.ProductCommunicatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ApiGateway {

    private final ProductCommunicatorService productCommunicatorService;
    private final OrderCommunicatorService orderCommunicatorService;

    @GetMapping("/grpc/products/{uid}")
    public ResponseEntity<ProductDto> getProductGrpc(@PathVariable String uid) {
        return ResponseEntity.ok(productCommunicatorService.getProductGrpc(uid));
    }

    @GetMapping("/grpc/orders/{customerId}")
    public ResponseEntity<List<OrderDto>> loadCustomerOrdersGrpc(@PathVariable String customerId){
        return ResponseEntity.ok(orderCommunicatorService.loadCustomerOrdersGrpc(customerId));
    }

    @PostMapping("/grpc/products")
    public ResponseEntity<Void> populateProductsGrpc(@RequestBody List<ProductDto> products){
        productCommunicatorService.populateProductsGrpc(products);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/grpc/orders")
    public ResponseEntity<OrderDto> placeOrderGrpc(@RequestBody OrderDto dto) {
        return ResponseEntity.ok(orderCommunicatorService.placeOrderGrpc(dto));
    }

    @GetMapping("/rest/products/{uid}")
    public ResponseEntity<ProductDto> getProductRest(@PathVariable String uid) {
        return ResponseEntity.ok(productCommunicatorService.getProductRest(uid));
    }

    @GetMapping("/rest/orders/{customerId}")
    public ResponseEntity<List<OrderDto>> loadCustomerOrdersRest(@PathVariable String customerId){
        return ResponseEntity.ok(orderCommunicatorService.loadCustomerOrdersRest(customerId));
    }

    @PostMapping("/rest/products")
    public ResponseEntity<Void> populateProductsRest(@RequestBody List<ProductDto> products){
        productCommunicatorService.populateProductsRest(products);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/rest/orders")
    public ResponseEntity<OrderDto> placeOrderRest(@RequestBody OrderDto dto) {
        return ResponseEntity.ok(orderCommunicatorService.placeOrderRest(dto));
    }
}
