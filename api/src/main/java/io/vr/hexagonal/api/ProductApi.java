package io.vr.hexagonal.api;

import io.vr.hexagonal.api.dto.ProductDTO;

import java.util.Optional;

public interface ProductApi {

    Optional<ProductDTO> getProduct(Long id);

    ProductDTO saveProduct(ProductDTO product);
}
