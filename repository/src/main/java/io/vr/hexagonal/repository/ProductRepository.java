package io.vr.hexagonal.repository;

import io.vr.hexagonal.repository.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
