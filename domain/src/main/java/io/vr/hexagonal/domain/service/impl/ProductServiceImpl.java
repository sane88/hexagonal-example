package io.vr.hexagonal.domain.service.impl;

import io.vr.hexagonal.domain.model.ProductDO;
import io.vr.hexagonal.domain.service.ProductService;
import io.vr.hexagonal.repository.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.vr.hexagonal.repository.ProductRepository;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }


    @Override
    public Optional<ProductDO> getProduct(Long id) {
        return repository.findById(id).map( product -> new ProductDO(product.getId(), product.getName()));
    }

    @Override
    public ProductDO save(ProductDO product) {
        var productEntity = repository.save(new Product(product.name()));
        return new ProductDO(productEntity.getId(), productEntity.getName());
    }
}
