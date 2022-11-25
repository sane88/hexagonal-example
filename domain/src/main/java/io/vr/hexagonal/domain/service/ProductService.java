package io.vr.hexagonal.domain.service;

import io.vr.hexagonal.domain.model.ProductDO;

import java.util.Optional;

public interface ProductService {
    Optional<ProductDO> getProduct(Long in);

    ProductDO save(ProductDO product);
}
