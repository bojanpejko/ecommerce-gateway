package com.bojan.ecommercegateway.dto;

import com.bojan.ecommerce.order.Status;

import java.time.Instant;
import java.util.List;

public record OrderDto(
        String uid,
        String customerId,
        Status status,
        String shippingAddress,
        String trackingNumber,
        List<OrderedProductDto> orderedProducts,
        Instant createdAt,
        Instant updatedAt
) {}
