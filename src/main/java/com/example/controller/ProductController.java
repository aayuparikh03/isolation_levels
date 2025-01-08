package com.example.controller;

import com.example.entity.Product;
import com.example.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAllProducts();
    }

    @PutMapping("/{id}/stock/{stock}")
    public String updateStock(@PathVariable Long id, @PathVariable Integer stock) {
        productService.updateStock(id, stock);
        return "Stock updated for product ID: " + id;
    }

    @PostMapping("/add")
    public String addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return "Product added successfully!";
    }

}
