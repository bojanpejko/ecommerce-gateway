package com.bojan.ecommercegateway.services;

import com.bojan.ecommerce.product.GetProductRequest;
import com.bojan.ecommerce.product.ProductRequest;
import com.bojan.ecommerce.product.ProductServiceGrpc;
import com.bojan.ecommercegateway.clients.ProductServiceFeignClient;
import com.bojan.ecommercegateway.dto.ProductDto;
import com.bojan.ecommercegateway.mappers.ProductMapper;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductCommunicatorService {

    @GrpcClient("product-service-client")
    private final ProductServiceGrpc.ProductServiceStub productServiceStub;

    @GrpcClient("product-service-blocking-client")
    private final ProductServiceGrpc.ProductServiceBlockingStub productServiceBlockingStub;

    private final ProductServiceFeignClient productServiceFeignClient;

    private final ProductMapper productMapper;

    public ProductDto getProductGrpc(String uid) {
        return productMapper.toDto(
            productServiceBlockingStub.getProduct(GetProductRequest.newBuilder().setUid(uid).build())
        );
    }

    public ProductDto getProductRest(String uid) {
        return productServiceFeignClient.get(uid).getBody();
    }

    public void populateProductsGrpc(List<ProductDto> products) {
        StreamObserver<ProductRequest> requestObserver = productServiceStub.createProducts(new StreamObserver<Empty>() {
            @Override
            public void onNext(Empty empty) {
                log.info("Response returned!");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                log.info("Product population completed!");
            }
        });

        products.stream().map(productMapper::toRequest).forEach(requestObserver::onNext);
        requestObserver.onCompleted();
    }

    public void populateProductsRest(List<ProductDto> products) {
        products.forEach(productServiceFeignClient::createProduct);
    }
}
