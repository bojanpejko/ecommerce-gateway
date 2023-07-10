package com.bojan.ecommercegateway.services;

import com.bojan.ecommerce.order.ListCustomerOrderHistoryRequest;
import com.bojan.ecommerce.order.OrderResponse;
import com.bojan.ecommerce.order.OrderServiceGrpc;
import com.bojan.ecommercegateway.clients.OrderServiceFeignClient;
import com.bojan.ecommercegateway.dto.OrderDto;
import com.bojan.ecommercegateway.mappers.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderCommunicatorService {

    @GrpcClient("order-service-client")
    private final OrderServiceGrpc.OrderServiceBlockingStub orderServiceStub;

    private final OrderServiceFeignClient orderServiceFeignClient;

    private final OrderMapper orderMapper;

    public List<OrderDto> loadCustomerOrdersGrpc(String customerId) {
        Iterable<OrderResponse> iterable = () -> orderServiceStub.listCustomerOrderHistory(
            ListCustomerOrderHistoryRequest
                .newBuilder()
                .setCustomerId(customerId)
                .build()
        );
        return StreamSupport.stream(iterable.spliterator(), false)
                .map(orderMapper::toDto)
                .toList();
    }

    public List<OrderDto> loadCustomerOrdersRest(String customerId) {
        return orderServiceFeignClient.listCustomerOrderHistory(customerId).getBody();
    }

    public OrderDto placeOrderGrpc(OrderDto dto) {
        OrderResponse response = orderServiceStub.createOrder(orderMapper.toRequest(dto));
        return orderMapper.toDto(response);
    }

    public OrderDto placeOrderRest(OrderDto dto) {
        return orderServiceFeignClient.createOrder(dto).getBody();
    }
}
