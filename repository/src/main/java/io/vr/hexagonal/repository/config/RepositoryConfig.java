package io.vr.hexagonal.repository.config;

import io.vr.hexagonal.repository.ProductRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = ProductRepository.class)
@EntityScan(basePackages = "io.vr.hexagonal.repository.model")
public class RepositoryConfig {
}
