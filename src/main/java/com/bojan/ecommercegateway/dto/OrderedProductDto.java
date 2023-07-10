package com.bojan.ecommercegateway.dto;

public record OrderedProductDto(OrderDto order, ProductDto product, Integer quantity) {}
