package com.bojan.ecommercegateway.mappers;

import com.bojan.ecommerce.order.OrderedProductResponse;
import com.bojan.ecommercegateway.dto.OrderedProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface OrderedProductMapper {

    @Mapping(source = "productUid", target = "product.uid")
    @Mapping(source = "orderUid", target = "order.uid")
    OrderedProductDto toDto(OrderedProductResponse response);

    @Mapping(source = "product.uid", target = "productUid")
    @Mapping(source = "order.uid", target = "orderUid")
    OrderedProductResponse toResponse(OrderedProductDto dto);
}
