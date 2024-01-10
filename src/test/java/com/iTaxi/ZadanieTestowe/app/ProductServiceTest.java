package com.iTaxi.ZadanieTestowe.app;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.iTaxi.ZadanieTestowe.app.Entity.Product;
import com.iTaxi.ZadanieTestowe.app.dta.ProductRepository;
import com.iTaxi.ZadanieTestowe.app.dto.ProductDTO;
import com.iTaxi.ZadanieTestowe.app.service.ProductService;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private ProductService productService;

    @Test
    public void getAllProductsTest() {
        // Przygotowanie danych testowych
        Product product1 = new Product(1L, "Produkt1", 100.0);
        Product product2 = new Product(2L, "Produkt2", 200.0);
        List<Product> productList = Arrays.asList(product1, product2);

        // Mockowanie zachowania repozytorium i model mappera
        when(productRepository.findAll()).thenReturn(productList);
        when(modelMapper.map(any(Product.class), eq(ProductDTO.class)))
                .thenAnswer(i -> new ProductDTO(((Product) i.getArgument(0)).getId(),
                        ((Product) i.getArgument(0)).getName(),
                        ((Product) i.getArgument(0)).getPrice()));

        // Wywołanie testowanej metody
        List<ProductDTO> result = productService.getAllProducts();

        // Weryfikacja wyników
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(product1.getName(), result.get(0).getName());
        assertEquals(product2.getName(), result.get(1).getName());
    }

}

