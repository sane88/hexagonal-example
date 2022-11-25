package io.vr.hexagonal.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "io.vr")
@SpringBootApplication
//@EnableAutoConfiguration
public class HexagonalExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(HexagonalExampleApplication.class, args);
    }

}
