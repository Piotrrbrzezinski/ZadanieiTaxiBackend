package com.iTaxi.ZadanieTestowe.app.controllers;

import com.iTaxi.ZadanieTestowe.app.dto.ProductDTO;
import com.iTaxi.ZadanieTestowe.app.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/products")
@Tag(name = "ProductController", description = "REST APIs related to Product Entity!")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Zwraca mi listę wszystkich produktów")
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Wyszukuje produkt po danym id")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @Operation(summary = "Dodaje nowy produkt")
    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO newProduct = productService.addProduct(productDTO);
        return ResponseEntity.ok(newProduct);
    }

    @Operation(summary = "Usuwa produkt po id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

