package com.iTaxi.ZadanieTestowe.app.service;

import com.iTaxi.ZadanieTestowe.app.Entity.Product;
import com.iTaxi.ZadanieTestowe.app.dta.ProductRepository;
import com.iTaxi.ZadanieTestowe.app.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    private ModelMapper modelMapper;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .orElse(null);
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Product savedProduct = productRepository.save(product);
        log.info("Dodano nowy produkt");
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
        log.info("Usunięto produkt id:" + id);
    }
}
