package io.vr.hexagonal;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vr.hexagonal.api.dto.ProductDTO;
import io.vr.hexagonal.app.HexagonalExampleApplication;
import io.vr.hexagonal.repository.ProductRepository;
import io.vr.hexagonal.repository.config.RepositoryConfig;
import io.vr.hexagonal.repository.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {HexagonalExampleApplication.class, RepositoryConfig.class})
@AutoConfigureMockMvc
@DirtiesContext
public class ProductApiIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository repository;

    @Test
    void shouldSaveNewProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ProductDTO(null, "SomeProduct"))))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()));

        assertEquals("SomeProduct", repository.findById(1L).map(Product::getName).orElse(""));
    }

    @Test
    void shouldGetExistingProduct() throws Exception {
        var id = repository.save(new Product("SomeProduct")).getId();

        var productDTO = objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.get(String.format("/v1/products/%d", id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString(), ProductDTO.class);

        assertEquals("SomeProduct", productDTO.name());
        assertEquals(id, productDTO.id());
    }
}
