package com.bojan.ecommercegateway.mappers;

import com.bojan.ecommerce.product.ProductRequest;
import com.bojan.ecommerce.product.ProductResponse;
import com.bojan.ecommercegateway.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    imports = UUID.class,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface ProductMapper {

    ProductDto toDto(ProductResponse response);

    ProductRequest toRequest(ProductDto dto);
}
