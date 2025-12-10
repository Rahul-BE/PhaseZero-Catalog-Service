package com.rahul.phasezero.controller;

import com.rahul.phasezero.dto.ProductRequestDTO;
import com.rahul.phasezero.dto.ProductResponseDTO;
import com.rahul.phasezero.service.ProductService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> addProduct(@Valid @RequestBody ProductRequestDTO dto) {
        ProductResponseDTO created = productService.addProduct(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(
            @RequestParam(required = false) String category) {

        if (category != null) {
            return ResponseEntity.ok(productService.filterByCategory(category));
        }

        return ResponseEntity.ok(productService.getAllProducts());
    }

    
    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDTO>> searchByName(
            @RequestParam String name) {
        return ResponseEntity.ok(productService.searchByName(name));
    }

   
    @GetMapping("/sort/price")
    public ResponseEntity<List<ProductResponseDTO>> sortByPrice() {
        return ResponseEntity.ok(productService.sortByPriceDesc());
    }

   
    @GetMapping("/inventory/value")
    public ResponseEntity<Double> getInventoryValue() {
        return ResponseEntity.ok(productService.getInventoryValue());
    }
}
