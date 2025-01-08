package com.example.service;

import com.example.entity.Product;
import com.example.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void updateStock(Long id, Integer newStock) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setStock(newStock);
            productRepository.save(product);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void addProduct(Product product) {
        productRepository.save(product);
    }
}
