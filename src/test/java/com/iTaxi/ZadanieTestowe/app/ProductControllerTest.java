package com.iTaxi.ZadanieTestowe.app;
import com.iTaxi.ZadanieTestowe.app.controllers.ProductController;
import com.iTaxi.ZadanieTestowe.app.dto.ProductDTO;
import com.iTaxi.ZadanieTestowe.app.service.ProductService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;


@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllProductsTest() throws Exception {
        List<ProductDTO> products = Arrays.asList(
                new ProductDTO(1L, "Produkt1", 100.0),
                new ProductDTO(2L, "Produkt2", 200.0)
        );

        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Produkt1")))
                .andExpect(jsonPath("$[1].name", is("Produkt2")));
    }

    @Test
    public void getProductByIdTest() throws Exception {
        ProductDTO product = new ProductDTO(1L, "Produkt1", 100.0);

        when(productService.getProductById(1L)).thenReturn(product);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Produkt1")));
    }

    @Test
    public void addProductTest() throws Exception {
        ProductDTO product = new ProductDTO(1L, "Produkt1", 100.0);

        //when(productService.addProduct(any(ProductDTO.class))).thenReturn(product);
       // when(productService.addProduct(any(ProductDTO.class))).thenReturn(product);
        Mockito.when(productService.addProduct(Mockito.any(ProductDTO.class))).thenReturn(product);


        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Produkt1")));
    }

    @Test
    public void deleteProductTest() throws Exception {
        doNothing().when(productService).deleteProduct(1L);

        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isOk());
    }
}
