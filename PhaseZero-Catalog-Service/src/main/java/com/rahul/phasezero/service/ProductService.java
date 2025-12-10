package com.rahul.phasezero.service;

import com.rahul.phasezero.dto.ProductRequestDTO;
import com.rahul.phasezero.dto.ProductResponseDTO;
import com.rahul.phasezero.exception.DuplicatePartNumberException;
import com.rahul.phasezero.exception.InvalidProductDataException;
import com.rahul.phasezero.model.Product;
import com.rahul.phasezero.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    
    public ProductResponseDTO addProduct(ProductRequestDTO dto) {

       
        if (productRepository.findByPartNumber(dto.getPartNumber()).isPresent()) {
            throw new DuplicatePartNumberException("partNumber already exists");
        }

        
        if (dto.getPrice() < 0 || dto.getStock() < 0) {
            throw new InvalidProductDataException("price or stock cannot be negative");
        }

       
        String normalizedName = dto.getPartName().toLowerCase();

        
        Product product = new Product();
        product.setPartNumber(dto.getPartNumber());
        product.setPartName(normalizedName);
        product.setCategory(dto.getCategory());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());

        
        Product saved = productRepository.save(product);

        
        return convertToDTO(saved);
    }

    
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    
    
    public List<ProductResponseDTO> searchByName(String name) {
        return productRepository.findByPartNameContainingIgnoreCase(name)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    
    public List<ProductResponseDTO> filterByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    
    public List<ProductResponseDTO> sortByPriceDesc() {
        return productRepository.findAll()
                .stream()
                .sorted((a, b) -> Double.compare(a.getPrice(), b.getPrice()))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    
    public double getInventoryValue() {
        return productRepository.findAll()
                .stream()
                .mapToDouble(p -> p.getPrice() * p.getStock())
                .sum();
    }

    
    private ProductResponseDTO convertToDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setPartNumber(product.getPartNumber());
        dto.setPartName(product.getPartName());
        dto.setCategory(product.getCategory());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        return dto;
    }
}
