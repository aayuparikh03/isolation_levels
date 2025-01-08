package com.example.service;

import com.example.entity.Product;
import com.example.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PhantomReadService {

    private final ProductRepository productRepository;

    public PhantomReadService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public List<Product> queryProductsTwice() {
        List<Product> firstQuery = productRepository.findAll();

        try {
            Thread.sleep(5000); // Simulate delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        List<Product> secondQuery = productRepository.findAll();
        System.out.println("First Query Size: " + firstQuery.size() + ", Second Query Size: " + secondQuery.size());
        return secondQuery;
    }

    @Transactional
    public void addProduct(Product product) {
        productRepository.save(product);
    }
}
