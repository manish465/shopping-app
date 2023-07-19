package com.manish.productservice.service;

import com.manish.productservice.dto.ProductRequest;
import com.manish.productservice.dto.ProductResponse;
import com.manish.productservice.model.Product;
import com.manish.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product with product id {} is saved: ", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> productList = productRepository.findAll();

        return productList.stream().map(product -> ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build()
        ).toList();
    }
}
