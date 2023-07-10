package com.bojan.ecommercegateway.mappers;

import com.bojan.ecommerce.order.CreateOrderRequest;
import com.bojan.ecommerce.order.OrderResponse;
import com.bojan.ecommercegateway.dto.OrderDto;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = OrderedProductMapper.class,
    imports = UUID.class,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface OrderMapper {

    @Mapping(source = "orderedProductsList", target = "orderedProducts")
    OrderDto toDto(OrderResponse response);

    @Mapping(source = "orderedProducts", target = "orderedProductsList")
    CreateOrderRequest toRequest(OrderDto dto);
}
