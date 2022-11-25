package io.vr.hexagonal.app.controller;

import io.vr.hexagonal.api.ProductApi;
import io.vr.hexagonal.api.dto.ProductDTO;
import io.vr.hexagonal.domain.model.ProductDO;
import io.vr.hexagonal.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/products")
public class ProductController implements ProductApi {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductDTO> getProduct(@PathVariable Long id) {
        return service.getProduct(id).map(p -> new ProductDTO(p.id(), p.name()));
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO saveProduct(@RequestBody ProductDTO product) {
        var savedProduct = service.save(new ProductDO(null, product.name()));
        return new ProductDTO(savedProduct.id(), savedProduct.name());
    }
}
